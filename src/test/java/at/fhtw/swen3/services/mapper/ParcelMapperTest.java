package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.persistence.entity.ParcelEntity;
import at.fhtw.swen3.services.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OpenApiGeneratorApplication.class)
class ParcelMapperTest {

    @Test
    void testParcelDtoToEntity() {
        Recipient sender = new Recipient();
        sender.setStreet("Zieglergasse 21-23");
        sender.setPostalCode("A-1070");
        sender.setCity("Wien");
        sender.setCountry("Österreich");
        sender.setName("GTVS Neubau");

        Recipient recipient = new Recipient();
        recipient.setName("Folic");
        recipient.setStreet("Neubaugürtel 52/14");
        recipient.setCity("Wien");
        recipient.setCountry("Österreich");
        recipient.setPostalCode("A-1070");

        HopArrival visitedHop = new HopArrival();
        visitedHop.setCode("WTTA012");
        visitedHop.setDescription("Truck in Oberlaa Stadt");

        HopArrival futureHop = new HopArrival();
        futureHop.setCode("WTTA014");
        futureHop.setDescription("Truck in Altmannsdorf");

        Parcel parcel = new Parcel();
        parcel.setSender(sender);
        parcel.setRecipient(recipient);
        parcel.setWeight(5.0F);

        NewParcelInfo newParcelInfo = new NewParcelInfo();
        newParcelInfo.setTrackingId("123456");

        LinkedList<HopArrival> visitedHops = new LinkedList<>();
        visitedHops.add(visitedHop);

        LinkedList<HopArrival> futureHops = new LinkedList<>();
        visitedHops.add(futureHop);

        TrackingInformation trackingInformation = new TrackingInformation();
        trackingInformation.setState(TrackingInformation.StateEnum.PICKUP);
        trackingInformation.setFutureHops(futureHops);
        trackingInformation.setVisitedHops(visitedHops);

        ParcelEntity parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel, newParcelInfo, trackingInformation);
        assertEquals(parcel.getWeight(), parcelEntity.getWeight());

    }

}