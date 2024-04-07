package com.algaworks.algafoods.infrastructure.repository;

import com.algaworks.algafoods.domain.model.FotoProduto;
import com.algaworks.algafoods.domain.repository.ProdutoRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {
    @PersistenceContext
    private EntityManager manager;
    @Transactional
    @Override
    public FotoProduto save(FotoProduto fotoProduto) {
        return manager.merge(fotoProduto);
    }
}
