package com.loja.produtos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO implements Serializable {

	private Long id;

	private String nome;

	@JsonProperty("produto_url")
	private String url;
}
