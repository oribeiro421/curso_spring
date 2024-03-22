package com.algaworks.algafoods.api.model;

import com.algaworks.algafoods.domain.model.Permissao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GrupoModel {

    private Long id;
    private String nome;
}
