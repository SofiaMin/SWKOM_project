package at.fhtw.swen3.model.entities;


import javax.persistence.*;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Entity(name = "HopArrival")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"visitedHop", "futureHop"})
@EqualsAndHashCode(exclude = {"visitedHop", "futureHop"})
public class HopArrivalEntityModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private String code;
    @Column
    private String description;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dateTime;

    //@Column
    //@ManyToOne
    //private TrackingInformationEntityModel visitedHop;
    //@Column
    //@ManyToOne
    //private TrackingInformationEntityModel futureHop;
}
