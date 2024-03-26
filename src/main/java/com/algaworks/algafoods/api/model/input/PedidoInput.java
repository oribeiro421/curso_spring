package com.algaworks.algafoods.api.model.input;

import com.algaworks.algafoods.domain.model.ItemPedido;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoInput {

    @Valid
    @NotNull
    private RestauranteIdInput restaurante;
    @Valid
    @NotNull
    private EnderecoInput enderecoEntrega;
    @Valid
    @NotNull
    private FormaPagamentoIdInput formaPagamento;
    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInput> itens;
}
