package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoods.domain.model.Permissao;
import com.algaworks.algafoods.domain.repository.PermissaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PermissaoService {

    private static final String MSG_PERMISSAO_NAO_ENCONTRADA
            = "Não existe um cadastro de permissao com código %d";
    private static final String MSG_PERMISSAO_EM_USO
            = "Permissao de código %d não pode ser removida, pois está em uso";
    @Autowired
    private PermissaoRepository permissaoRepository;

    @Transactional
    public Permissao salvar(Permissao permissao){
        return permissaoRepository.save(permissao);
    }

    @Transactional
    public void excluir(Long permissaoId) throws EntidadeEmUsoException{
        try{
            permissaoRepository.deleteById(permissaoId);
            permissaoRepository.flush();
        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_PERMISSAO_NAO_ENCONTRADA, permissaoId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_PERMISSAO_EM_USO, permissaoId));
        }
    }

    public Permissao buscarOuFalhar(Long permissaoId){
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_PERMISSAO_NAO_ENCONTRADA, permissaoId)));
    }
}
