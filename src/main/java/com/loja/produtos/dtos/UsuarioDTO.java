package com.loja.produtos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loja.produtos.domains.DomainBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO extends DomainBase implements Serializable {

	private Long id;

	private String nomeComposto;

	private String cpf;

	private String endereco;

	private String telefone;

	private String email;

	@JsonProperty("usuario_url")
	private String url;
}
