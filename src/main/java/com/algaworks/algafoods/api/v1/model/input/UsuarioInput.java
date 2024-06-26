package com.algaworks.algafoods.api.v1.model.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioInput {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
}
