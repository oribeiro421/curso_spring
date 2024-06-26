package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.FotoProdutoNaoEncontradoException;
import com.algaworks.algafoods.domain.model.FotoProduto;
import com.algaworks.algafoods.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class FotoProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo){
        Long restauranteId = foto.getRestauranteId();
        Long produtoId = foto.getProduto().getId();
        String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
        if (fotoExistente.isPresent()){
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeNovoArquivo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto.builder()
                            .contentType(foto.getContentType())
                            .nomeArquivo(foto.getNomeArquivo())
                            .inputStream(dadosArquivo)
                            .build();

        fotoStorageService.substituir(nomeArquivoExistente,novaFoto);

        return foto;
    }
    @Transactional
    public void excluir(Long restauranteId, Long produtoId){
        FotoProduto fotoProduto = buscarOuFalhar(restauranteId, produtoId);

        produtoRepository.delete(fotoProduto);
        produtoRepository.flush();

        fotoStorageService.remover(fotoProduto.getNomeArquivo());
    }

    public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId){
        return produtoRepository.findFotoById(restauranteId,produtoId)
                .orElseThrow(() -> new FotoProdutoNaoEncontradoException(restauranteId, produtoId));
    }
}
