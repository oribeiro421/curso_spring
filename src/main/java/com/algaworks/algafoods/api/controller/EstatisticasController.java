package com.algaworks.algafoods.api.controller;

import com.algaworks.algafoods.domain.filter.VendaDiariaFilter;
import com.algaworks.algafoods.domain.model.dto.VendaDiaria;
import com.algaworks.algafoods.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

    @Autowired
    private VendaQueryService vendaQueryService;

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter,
               @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){
        return vendaQueryService.consultarVendasDiarias(filter, timeOffset);
    }

}
