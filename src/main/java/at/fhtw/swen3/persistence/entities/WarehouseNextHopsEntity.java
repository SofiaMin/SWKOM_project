package at.fhtw.swen3.persistence.entities;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@Entity
@Table(name = "warehousehops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer traveltimeMins;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private HopEntity hopEntity;
}

