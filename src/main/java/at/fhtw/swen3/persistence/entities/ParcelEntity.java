package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.TrackingInformation;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.hibernate.annotations.Where;

@Builder
@Entity
@Table(name = "parcel")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @DecimalMin(value = "0.0", message = "Minimum weight should be greater than 0.0")
    private float weight;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private RecipientEntity recipient;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private RecipientEntity sender;
    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;
    private TrackingInformation.StateEnum state;
    @OneToMany(mappedBy="fk_parcel", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @Where(clause = "visited = true")
    @NotNull(message = "Visited Hops cannot be null.")
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();
    @OneToMany(mappedBy="fk_parcel", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @Where(clause = "visited = false")
    @NotNull(message = "Future hops cannot be null.")
    private List<HopArrivalEntity> futureHops = new ArrayList<>();

    /**
     * Constructor-like functionality to build an Object
     */
    public ParcelEntity weight(Float weight) {
        this.weight = weight;
        return this;
    }

    public ParcelEntity recipient(RecipientEntity recipientEntity) {
        this.recipient = recipientEntity;
        return this;
    }

    public ParcelEntity sender(RecipientEntity recipientEntity) {
        this.sender = recipientEntity;
        return this;
    }

    public ParcelEntity trackingId(String trackingId) {
        this.trackingId = trackingId;
        return this;
    }

    public ParcelEntity state(TrackingInformation.StateEnum state) {
        this.state = state;
        return this;
    }
}
