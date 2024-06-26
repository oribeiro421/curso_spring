package com.algaworks.algafoods.api.v1.controller;

import com.algaworks.algafoods.api.v1.assembler.GrupoModelAssembler;
import com.algaworks.algafoods.api.v1.model.GrupoModel;
import com.algaworks.algafoods.domain.model.Usuario;
import com.algaworks.algafoods.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoModelAssembler grupoModelAssembler;


    @GetMapping
    public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId){
        Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);

        return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
    }

    @PutMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void asossicarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioService.asossicarGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioService.desassociarGrupo(usuarioId, grupoId);
    }
}
