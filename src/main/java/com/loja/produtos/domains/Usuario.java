package com.loja.produtos.domains;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@ApiModel(description = "Todos os detalhes de Usuario.")
public class Usuario extends DomainBase{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "O banco de dados gera usuario ID")
    private Long id;

    @NotNull
    @ApiModelProperty(notes = "Nome do Usuário")
    private String nomeComposto;

    @CPF
    @NotNull(message = "CPF invalido")
    @Column(unique = true, nullable = false)
    @ApiModelProperty(notes = "Numero do CPF do Usuário")
    private String cpf;

    @NotNull
    @ApiModelProperty(notes = "Endereço do Usuário")
    private String endereco;

    @ApiModelProperty(notes = "Numero do telefone do Usuário")
    private String telefone;

    @Email
    @NotNull
    @ApiModelProperty(notes = "Email do Usuário")
    private String email;
}

