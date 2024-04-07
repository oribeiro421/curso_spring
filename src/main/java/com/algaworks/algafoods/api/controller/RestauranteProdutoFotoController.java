package com.algaworks.algafoods.api.controller;

import com.algaworks.algafoods.api.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafoods.api.model.FotoProdutoModel;
import com.algaworks.algafoods.api.model.input.FotoProdutoInput;
import com.algaworks.algafoods.domain.model.FotoProduto;
import com.algaworks.algafoods.domain.model.Produto;
import com.algaworks.algafoods.domain.service.FotoProdutoService;
import com.algaworks.algafoods.domain.service.ProdutoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FotoProdutoModelAssembler fotoProdutoModelAssembler;
    @Autowired
    private FotoProdutoService fotoProdutoService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                          @Valid FotoProdutoInput fotoProdutoInput){
        Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);

        MultipartFile arquivo = fotoProdutoInput.getArquivo();

        FotoProduto foto = new FotoProduto();

        foto.setProduto(produto);
        foto.setDescricao(fotoProdutoInput.getDescricao());
        foto.setContentType(arquivo.getContentType());
        foto.setTamanho(arquivo.getSize());
        foto.setNomeArquivo(arquivo.getOriginalFilename());

        FotoProduto fotoSalva = fotoProdutoService.salvar(foto);

        return fotoProdutoModelAssembler.toModel(fotoSalva);
    }
}