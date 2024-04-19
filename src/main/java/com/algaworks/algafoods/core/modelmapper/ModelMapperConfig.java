package com.algaworks.algafoods.core.modelmapper;

import com.algaworks.algafoods.api.v1.model.EnderecoModel;
import com.algaworks.algafoods.api.v1.model.input.ItemPedidoInput;
import com.algaworks.algafoods.api.v2.model.input.CidadeInputV2;
import com.algaworks.algafoods.domain.model.Cidade;
import com.algaworks.algafoods.domain.model.Endereco;
import com.algaworks.algafoods.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
                .addMappings(mapper -> mapper.skip(Cidade::setId));

        var enderecoToEnderecoModelTypeMap = modelMapper
                .createTypeMap(Endereco.class, EnderecoModel.class);

       enderecoToEnderecoModelTypeMap.<String>addMapping(
               endereco -> endereco.getCidade().getEstado().getNome(),
               ((enderecoModel, value) -> enderecoModel.getCidade().setEstado(value))
       );

       modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
               .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        return modelMapper;
    }
}
