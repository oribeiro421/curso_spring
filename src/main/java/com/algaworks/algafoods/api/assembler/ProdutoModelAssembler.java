package com.algaworks.algafoods.api.assembler;

import com.algaworks.algafoods.api.model.ProdutoModel;
import com.algaworks.algafoods.api.model.RestauranteModel;
import com.algaworks.algafoods.domain.model.Produto;
import com.algaworks.algafoods.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoModel toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> toCollectionModel(List<Produto> produto) {
        return produto.stream()
                .map(produtos -> toModel(produtos))
                .collect(Collectors.toList());
    }
}
