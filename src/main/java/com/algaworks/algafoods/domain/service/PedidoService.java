package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoods.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafoods.domain.model.Pedido;
import com.algaworks.algafoods.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarOuFalhar(Long pedidoId){
        return pedidoRepository.findById(pedidoId).orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }
}
