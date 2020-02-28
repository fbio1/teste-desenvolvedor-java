package com.loja.produtos.domains;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ApiModel(description = "Todos os detalhes de Produto.")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "O banco de dados gera produto ID")
    private Long id;

    @NotNull
    @ApiModelProperty(notes = "Nome do produto cadastrado")
    private String nome;
}
