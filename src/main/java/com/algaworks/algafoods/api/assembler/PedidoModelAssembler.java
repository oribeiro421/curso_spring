package com.algaworks.algafoods.api.assembler;

import com.algaworks.algafoods.api.model.GrupoModel;
import com.algaworks.algafoods.api.model.PedidoModel;
import com.algaworks.algafoods.domain.model.Grupo;
import com.algaworks.algafoods.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModel toModel(Pedido pedido) {
        return modelMapper.map(pedido, PedidoModel.class);
    }

    public List<PedidoModel> toCollectionModel(List<Pedido> pedido) {
        return pedido.stream()
                .map(pedidos -> toModel(pedidos))
                .collect(Collectors.toList());
    }
}
