package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name = "truck")
@Getter
@Setter
public class TruckEntity extends HopEntity{
    private String regionGeoJson;
    private String numberPlate;
}
