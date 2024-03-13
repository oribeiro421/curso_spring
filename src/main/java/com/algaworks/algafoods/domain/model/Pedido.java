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
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal sbuTotal;
    @Column(nullable = false)
    private BigDecimal taxaFrete;
    @Column(nullable = false)
    private BigDecimal valorTotal;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataEntrega;
    @Embedded
    private Endereco enderecoEntrega;
    private StatusPedido statusPedido;
    @ManyToOne
    @JoinColumn(name = "usuario_client_id", nullable = false)
    private Usuario cliente;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;
    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> items;

}
