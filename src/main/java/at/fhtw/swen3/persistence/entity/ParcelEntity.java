package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Parcel")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"recipient", "sender"})
@EqualsAndHashCode(exclude = {"recipient", "sender"})
public class ParcelEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    //Parcel
    @PositiveOrZero
    @Column
    private Float weight;
    @Valid
    @Column
    @OneToOne
    @JoinColumn(name="fk_recipient")
    private Recipient recipient;
    @NotNull(message = "Sender ID cannot be null")
    @OneToOne
    @JoinColumn(name="fk_sender")
    private Recipient sender;

    //NewParcelInfo
    //@Pattern(regexp = "^[A-Z/d]{9}$")
    //private String trackingId;

    //TrackingInformation
    //@NotBlank
    //private String value;
    //@NotNull(message = "State cannot be null")
    //private TrackingInformation.StateEnum state;
    //@Valid
    //private List<HopArrivalEntity> visitedHops = new ArrayList<>();
    //@Valid
    //private List<HopArrivalEntity> futureHops = new ArrayList<>();

}
