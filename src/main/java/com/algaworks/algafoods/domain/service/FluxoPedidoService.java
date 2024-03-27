package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.NegocioException;
import com.algaworks.algafoods.domain.model.Pedido;
import com.algaworks.algafoods.domain.model.StatusPedido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Transactional
    public void confirmar(String codigoPedido){
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.confirmar();
    }
    @Transactional
    public void entregar(String codigoPedido){
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.entregar();
    }
    @Transactional
    public void cancelar(String codigoPedido){
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        pedido.cancelar();
    }

}
