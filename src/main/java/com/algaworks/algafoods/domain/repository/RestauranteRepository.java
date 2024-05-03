package com.algaworks.algafoods.domain.repository;

import com.algaworks.algafoods.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,
        RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {

    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();

    List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

    List<Restaurante> findTop2ByNomeContaining(String nome);

    int countByCozinhaId(Long cozinha);

    @Query("SELECT case when count(1) > 0 then true " +
            "else false end" +
            "FROM Restaurante rest" +
            "JOIN rest.responsaveis resp" +
            "WHERE rest.id = :restauranteId" +
            "AND resp.id = :usuarioId")
    boolean existsResponsavel(Long restauranteId, Long usuarioId);

}
