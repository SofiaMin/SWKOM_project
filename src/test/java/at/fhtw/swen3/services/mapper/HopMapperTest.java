package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entity.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class HopMapperTest {
    private static final Logger log = LoggerFactory.getLogger(HopMapperTest.class.getSimpleName());

    @Test
    void dtoToEntity() {
        log.info("Test dtoToEntity()");
        Hop hop = new Hop().hopType("Paketstation").code("pk").description("a description...");
        HopEntity hopEntity = HopMapper.INSTANCE.dtoToEntity(hop);
        assertEquals(hop.getHopType(), hopEntity.getHopType());
        assertEquals(hop.getCode(), hopEntity.getCode());
        assertEquals(hop.getDescription(), hopEntity.getDescription());
    }
}
