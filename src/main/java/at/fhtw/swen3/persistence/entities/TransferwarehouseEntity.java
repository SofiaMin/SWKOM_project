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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String regionGeoJson;
    private String logisticsPartner;
    private String logisticsPartnerUrl;
}
