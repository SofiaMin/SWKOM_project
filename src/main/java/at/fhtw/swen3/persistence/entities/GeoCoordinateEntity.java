package at.fhtw.swen3.persistence.entities;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Double lat;
    private Double lon;
}
