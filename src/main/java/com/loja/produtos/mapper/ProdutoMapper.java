package com.loja.produtos.mapper;

import com.loja.produtos.domains.Produto;
import com.loja.produtos.dtos.ProdutoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdutoMapper {

	ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
	
	ProdutoDTO produtoToProdutoDTO(Produto produto);
	
	Produto produtoDTOToProtudo(ProdutoDTO produtoDTO);
	
}