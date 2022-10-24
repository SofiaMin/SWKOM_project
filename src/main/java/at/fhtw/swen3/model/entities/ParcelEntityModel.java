package at.fhtw.swen3.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Parcel")
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"recipient", "sender"})
@EqualsAndHashCode(exclude = {"recipient", "sender"})
public class ParcelEntityModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private Float weight;
    @Column
    @OneToOne
    @JoinColumn(name="fk_recipient")
    private RecipientEntityModel recipient;
    @OneToOne
    @JoinColumn(name="fk_sender")
    private RecipientEntityModel sender;
}
