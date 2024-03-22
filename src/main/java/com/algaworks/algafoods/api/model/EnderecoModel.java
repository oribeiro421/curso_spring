package com.algaworks.algafoods.api.model;

import com.algaworks.algafoods.domain.model.Cidade;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoModel {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumoModel cidade;
}
