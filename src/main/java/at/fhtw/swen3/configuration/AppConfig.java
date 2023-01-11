package at.fhtw.swen3.configuration;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.OSMEncodingProxy;
import at.fhtw.swen3.persistence.repositories.GeoCoordinateRepository;
import at.fhtw.swen3.persistence.repositories.HopArrivalRepository;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.RecipientRepository;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseNextHopsRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.TrackingService;
import at.fhtw.swen3.services.WebhookManager;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.TrackingServiceImpl;
import at.fhtw.swen3.util.UUIDGenerator;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import at.fhtw.swen3.services.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class AppConfig {
    @Bean
    public ParcelServiceImpl parcelService(Validator validator, RecipientRepository recipientRepository,
                                           ParcelRepository parcelRepository,
                                           TrackingService trackingService, HopRepository hopRepository,
                                           WebhookManager webhookManager) {
        return new ParcelServiceImpl(validator, recipientRepository, parcelRepository, trackingService,
                hopRepository, webhookManager);
    }

    @Bean
    public WarehouseServiceImpl warehouseService(Validator validator, WarehouseRepository warehouseRepository,
                                                 GeoCoordinateRepository geoCoordinateRepository,
                                                 WarehouseNextHopsRepository warehouseNextHopsRepository,
                                                 HopRepository hopRepository) {
        return new WarehouseServiceImpl(validator, warehouseRepository, geoCoordinateRepository,
                warehouseNextHopsRepository, hopRepository);
    }

    @Bean
    public TrackingServiceImpl trackingService(WarehouseRepository warehouseRepository, HopArrivalRepository hopArrivalRepository,
                                               ParcelRepository parcelRepository, TruckRepository truckRepository, WarehouseNextHopsRepository warehouseNextHopsRepository) {
        return new TrackingServiceImpl(warehouseRepository, hopArrivalRepository, parcelRepository,
                new OSMEncodingProxy(), truckRepository, warehouseNextHopsRepository);
    }
}
