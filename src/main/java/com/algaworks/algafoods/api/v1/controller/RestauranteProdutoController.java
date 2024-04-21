package com.algaworks.algafoods.api.v1.controller;

import com.algaworks.algafoods.api.v1.AlgaLinks;
import com.algaworks.algafoods.api.v1.assembler.ProdutoInputDisassembler;
import com.algaworks.algafoods.api.v1.assembler.ProdutoModelAssembler;
import com.algaworks.algafoods.api.v1.model.ProdutoModel;
import com.algaworks.algafoods.api.v1.model.input.ProdutoInput;
import com.algaworks.algafoods.domain.model.Produto;
import com.algaworks.algafoods.domain.model.Restaurante;
import com.algaworks.algafoods.domain.repository.ProdutoRepository;
import com.algaworks.algafoods.domain.service.ProdutoService;
import com.algaworks.algafoods.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private ProdutoInputDisassembler produtoInputDisassembler;
    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;
    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<ProdutoModel> listar(@PathVariable Long restauranteId,
                                                @RequestParam(required = false) Boolean incluirInativos){
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
        List<Produto> todosProdutos;

        if ((incluirInativos)){
            todosProdutos = produtoRepository.findTodosByRestaurante(restaurante);
        }else {
            todosProdutos = produtoRepository.findAtivosByRestaurante(restaurante);
        }
        return produtoModelAssembler.toCollectionModel(todosProdutos).add(algaLinks.linkToProdutos(restauranteId));
    }
    @GetMapping("/{produtoId}")
    public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId){
        Produto produto = produtoService.buscarOuFalhar(restauranteId,produtoId);

        return produtoModelAssembler.toModel(produto);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel adicionar(@PathVariable Long restauranteId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
        produto.setRestaurante(restaurante);

        produto = produtoService.salvar(produto);

        return produtoModelAssembler.toModel(produto);
    }

    @PutMapping("/{produtoId}")
    public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId,
                                  @RequestBody @Valid ProdutoInput produtoInput) {
        Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);

        produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

        produtoAtual = produtoService.salvar(produtoAtual);

        return produtoModelAssembler.toModel(produtoAtual);
    }

}
