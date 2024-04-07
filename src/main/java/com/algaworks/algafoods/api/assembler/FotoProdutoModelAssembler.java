package com.algaworks.algafoods.api.assembler;

import com.algaworks.algafoods.api.model.FotoProdutoModel;
import com.algaworks.algafoods.api.model.ProdutoModel;
import com.algaworks.algafoods.domain.model.FotoProduto;
import com.algaworks.algafoods.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FotoProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FotoProdutoModel toModel(FotoProduto foto) {
        return modelMapper.map(foto, FotoProdutoModel.class);
    }

}
