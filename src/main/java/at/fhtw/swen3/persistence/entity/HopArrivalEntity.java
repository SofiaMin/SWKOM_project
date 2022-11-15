package at.fhtw.swen3.persistence.entity;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Entity
@Table(name = "hop_arrival")
@Getter
@Setter
@NoArgsConstructor
public class HopArrivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Pattern(regexp = "^[A-Za-zÄÖÜäöüß0-9\\s\\-]+$")
    private String code;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private OffsetDateTime dateTime;
    @ManyToOne
    @JoinColumn(name="fk_parcel")
    private ParcelEntity fk_parcel;
}

