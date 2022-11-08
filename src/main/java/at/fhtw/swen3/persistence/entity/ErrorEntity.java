package at.fhtw.swen3.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "error")
@Getter
@Setter
public class ErrorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String errorMessage;
}