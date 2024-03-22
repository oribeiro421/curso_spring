package com.algaworks.algafoods.api.assembler;

import com.algaworks.algafoods.api.model.input.EstadoInput;
import com.algaworks.algafoods.api.model.input.UsuarioInput;
import com.algaworks.algafoods.domain.model.Estado;
import com.algaworks.algafoods.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainObject(UsuarioInput usuarioInput) {
        return modelMapper.map(usuarioInput, Usuario.class);
    }

    public void copyToDomainObject(UsuarioInput usuarioInput, Usuario usuario) {
        modelMapper.map(usuarioInput, usuario);
    }
}
