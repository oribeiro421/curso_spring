package com.algaworks.algafoods.api.controller;

import com.algaworks.algafoods.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafoods.api.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafoods.api.model.FormaPagamentoModel;
import com.algaworks.algafoods.api.model.input.FormaPagamentoInput;
import com.algaworks.algafoods.domain.model.FormaPagamento;
import com.algaworks.algafoods.domain.repository.FormaPagamentoRepositoy;
import com.algaworks.algafoods.domain.service.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepositoy formaPagamentoRepositoy;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

    @Autowired
    private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoModel>> listar(){
        List<FormaPagamentoModel> formaPagamentoModels =
                formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepositoy.findAll());

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(formaPagamentoModels);
    }
    @GetMapping("/{formaPagamentoId}")
    public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId){
        FormaPagamento formaPagamento = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);

        formaPagamento = formaPagamentoService.salvar(formaPagamento);

        return formaPagamentoModelAssembler.toModel(formaPagamento);
    }

    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
                                         @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = formaPagamentoService.buscarOuFalhar(formaPagamentoId);

        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        return formaPagamentoModelAssembler.toModel(formaPagamentoService.salvar(formaPagamentoAtual));
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        formaPagamentoService.excluir(formaPagamentoId);
    }
}
