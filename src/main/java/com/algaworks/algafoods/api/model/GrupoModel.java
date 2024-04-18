package com.algaworks.algafoods.api.model;

import com.algaworks.algafoods.domain.model.Permissao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Setter
@Getter
public class GrupoModel extends RepresentationModel<GrupoModel> {

    private Long id;
    private String nome;
}
