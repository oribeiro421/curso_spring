package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.model.FotoProduto;
import com.algaworks.algafoods.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public FotoProduto salvar(FotoProduto foto){
        return produtoRepository.save(foto);
    }
}
