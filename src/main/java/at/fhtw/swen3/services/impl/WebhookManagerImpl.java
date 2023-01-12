package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.SubscriptionEntity;
import at.fhtw.swen3.persistence.repositories.WebhookRepository;
import at.fhtw.swen3.services.WebhookManager;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebhookManagerImpl implements WebhookManager {

    private final WebhookRepository webhookRepository;
    private final KafkaTemplate<String, TrackingInformation> kafkaTemplate;

    public void notifySubscribers(String trackingID, TrackingInformation trackingInformation) {
        webhookRepository.findAllByTrackingID(trackingID).stream().parallel()
                        .forEach(e -> kafkaTemplate.send("tracking", e.getSubscriberID(), trackingInformation));
    }

    @Override
    public void clearSubscribers(String trackingId) {
        webhookRepository.deleteAllByTrackingID(trackingId);
    }

    @Override
    public void createSubscriber(String trackingID, String subscriberID) {
        webhookRepository.save(SubscriptionEntity.builder().subscriberID(subscriberID).trackingID(trackingID).build());
    }
}
