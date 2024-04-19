package com.algaworks.algafoods.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
@Getter
@Setter
public class ProdutoModel extends RepresentationModel<ProdutoModel> {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean ativo;
}
