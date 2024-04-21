package com.algaworks.algafoods.api.v1.controller;

import com.algaworks.algafoods.api.v1.AlgaLinks;
import com.algaworks.algafoods.api.v1.assembler.UsuarioModelAssembler;
import com.algaworks.algafoods.api.v1.model.UsuarioModel;
import com.algaworks.algafoods.domain.model.Restaurante;
import com.algaworks.algafoods.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/restaurantes/{restauranteId}/responsaveis")
public class RestauranteResponsavelController {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;
    @Autowired
    private AlgaLinks algaLinks;

    @GetMapping
    public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId){
        Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);

        CollectionModel<UsuarioModel> usuarioModels = usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis())
                .removeLinks()
                .add(algaLinks.linkToRestauranteResponsaveis(restauranteId))
                .add();

        usuarioModels.getContent().stream().forEach(usuarioModel -> {
            usuarioModel.add(algaLinks.linkToRestauranteResponsavelDesassociacao(
                    restauranteId, usuarioModel.getId(), "desassociar"));
        });

        return usuarioModels;
    }
    @PutMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
        restauranteService.asossicarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> desassociarResponsavel(@PathVariable Long restauranteId, @PathVariable Long usuarioId){
        restauranteService.desassociarResponsavel(restauranteId, usuarioId);

        return ResponseEntity.noContent().build();
    }
}
