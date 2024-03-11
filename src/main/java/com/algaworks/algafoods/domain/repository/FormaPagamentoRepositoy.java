package com.algaworks.algafoods.domain.repository;

import com.algaworks.algafoods.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepositoy extends JpaRepository<FormaPagamento, Long> {
}
