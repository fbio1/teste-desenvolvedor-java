package com.loja.produtos.dtos;

import com.loja.produtos.domains.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioByProdutosListDTO {

	private List<ProdutoDTO> produtoDTOS;

	private Usuario usuario;

	public UsuarioByProdutosListDTO(List<UsuarioByProdutosListDTO> produtosByIdUsuario) {
	}
}
