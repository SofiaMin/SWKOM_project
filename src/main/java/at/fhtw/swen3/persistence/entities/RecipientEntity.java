package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipient")
@Getter
@Setter
public class RecipientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Pattern(regexp = "^[A-ZÄÖÜ][a-zäöüß]+\\.?\\s?([A-ZÄÖÜ][a-zäöüß]+)*$")
    private String name;
    @Pattern(regexp = "[A-Za-zÄÖÜäöüß]+\\s[0-9A-Za-zÄÖÜäöüß/\\s]+")
    private String street;
    @Pattern(regexp = "^[AD]-\\d{4}$")
    private String postalCode;
    @Pattern(regexp = "^[A-ZÄÖÜ][a-zäöüß]+\\.?\\s?([A-ZÄÖÜ][a-zäöüß]+)*$")
    private String city;
    private String country;

    /**
     * Constructor-like functionality to build an Object
     */
    public RecipientEntity name(String name) {
        this.name = name;
        return this;
    }

    public RecipientEntity street(String street) {
        this.street = street;
        return this;
    }

    public RecipientEntity postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public RecipientEntity city(String city) {
        this.city = city;
        return this;
    }

    public RecipientEntity country(String country) {
        this.country = country;
        return this;
    }
}
