package com.algaworks.algafoods.api.v1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class PermissaoModel extends RepresentationModel<PermissaoModel> {

    private Long id;
    private String nome;
    private String descricao;
}
