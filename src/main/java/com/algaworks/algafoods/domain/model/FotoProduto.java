package com.algaworks.algafoods.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class FotoProduto {

    @Id
    @Column(name = "produto_id")
    private Long id;
    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;
}
