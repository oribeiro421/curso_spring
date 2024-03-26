package com.algaworks.algafoods.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formaPagamentos;
    @Embedded
    private Endereco endereco;
    private Boolean ativo = Boolean.TRUE;
    private Boolean aberto = Boolean.FALSE;
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produto;
    @ManyToMany
    @JoinTable(name = "restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> responsaveis;

    public void ativar(){
        setAtivo(true);
    }
    public void inativar(){
        setAtivo(false);
    }
    public void abrir(){
        setAberto(true);
    }
    public void fechar(){
        setAberto(false);
    }
    public boolean removerFormaPagamento(FormaPagamento formaPagamento){
        return getFormaPagamentos().remove(formaPagamento);
    }
    public boolean adicioanarFormaPagamento(FormaPagamento formaPagamento){
        return getFormaPagamentos().add(formaPagamento);
    }
    public boolean removerResponsaveis(Usuario usuario){
        return getResponsaveis().remove(usuario);
    }
    public boolean adicioanarResponsaveis(Usuario usuario){
        return getResponsaveis().add(usuario);
    }
    public boolean aceitaFormaPagamento(FormaPagamento formaPagamento){
        return getFormaPagamentos().contains(formaPagamento);
    }
    public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento){
        return !aceitaFormaPagamento(formaPagamento);
    }
}
