package at.fhtw.swen3.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Hop")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"locationCoordinates"})
@EqualsAndHashCode(exclude = {"locationCoordinates"})
public class HopEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private String hopType;
    @Column
    private String code;
    @Column
    private String description;
    @Column
    private Integer processingDelayMins;
    @Column
    private String locationName;
    @Column
    @OneToOne
    @JoinColumn(name="fk_locationCoordinates")
    private GeoCoordinateEntity locationCoordinates;
}
