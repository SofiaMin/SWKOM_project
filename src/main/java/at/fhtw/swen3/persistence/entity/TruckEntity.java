package at.fhtw.swen3.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Truck")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TruckEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private String regionGeoJson;
    @Column
    private String numberPlate;
}
