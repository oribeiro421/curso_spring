package com.algaworks.algafoods.domain.model;

import jakarta.persistence.*;
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
    @Column(nullable = false)
    private String nome;
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "pagamento_id"))
    private List<FormaPagamento> formaPagamentos;
    @Embedded
    private Endereco endereco;
    private Boolean ativo = Boolean.TRUE;
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produto;

    public void ativar(){
        setAtivo(true);
    }
    public void inativar(){
        setAtivo(false);
    }
}
