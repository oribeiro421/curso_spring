package com.algaworks.algafoods.api.v1.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
public class GrupoModel extends RepresentationModel<GrupoModel> {

    private Long id;
    private String nome;
}
