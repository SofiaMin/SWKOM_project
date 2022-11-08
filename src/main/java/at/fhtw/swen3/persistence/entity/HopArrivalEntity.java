package at.fhtw.swen3.persistence.entity;

import javax.persistence.*;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Entity(name = "HopArrival")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"visitedHop", "futureHop"})
@EqualsAndHashCode(exclude = {"visitedHop", "futureHop"})
public class HopArrivalEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    @Column
    private String code;

    @Pattern(regexp = "^[ÖÄÜA-Z][[:lower:]]+\\s\\d*-*\\d*$")
    @Column
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column
    private OffsetDateTime dateTime;

    @Column
    @ManyToOne
    private TrackingInformationEntity visitedHop;
    @Column
    @ManyToOne
    private TrackingInformationEntity futureHop;

}
