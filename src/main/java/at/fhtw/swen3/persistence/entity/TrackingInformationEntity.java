package at.fhtw.swen3.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "TrackingInformation")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"visitedHops", "futureHops"})
@EqualsAndHashCode(exclude = {"visitedHops", "futureHops"})
public class TrackingInformationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    @Enumerated(EnumType.STRING)
    private StateEnumForEntity state;
    @Column
    @OneToMany(mappedBy = "visitedHop")
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();
    @Column
    @OneToMany(mappedBy = "futureHop")
    private List<HopArrivalEntity> futureHops = new ArrayList<>();
}
