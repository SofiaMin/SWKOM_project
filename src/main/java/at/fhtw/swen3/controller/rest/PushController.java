package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.services.WebhookManager;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PushController {

    private final WebhookManager webhookManager;
    @GetMapping("/subscribe")
    public void subscribeOnParcel(@RequestParam String trackingID, @RequestParam String subscriberID) {
        webhookManager.createSubscriber(trackingID, subscriberID);
    }
}
