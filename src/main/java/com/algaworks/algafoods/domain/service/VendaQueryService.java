package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.filter.VendaDiariaFilter;
import com.algaworks.algafoods.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter, String timeOffset);

}
