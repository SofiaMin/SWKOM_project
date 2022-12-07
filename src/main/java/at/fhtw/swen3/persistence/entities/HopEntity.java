package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "hop")
@Getter
@Setter
public class HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String hopType;
    //@Pattern(regexp = "^[A-Za-zÄÖÜäöüß0-9\\s\\-]+$")
    private String code;
    private String description;
    private Integer processingDelayMins;
    private String locationName;
    @OneToOne
    @NotNull(message = "GeoCoordinates cannot be null")
    private GeoCoordinateEntity locationCoordinates;


}
