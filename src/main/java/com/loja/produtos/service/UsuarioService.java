package com.loja.produtos.service;

import com.loja.produtos.controller.UsuarioController;
import com.loja.produtos.domains.Usuario;
import com.loja.produtos.dtos.ProdutoDTO;
import com.loja.produtos.dtos.UsuarioDTO;
import com.loja.produtos.exception.ResourceNotFoundException;
import com.loja.produtos.mapper.UsuarioMapper;
import com.loja.produtos.repository.UsuarioRepository;
import com.loja.produtos.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements AbstractService<UsuarioDTO, Long> {

	private final UsuarioMapper usuarioMapper;
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository) {
		this.usuarioMapper = usuarioMapper;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public List<UsuarioDTO> getAll() {
		return usuarioRepository.findAll()
				.stream()
				.map(usuario -> {
					UsuarioDTO usuarioDTO = usuarioMapper.usuariotoUsuarioDTO(usuario);
                    usuarioDTO.setUrl(UsuarioController.BASE_URL + usuario.getId());
					return usuarioDTO;
				})
				.collect(Collectors.toList());
	}
	
	@Override
	public UsuarioDTO getById(Long id) {
		UsuarioDTO UsuarioDTO = usuarioMapper.usuariotoUsuarioDTO(usuarioRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
		UsuarioDTO.setUrl(UsuarioController.BASE_URL + id);
		return UsuarioDTO;
	}
	
	@Override
    public UsuarioDTO createNew(UsuarioDTO usuarioDTO) {
        return saveAndReturnDTO(usuarioMapper.usuarioDTOTousuario(usuarioDTO));
    }

    private UsuarioDTO saveAndReturnDTO(Usuario usuario) {
        Usuario saved = usuarioRepository.save(usuario);
        UsuarioDTO returnDto = usuarioMapper.usuariotoUsuarioDTO(saved);
        returnDto.setUrl(UsuarioController.BASE_URL + saved.getId());
        return returnDto;
    }

    @Override
    public UsuarioDTO saveByDTO(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.usuarioDTOTousuario(usuarioDTO);
        usuario.setId(id);
        return saveAndReturnDTO(usuario);
    }
    
    @Override
    public UsuarioDTO patch(Long id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id).map(usuario -> {
            if(usuarioDTO.getNomeComposto() != null){
                usuario.setNomeComposto(usuarioDTO.getNomeComposto());
            }
            UsuarioDTO returnDto = usuarioMapper.usuariotoUsuarioDTO(usuarioRepository.save(usuario));
            returnDto.setUrl(UsuarioController.BASE_URL + id);
            return returnDto;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
	    usuarioRepository.deleteById(id);
    }
}
