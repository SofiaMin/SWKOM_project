package at.fhtw.swen3.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "geo_coordinate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeoCoordinateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Double lat;
    private Double lon;
}
