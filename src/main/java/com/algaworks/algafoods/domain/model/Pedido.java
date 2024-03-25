package com.algaworks.algafoods.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    @CreationTimestamp
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataEntrega;
    @Embedded
    private Endereco enderecoEntrega;
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;
    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
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
