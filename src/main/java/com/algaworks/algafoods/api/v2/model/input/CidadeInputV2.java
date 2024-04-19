package com.algaworks.algafoods.api.v2.model.input;

import com.algaworks.algafoods.api.v1.model.input.EstadoIdInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeInputV2 {

    @NotBlank
    private String nomeCidade;
    @NotNull
    private Long idEstado;
}
