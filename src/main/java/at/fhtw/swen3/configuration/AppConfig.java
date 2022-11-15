package at.fhtw.swen3.configuration;

import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import at.fhtw.swen3.services.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ParcelServiceImpl parcelService(Validator validator, RecipientRepository recipientRepository, ParcelRepository parcelRepository) {
        return new ParcelServiceImpl(validator, recipientRepository, parcelRepository);
    }

    @Bean
    public WarehouseServiceImpl warehouseService(Validator validator, WarehouseRepository warehouseRepository) {
        return new WarehouseServiceImpl(validator, warehouseRepository);
    }
}
