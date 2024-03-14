package com.algaworks.algafoods.domain.repository;

import com.algaworks.algafoods.domain.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Long> {
}
