package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafoods.domain.model.Produto;
import com.algaworks.algafoods.domain.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long restauranteId,Long produtoId){
        return produtoRepository.findById(restauranteId,produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }
}
