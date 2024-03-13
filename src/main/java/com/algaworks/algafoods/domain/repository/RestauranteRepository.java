package com.algaworks.algafoods.domain.repository;

import com.algaworks.algafoods.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query("from Restaurante r join fetch r.cozinha")
    List<Restaurante> findAll();

}
