package com.algaworks.algafoods.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiaria {

    private Date data;
    private Long totalVendas;
    private BigDecimal totalFaturado;
}
