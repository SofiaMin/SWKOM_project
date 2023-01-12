package at.fhtw.swen3.persistence.entities;
import java.util.ArrayList;
import java.util.List;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warehouse")
@Getter
@Setter
public class WarehouseEntity extends HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer level;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();
}


