package com.algaworks.algafoods.domain.repository;

import com.algaworks.algafoods.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {

    Optional<Pedido> findByCodigo(String codigo);
    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
    List<Pedido> findAll();

    @Query("select case when count(1) > 0 then true else false end" +
            "from Pedido ped" +
            "join ped.restaurante rest" +
            "join rest.responsaveis resp" +
            "where ped.codigo = :codigoPedido " +
            "and resp.id = :usuarioId")
    boolean isPedidoGerenciadoPor(String codigoPedido, Long usuarioId);
}
