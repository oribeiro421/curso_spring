package com.algaworks.algafoods.domain.model;

import com.algaworks.algafoods.core.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.ConvertGroup;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @NotNull
    @PositiveOrZero
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;
   // @JsonIgnore
    @Valid
    @ConvertGroup(to = Groups.CozinhaId.class)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;
    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;
    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "pagamento_id"))
    private List<FormaPagamento> formaPagamentos;
    @JsonIgnore
    @Embedded
    private Endereco endereco;
    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produto;
}
