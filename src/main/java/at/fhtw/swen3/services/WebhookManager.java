package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.TrackingInformation;

public interface WebhookManager {
    void notifySubscribers(String trackingID, TrackingInformation trackingInformation);

    void clearSubscribers(String trackingId);

    void createSubscriber(String trackingID, String subscriberID);
}
