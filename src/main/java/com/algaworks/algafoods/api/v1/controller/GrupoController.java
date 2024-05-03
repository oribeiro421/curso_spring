package com.algaworks.algafoods.api.v1.controller;

import com.algaworks.algafoods.api.v1.assembler.GrupoInputDisassembler;
import com.algaworks.algafoods.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafoods.api.v1.model.GrupoModel;
import com.algaworks.algafoods.api.v1.model.input.GrupoInput;
import com.algaworks.algafoods.core.security.CheckSecurity;
import com.algaworks.algafoods.domain.model.Grupo;
import com.algaworks.algafoods.domain.repository.GrupoRepository;
import com.algaworks.algafoods.domain.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository repository;
    @Autowired
    private GrupoService service;
    @Autowired
    private GrupoInputDisassembler disassembler;
    @Autowired
    private GrupoModelAssembler assembler;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping
    public CollectionModel<GrupoModel> listar(){
        return assembler.toCollectionModel(repository.findAll());
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping("/{grupoId}")
    public GrupoModel buscar(@PathVariable Long grupoId){
        Grupo grupo = service.buscarOuFalhar(grupoId);
        return assembler.toModel(grupo);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = disassembler.toDomainObject(grupoInput);

        grupo = service.salvar(grupo);

        return assembler.toModel(grupo);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @PutMapping("/{grupoId}")
    public GrupoModel atualizar(@PathVariable Long grupoId,
                                @RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupoAtual = service.buscarOuFalhar(grupoId);

        disassembler.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = service.salvar(grupoAtual);

        return assembler.toModel(grupoAtual);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        service.excluir(grupoId);
    }
}
