package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoods.domain.model.FormaPagamento;
import com.algaworks.algafoods.domain.repository.FormaPagamentoRepositoy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoService {

    private static final String MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA
            = "Não existe um cadastro de forma de pagamento com código %d";
    private static final String MSG_FORMA_DE_PAGAMENTO_EM_USO
            = "Forma de pagamento de código %d não pode ser removida, pois está em uso";
    @Autowired
    private FormaPagamentoRepositoy repositoy;

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento){
        return repositoy.save(formaPagamento);
    }

    @Transactional
    public void excluir(Long formaPagamendoId) throws EntidadeEmUsoException{
        try{
             repositoy.deleteById(formaPagamendoId);
             repositoy.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA, formaPagamendoId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_FORMA_DE_PAGAMENTO_EM_USO, formaPagamendoId));
        }
    }

    public FormaPagamento buscarOuFalhar(Long formaPagamendoId){
        return repositoy.findById(formaPagamendoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_FORMA_DE_PAGAMENTO_NAO_ENCONTRADA, formaPagamendoId)));
    }
}
