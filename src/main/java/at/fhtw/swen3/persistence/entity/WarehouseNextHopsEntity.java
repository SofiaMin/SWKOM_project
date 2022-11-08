package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.Hop;
import lombok.*;

import javax.persistence.*;
@Entity(name = "WarehouseNextHops")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"hop", "warehouse"})
@EqualsAndHashCode(exclude = {"hop", "warehouse"})
public class WarehouseNextHopsEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private Integer traveltimeMins;
    @Column
    @OneToOne
    @JoinColumn(name="fk_hop")
    private Hop hop;
    @Column
    @ManyToOne
    private WarehouseEntity warehouse;
}
