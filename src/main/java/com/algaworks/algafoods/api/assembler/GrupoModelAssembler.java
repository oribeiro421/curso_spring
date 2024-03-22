package com.algaworks.algafoods.api.assembler;

import com.algaworks.algafoods.api.model.GrupoModel;
import com.algaworks.algafoods.api.model.RestauranteModel;
import com.algaworks.algafoods.domain.model.Grupo;
import com.algaworks.algafoods.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoModel toModel(Grupo grupo) {
        return modelMapper.map(grupo, GrupoModel.class);
    }

    public List<GrupoModel> toCollectionModel(List<Grupo> grupo) {
        return grupo.stream()
                .map(grupos -> toModel(grupos))
                .collect(Collectors.toList());
    }
}
