package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoods.domain.model.Grupo;
import com.algaworks.algafoods.domain.repository.GrupoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class GrupoService {

    private static final String MSG_GRUPO_NAO_ENCONTRADA
            = "Não existe um cadastro de grupo com código %d";

    private static final String MSG_GRUPO_EM_USO
            = "Grupo de código %d não pode ser removida, pois está em uso";

    @Autowired
    private GrupoRepository grupoRepository;

    @Transactional
    public Grupo salvar(Grupo grupo){
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long grupoId){
        try {
            grupoRepository.deleteById(grupoId);
            grupoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_GRUPO_NAO_ENCONTRADA, grupoId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_GRUPO_EM_USO, grupoId));
        }
    }

    public Grupo buscarOuFalhar(Long grupoId){
        return grupoRepository.findById(grupoId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                String.format(MSG_GRUPO_NAO_ENCONTRADA, grupoId)
        ));
    }
}
