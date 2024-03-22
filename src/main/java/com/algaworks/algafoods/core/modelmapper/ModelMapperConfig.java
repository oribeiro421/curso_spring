package com.algaworks.algafoods.core.modelmapper;

import com.algaworks.algafoods.api.model.EnderecoModel;
import com.algaworks.algafoods.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        var enderecoToEnderecoModelTypeMap = modelMapper
                .createTypeMap(Endereco.class, EnderecoModel.class);

       enderecoToEnderecoModelTypeMap.<String>addMapping(
               endereco -> endereco.getCidade().getEstado().getNome(),
               ((enderecoModel, value) -> enderecoModel.getCidade().setEstado(value))
       );

        return modelMapper;
    }
}
