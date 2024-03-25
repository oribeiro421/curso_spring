package com.algaworks.algafoods.api.assembler;

import com.algaworks.algafoods.api.model.PermissaoModel;
import com.algaworks.algafoods.api.model.ProdutoModel;
import com.algaworks.algafoods.domain.model.Permissao;
import com.algaworks.algafoods.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoModel toModel(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoModel.class);
    }

    public List<PermissaoModel> toCollectionModel(Collection<Permissao> permissao) {
        return permissao.stream()
                .map(permissoes -> toModel(permissoes))
                .collect(Collectors.toList());
    }
}
