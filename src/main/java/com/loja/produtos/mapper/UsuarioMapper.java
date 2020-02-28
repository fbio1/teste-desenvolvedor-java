package com.loja.produtos.mapper;

import com.loja.produtos.domains.Usuario;
import com.loja.produtos.dtos.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	UsuarioDTO usuariotoUsuarioDTO(Usuario usuario);
	
	Usuario usuarioDTOTousuario(UsuarioDTO UsuarioDTO);
	
}