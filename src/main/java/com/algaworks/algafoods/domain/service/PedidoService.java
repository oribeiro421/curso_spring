package com.algaworks.algafoods.domain.service;

import com.algaworks.algafoods.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoods.domain.exception.NegocioException;
import com.algaworks.algafoods.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafoods.domain.model.*;
import com.algaworks.algafoods.domain.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private FormaPagamentoService formaPagamentoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private CidadeService cidadeService;
    @Autowired
    private ProdutoService produtoService;

    @Transactional
    public Pedido salvar(Pedido pedido){
        validaPedido(pedido);
        validarItens(pedido);

        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularTotal();

        return pedidoRepository.save(pedido);
    }
    public void validaPedido(Pedido pedido){
        Restaurante restaurante = restauranteService.buscarOuFalhar(pedido.getRestaurante().getId());
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(pedido.getFormaPagamento().getId());
        Cidade cidade = cidadeService.buscarOuFalhar(pedido.getEnderecoEntrega().getCidade().getId());
        Usuario usuario = usuarioService.buscarOuFalhar(pedido.getCliente().getId());

        pedido.setFormaPagamento(formaPagamento);
        pedido.getEnderecoEntrega().setCidade(cidade);
        pedido.setRestaurante(restaurante);
        pedido.setCliente(usuario);

        if (restaurante.naoAceitaFormaPagamento(formaPagamento)){
            throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    formaPagamento.getDescricao()));
        }
    }
    public void validarItens(Pedido pedido){
        pedido.getItens().forEach(item -> {
            Produto produto = produtoService.buscarOuFalhar
                    (pedido.getRestaurante().getId(), item.getProduto().getId());

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());
        });
    }
    public Pedido buscarOuFalhar(String codigoPedido){
        return pedidoRepository.findByCodigo(codigoPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException(codigoPedido));
    }
}