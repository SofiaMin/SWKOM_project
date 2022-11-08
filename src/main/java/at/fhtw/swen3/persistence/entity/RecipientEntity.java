package at.fhtw.swen3.persistence.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity(name = "Recipient")
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Pattern(regexp = "^[[:upper:]][[:lower:]]*ß*[[:lower:]]*\\/*-*\\d*\\/*[[:lower:]]*$")
    @Column
    private String name;

    @Pattern(regexp = "^[[:upper:]][[:lower:]]*ß*[[:lower:]]*\\s\\d+\\/*[[:alpha:]]*\\d*\\/*\\d*$")
    @Column
    private String street;

    @Pattern(regexp = "^A-\\d\\d\\d\\d$")
    @Column
    private String postalCode;

    @Pattern(regexp = "^[[:upper:]][[:lower:]]*ß*[[:lower:]]*\\/*-*\\d*\\/*[[:lower:]]*$")
    @Column
    private String city;

    @Pattern(regexp = "[ÖÄÜA-Z][[:lower:]]+$")
    @Column
    private String country;


}
