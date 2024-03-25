package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.NegocioException;
import com.algaworks.algafoods.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafoods.domain.model.Grupo;
import com.algaworks.algafoods.domain.model.Usuario;
import com.algaworks.algafoods.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private GrupoService grupoService;

    @Transactional
    public Usuario salvar(Usuario usuario){
        repository.detach(usuario);

        Optional<Usuario> usuarioExistente = repository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)){
            throw new NegocioException(String.format
                    ("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
        }
        return repository.save(usuario);
    }
    @Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha){

        Usuario usuario = buscarOuFalhar(usuarioId);

        if (usuario.senhaNaoCoincideCom(senhaAtual)){
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }

        usuario.setSenha(novaSenha);
    }
    @Transactional
    public void asossicarGrupo(Long usuarioId,Long grupoId){
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);

        usuario.associarGrupo(grupo);
    }
    @Transactional
    public void desassociarGrupo(Long usuarioId,Long grupoId){
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = grupoService.buscarOuFalhar(grupoId);

        usuario.desassociarGrupo(grupo);
    }

    public Usuario buscarOuFalhar(Long usuarioId){
        return repository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
