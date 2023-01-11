package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "transferwarehouse")
@Getter
@Setter
public class TransferwarehouseEntity extends HopEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(columnDefinition="TEXT")
    private String regionGeoJson;
    @Column(columnDefinition="TEXT")
    private String logisticsPartner;
    @Column(columnDefinition="TEXT")
    private String logisticsPartnerUrl;
}
