package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebhookRepository extends JpaRepository<SubscriptionEntity, Long> {
    List<SubscriptionEntity> findAllByTrackingID(String trackingID);
    void deleteAllByTrackingID(String trackingID);
}
