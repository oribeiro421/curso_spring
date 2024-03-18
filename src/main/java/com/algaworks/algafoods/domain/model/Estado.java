package com.algaworks.algafoods.domain.model;

import com.algaworks.algafoods.core.Groups;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Estado {

    @NotNull(groups = Groups.EstadoId.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String nome;
}
