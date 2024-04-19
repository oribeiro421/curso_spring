package com.algaworks.algafoods.api.v1.controller;

import com.algaworks.algafoods.api.v1.assembler.PermissaoInputDissasembler;
import com.algaworks.algafoods.api.v1.assembler.PermissaoModelAssembler;
import com.algaworks.algafoods.api.v1.model.PermissaoModel;
import com.algaworks.algafoods.api.v1.model.input.PermissaoInput;
import com.algaworks.algafoods.domain.model.Permissao;
import com.algaworks.algafoods.domain.repository.PermissaoRepository;
import com.algaworks.algafoods.domain.service.PermissaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoRepository permissaoRepository;
    @Autowired
    private PermissaoService permissaoService;
    @Autowired
    private PermissaoModelAssembler permissaoModelAssembler;
    @Autowired
    private PermissaoInputDissasembler permissaoInputDissasembler;

    @GetMapping
    public CollectionModel<PermissaoModel> listar(){
        return permissaoModelAssembler.toCollectionModel(permissaoRepository.findAll());
    }
    @GetMapping("/{permissaoId}")
    public PermissaoModel buscar(@PathVariable Long permissaoId){
        Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
        return permissaoModelAssembler.toModel(permissao);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PermissaoModel adicionar(@RequestBody @Valid PermissaoInput permissaoInput){
        Permissao permissao = permissaoInputDissasembler.toDomainObject(permissaoInput);
        return permissaoModelAssembler.toModel(permissaoService.salvar(permissao));
    }
    @PutMapping("/{permissaoId}")
    public PermissaoModel atualizar(@PathVariable Long permissaoId, @RequestBody PermissaoInput permissaoInput){
        Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);

        permissaoInputDissasembler.copyToDomainObject(permissaoInput, permissao);

        return permissaoModelAssembler.toModel(permissaoService.salvar(permissao));
    }
    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long permissaoId) {
        permissaoService.excluir(permissaoId);
    }

}
