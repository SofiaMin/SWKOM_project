package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.OpenApiGeneratorApplication;
import at.fhtw.swen3.persistence.entity.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.Recipient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OpenApiGeneratorApplication.class)
class HopArrivalMapperTest {

    @Test
    void TestHopArrivalDtoToEntity() {

        HopArrival hop = new HopArrival();
        hop.setCode("WTTA012");
        hop.setDescription("Truck in Oberlaa Stadt");

        HopArrivalEntity hopEntity = HopArrivalMapper.INSTANCE.dtoToEntity(hop);

        assertEquals(hop.getCode(), hopEntity.getCode());
        assertEquals(hop.getDescription(), hopEntity.getDescription());

    }

    @Test
    void TestHopArrivalEntityToDto() {

        HopArrivalEntity hopArrivalEntity = new HopArrivalEntity();
        hopArrivalEntity.setCode("WTTA012");
        hopArrivalEntity.setDescription("Truck in Oberlaa Stadt");

        HopArrival hop = HopArrivalMapper.INSTANCE.INSTANCE.entityToDto(hopArrivalEntity);

        assertEquals(hopArrivalEntity.getCode(), hop.getCode());
        assertEquals(hopArrivalEntity.getDescription(), hop.getDescription());

    }

}