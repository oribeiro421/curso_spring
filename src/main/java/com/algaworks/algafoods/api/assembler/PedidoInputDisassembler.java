package com.algaworks.algafoods.api.assembler;

import com.algaworks.algafoods.api.model.input.FormaPagamentoInput;
import com.algaworks.algafoods.api.model.input.PedidoInput;
import com.algaworks.algafoods.domain.model.FormaPagamento;
import com.algaworks.algafoods.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Pedido toDomainObject(PedidoInput pedidoInput){
        return modelMapper.map(pedidoInput, Pedido.class);
    }

    public void copyToDomainObject(PedidoInput pedidoInput, Pedido pedido){

        modelMapper.map(pedidoInput, pedido);
    }
}
