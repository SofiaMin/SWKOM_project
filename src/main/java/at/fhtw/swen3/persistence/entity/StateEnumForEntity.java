package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.TrackingInformation;

public enum StateEnumForEntity {
    PICKUP("Pickup"),

    INTRANSPORT("InTransport"),

    INTRUCKDELIVERY("InTruckDelivery"),

    TRANSFERRED("Transferred"),

    DELIVERED("Delivered");

    private String value;

    StateEnumForEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static StateEnumForEntity fromValue(String value) {
        for (StateEnumForEntity b : StateEnumForEntity.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

}
