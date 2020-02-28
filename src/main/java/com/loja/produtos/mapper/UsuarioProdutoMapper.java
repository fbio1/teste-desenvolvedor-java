package com.loja.produtos.mapper;

import com.loja.produtos.domains.UsuarioProduto;
import com.loja.produtos.dtos.UsuarioByProdutosListDTO;
import com.loja.produtos.dtos.UsuarioProdutoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioProdutoMapper {

	UsuarioProdutoMapper INSTANCE = Mappers.getMapper(UsuarioProdutoMapper.class);
	
	UsuarioProdutoDTO convertToDTO(UsuarioProduto usuario_produto);
	
	UsuarioProduto convertEntity(UsuarioProdutoDTO usuario_produtoDTO);
}