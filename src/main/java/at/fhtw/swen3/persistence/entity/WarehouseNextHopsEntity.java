package at.fhtw.swen3.persistence.entity;

import javax.persistence.*;

import lombok.*;

@Builder
@Entity
@Table(name = "transferwarehouse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer traveltimeMins;
    @OneToOne
    private HopEntity hopEntity;
}

