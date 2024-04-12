package com.algaworks.algafoods.domain.event;

import com.algaworks.algafoods.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

    private Pedido pedido;
}
