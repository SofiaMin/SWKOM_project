package at.fhtw.swen3.persistence.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Table(name = "error")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String errorMessage;
}