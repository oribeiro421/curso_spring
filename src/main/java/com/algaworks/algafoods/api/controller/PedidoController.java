package com.algaworks.algafoods.api.controller;

import com.algaworks.algafoods.api.assembler.PedidoModelAssembler;
import com.algaworks.algafoods.api.model.PedidoModel;
import com.algaworks.algafoods.domain.model.Pedido;
import com.algaworks.algafoods.domain.repository.PedidoRepository;
import com.algaworks.algafoods.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @GetMapping
    public List<PedidoModel> listar(){
        return pedidoModelAssembler.toCollectionModel(pedidoRepository.findAll());
    }
    @GetMapping("/{pedidoId}")
    public PedidoModel obter(@PathVariable Long pedidoId){
        Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
        return pedidoModelAssembler.toModel(pedido);
    }
}
