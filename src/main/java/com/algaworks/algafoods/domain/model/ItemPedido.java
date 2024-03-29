package com.algaworks.algafoods.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer quantidade;
    @Column(nullable = false)
    private BigDecimal precoUnitario;
    @Column(nullable = false)
    private BigDecimal precoTotal;
    private String observacao;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;

    public void calcularPrecoTotal(){
        BigDecimal precoUnitario = this.precoUnitario;
        Integer quantidade = this.quantidade;

        if (precoUnitario == null){
            precoUnitario = BigDecimal.ZERO;
        }
        if (quantidade == null){
            quantidade = 0;
        }

        this.setPrecoTotal(precoUnitario.multiply(BigDecimal.valueOf(quantidade)));
    }

}
