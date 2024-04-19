package com.algaworks.algafoods.api.v1.controller;

import com.algaworks.algafoods.api.v1.assembler.PedidoInputDisassembler;
import com.algaworks.algafoods.api.v1.assembler.PedidoModelAssembler;
import com.algaworks.algafoods.api.v1.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafoods.api.v1.model.PedidoModel;
import com.algaworks.algafoods.api.v1.model.PedidoResumoModel;
import com.algaworks.algafoods.api.v1.model.input.PedidoInput;
import com.algaworks.algafoods.core.data.PageWrapper;
import com.algaworks.algafoods.core.data.PageableTranslator;
import com.algaworks.algafoods.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoods.domain.exception.NegocioException;
import com.algaworks.algafoods.domain.model.Pedido;
import com.algaworks.algafoods.domain.model.Usuario;
import com.algaworks.algafoods.domain.repository.PedidoRepository;
import com.algaworks.algafoods.domain.filter.PedidoFilter;
import com.algaworks.algafoods.domain.service.PedidoService;
import com.algaworks.algafoods.infrastructure.spec.PedidoSpecs;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;

    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;

    @Autowired
    private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filter, Pageable pageable){
        Pageable pageableTraduzido = traduzirPageable(pageable);

        Page<Pedido> pedidoPage = pedidoRepository
                .findAll(PedidoSpecs.usandoFiltro(filter), pageableTraduzido);

        pedidoPage = new PageWrapper<>(pedidoPage, pageable);

        return pagedResourcesAssembler.toModel(pedidoPage, pedidoResumoModelAssembler);
    }
    @GetMapping("/{codigoPedido}")
    public PedidoModel obter(@PathVariable String codigoPedido){
        Pedido pedido = pedidoService.buscarOuFalhar(codigoPedido);
        return pedidoModelAssembler.toModel(pedido);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel salvar(@RequestBody @Valid PedidoInput pedidoInput){
        try {
            Pedido pedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            pedido.setCliente(new Usuario());
            pedido.getCliente().setId(1L);

            return pedidoModelAssembler.toModel(pedidoService.salvar(pedido));
        } catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage(), e);
        }
    }

    private Pageable traduzirPageable(Pageable apiPageable){
        var mapeamento = Map.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "cliente.nome", "cliente.nome",
                "valorTotal", "valorTotal",
                "status", "status",
                "subTotal", "subTotal",
                "taxaFrete", "taxaFrete",
                "dataCriacao", "dataCriacao"
        );

        return PageableTranslator.translate(apiPageable, mapeamento);
    }

}
