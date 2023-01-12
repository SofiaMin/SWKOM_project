package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "truck")
@Getter
@Setter
public class TruckEntity extends HopEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(columnDefinition="TEXT")
    private String regionGeoJson;
    @Column(columnDefinition="TEXT")
    private String numberPlate;
}

