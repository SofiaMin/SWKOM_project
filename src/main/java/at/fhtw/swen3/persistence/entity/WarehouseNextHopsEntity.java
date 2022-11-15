package at.fhtw.swen3.persistence.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transferwarehouse")
@Getter
@Setter

public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer traveltimeMins;
    @OneToOne
    private HopEntity hopEntity;
}

