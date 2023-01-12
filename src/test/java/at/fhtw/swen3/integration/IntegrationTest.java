package at.fhtw.swen3.integration;

import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.TrackingInformation;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

import static at.fhtw.swen3.services.dto.TrackingInformation.StateEnum.DELIVERED;
import static at.fhtw.swen3.services.dto.TrackingInformation.StateEnum.INTRANSPORT;
import static at.fhtw.swen3.services.dto.TrackingInformation.StateEnum.INTRUCKDELIVERY;
import static at.fhtw.swen3.services.dto.TrackingInformation.StateEnum.PICKUP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception{
        // import warehouse

        String jsonWarehouse = getResourceFileAsString("kleines_testwarehouse.json");

        mockMvc.perform(
                post("/warehouse")
                        .content(jsonWarehouse)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());

        MvcResult warehouseInfo = mockMvc.perform(get("/warehouse/WENA04")).andExpect(status().is2xxSuccessful()).andReturn();

        Gson gson = new Gson();

        Hop hop = gson.fromJson(warehouseInfo.getResponse().getContentAsString(), Hop.class);
        assertEquals("WENA04", hop.getCode());
        assertEquals("Warehouse Level 4 - Wien", hop.getDescription());


        // submit parcel
        MvcResult parcelResult = mockMvc.perform(
                        post("/parcel")
                                .content("{\n" +
                                        "    \"weight\": 10.1,\n" +
                                        "    \"recipient\": {\n" +
                                        "        \"name\": \"Mark\",\n" +
                                        "        \"street\": \"Atzgersdorfer\",\n" +
                                        "        \"postalCode\": \"1230\",\n" +
                                        "        \"city\": \"Wien\",\n" +
                                        "        \"country\": \"Austria\"\n" +
                                        "    },\n" +
                                        "    \"sender\": {\n" +
                                        "        \"name\": \"Daniel\",\n" +
                                        "        \"street\": \"Lehmgasse 2-6\",\n" +
                                        "        \"postalCode\": \"1100\",\n" +
                                        "        \"city\": \"Wien\",\n" +
                                        "        \"country\": \"Austria\"\n" +
                                        "    }\n" +
                                        "}")
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = parcelResult.getResponse().getContentAsString();

        NewParcelInfo newParcelInfo = gson.fromJson(contentAsString, NewParcelInfo.class);
        String trackingID = newParcelInfo.getTrackingId();

        // get info
        MvcResult info = mockMvc.perform(get("/parcel/" + trackingID))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn();

        Gson gSonInstance = new GsonBuilder()
                .registerTypeAdapter(TrackingInformation.StateEnum.class, (JsonDeserializer<TrackingInformation.StateEnum>)
                        (json, type, context) -> TrackingInformation.StateEnum.fromValue(json.getAsString()))
                .registerTypeAdapter(OffsetDateTime.class, (JsonDeserializer<OffsetDateTime>)
                        (json, type, context) -> OffsetDateTime.parse(json.getAsString()))
                .create();
        TrackingInformation trackingInformation = gSonInstance.fromJson(info.getResponse().getContentAsString(), TrackingInformation.class);

        assertEquals(3, trackingInformation.getFutureHops().size());
        assertEquals(0, trackingInformation.getVisitedHops().size());
        assertEquals(PICKUP, trackingInformation.getState());

        // update parcel
        mockMvc.perform(post("/parcel/" + trackingID + "/reportHop/" + trackingInformation.getFutureHops().get(0).getCode()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        // get info
        MvcResult info2 = mockMvc.perform(get("/parcel/" + trackingID))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn();

        TrackingInformation trackingInformation2 = gSonInstance.fromJson(info2.getResponse().getContentAsString(), TrackingInformation.class);
        assertEquals(2, trackingInformation2.getFutureHops().size());
        assertEquals(1, trackingInformation2.getVisitedHops().size());
        assertEquals(INTRUCKDELIVERY, trackingInformation2.getState());

        // update parcel
        mockMvc.perform(post("/parcel/" + trackingID + "/reportHop/" + trackingInformation2.getFutureHops().get(0).getCode()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        // get info
        MvcResult info3 = mockMvc.perform(get("/parcel/" + trackingID))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn();

        TrackingInformation trackingInformation3 = gSonInstance.fromJson(info3.getResponse().getContentAsString(), TrackingInformation.class);
        assertEquals(1, trackingInformation3.getFutureHops().size());
        assertEquals(2, trackingInformation3.getVisitedHops().size());
        assertEquals(INTRANSPORT, trackingInformation3.getState());

        // report delivery
        mockMvc.perform(post("/parcel/" + trackingID + "/reportDelivery/"))
                .andExpect(status().is2xxSuccessful());

        MvcResult info4 = mockMvc.perform(get("/parcel/" + trackingID))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn();

        TrackingInformation trackingInformation4 = gSonInstance.fromJson(info4.getResponse().getContentAsString(), TrackingInformation.class);
        assertEquals(DELIVERED, trackingInformation4.getState());
    }

    static String getResourceFileAsString(String fileName) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}
