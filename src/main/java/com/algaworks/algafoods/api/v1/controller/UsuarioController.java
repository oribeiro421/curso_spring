package com.algaworks.algafoods.api.v1.controller;

import com.algaworks.algafoods.api.v1.assembler.UsuarioInputDisassembler;
import com.algaworks.algafoods.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafoods.api.v1.model.UsuarioModel;
import com.algaworks.algafoods.api.v1.model.input.SenhaInput;
import com.algaworks.algafoods.api.v1.model.input.UsuarioComSenhaInput;
import com.algaworks.algafoods.api.v1.model.input.UsuarioInput;
import com.algaworks.algafoods.core.security.CheckSecurity;
import com.algaworks.algafoods.domain.model.Usuario;
import com.algaworks.algafoods.domain.repository.UsuarioRepository;
import com.algaworks.algafoods.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService service;
    @Autowired
    private UsuarioModelAssembler assembler;
    @Autowired
    private UsuarioInputDisassembler disassembler;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @GetMapping
    public CollectionModel<UsuarioModel> listar(){
        return assembler.toCollectionModel(repository.findAll());
    }
    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId){
        Usuario usuario = service.buscarOuFalhar(usuarioId);
        return assembler.toModel(usuario);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput){
        Usuario usuario = disassembler.toDomainObject(usuarioInput);
        usuario = service.salvar(usuario);
        return assembler.toModel(usuario);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
                                  @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = service.buscarOuFalhar(usuarioId);
        disassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = service.salvar(usuarioAtual);

        return assembler.toModel(usuarioAtual);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        service.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }


}
