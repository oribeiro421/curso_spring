package com.algaworks.algafoods.api.model;

import com.algaworks.algafoods.api.model.CozinhaModel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class RestauranteModel {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaModel cozinha;
}
