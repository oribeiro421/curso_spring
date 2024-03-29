package com.algaworks.algafoods.api.controller;

import com.algaworks.algafoods.api.assembler.UsuarioInputDisassembler;
import com.algaworks.algafoods.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafoods.api.model.UsuarioModel;
import com.algaworks.algafoods.api.model.input.SenhaInput;
import com.algaworks.algafoods.api.model.input.UsuarioComSenhaInput;
import com.algaworks.algafoods.api.model.input.UsuarioInput;
import com.algaworks.algafoods.domain.model.Usuario;
import com.algaworks.algafoods.domain.repository.UsuarioRepository;
import com.algaworks.algafoods.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService service;
    @Autowired
    private UsuarioModelAssembler assembler;
    @Autowired
    private UsuarioInputDisassembler disassembler;

    @GetMapping
    public List<UsuarioModel> listar(){
        return assembler.toCollectionModel(repository.findAll());
    }
    @GetMapping("/{usuarioId}")
    public UsuarioModel obter(@PathVariable Long usuarioId){
        Usuario usuario = service.buscarOuFalhar(usuarioId);
        return assembler.toModel(usuario);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput){
        Usuario usuario = disassembler.toDomainObject(usuarioInput);
        usuario = service.salvar(usuario);
        return assembler.toModel(usuario);
    }
    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
                                  @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = service.buscarOuFalhar(usuarioId);
        disassembler.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = service.salvar(usuarioAtual);

        return assembler.toModel(usuarioAtual);
    }
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        service.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }


}
