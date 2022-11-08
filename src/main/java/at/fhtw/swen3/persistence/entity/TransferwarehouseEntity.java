package at.fhtw.swen3.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Transferhouse")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TransferwarehouseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private String regionGeoJson;
    @Column
    private String logisticsPartner;
    @Column
    private String logisticsPartnerUrl;
}
