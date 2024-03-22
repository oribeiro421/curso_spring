package com.algaworks.algafoods.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeIdInput {

    @NotNull
    private Long id;
}
