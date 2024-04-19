package com.algaworks.algafoods.api.v1.assembler;

import com.algaworks.algafoods.api.v1.model.input.PermissaoInput;
import com.algaworks.algafoods.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PermissaoInputDissasembler {

    @Autowired
    private ModelMapper modelMapper;

    public Permissao toDomainObject(PermissaoInput permissaoInput){
        return modelMapper.map(permissaoInput, Permissao.class);
    }

    public void copyToDomainObject(PermissaoInput permissaoInput, Permissao permissao){

        modelMapper.map(permissaoInput, permissao);
    }
}
