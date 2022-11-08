package at.fhtw.swen3.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "NewParcelInfo")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewParcelInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;
    @Column
    private String trackingId;
}
