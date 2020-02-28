package com.loja.produtos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioProdutoListDTO {

	private List<UsuarioProdutoDTO> usuarios;
}
