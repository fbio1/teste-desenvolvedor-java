package com.loja.produtos.service;

import com.loja.produtos.domains.Produto;
import com.loja.produtos.domains.UsuarioProduto;
import com.loja.produtos.dtos.ProdutoDTO;
import com.loja.produtos.dtos.UsuarioByProdutosListDTO;
import com.loja.produtos.dtos.UsuarioProdutoDTO;
import com.loja.produtos.exception.ResourceNotFoundException;
import com.loja.produtos.mapper.ProdutoMapper;
import com.loja.produtos.mapper.UsuarioProdutoMapper;
import com.loja.produtos.repository.UsuarioRepository;
import com.loja.produtos.repository.Usuario_ProdutoRepository;
import com.loja.produtos.service.UsuarioProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioProdutoService implements AbstractService<UsuarioProdutoDTO, Long> {

    private final UsuarioProdutoMapper usuarioProdutoMapper;
    private final Usuario_ProdutoRepository usuario_produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final UsuarioRepository usuarioRepository;

    public UsuarioProdutoService(UsuarioProdutoMapper usuarioProdutoMapper, Usuario_ProdutoRepository usuario_produtoRepository, ProdutoMapper produtoMapper, UsuarioRepository usuarioRepository) {
        this.usuarioProdutoMapper = usuarioProdutoMapper;
        this.usuario_produtoRepository = usuario_produtoRepository;
        this.produtoMapper = produtoMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioProdutoDTO> getAll() {
        return usuario_produtoRepository.findAll()
                .stream()
                .map(user_prod -> {
                    UsuarioProdutoDTO usuario_produtoDTO = usuarioProdutoMapper.convertToDTO(user_prod);
                    return usuario_produtoDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioProdutoDTO getById(Long id) {
        UsuarioProdutoDTO usuario_produtoDTO = usuarioProdutoMapper.convertToDTO(usuario_produtoRepository.findById(id).orElseThrow(ResourceNotFoundException::new));

        return usuario_produtoDTO;
    }

    @Override
    public UsuarioProdutoDTO createNew(UsuarioProdutoDTO usuario_produtoDTO) {
        return saveAndReturnDTO(usuarioProdutoMapper.convertEntity(usuario_produtoDTO));
    }

    private UsuarioProdutoDTO saveAndReturnDTO(UsuarioProduto usuario_produto) {
        UsuarioProduto saved = usuario_produtoRepository.save(usuario_produto);
        UsuarioProdutoDTO returnDto = usuarioProdutoMapper.convertToDTO(saved);
        return returnDto;
    }

    @Override
    public UsuarioProdutoDTO saveByDTO(Long id, UsuarioProdutoDTO produtoDTO) {
        UsuarioProduto produto = usuarioProdutoMapper.convertEntity(produtoDTO);
        produto.setId(id);
        return saveAndReturnDTO(produto);
    }

    @Override
    public UsuarioProdutoDTO patch(Long id, UsuarioProdutoDTO usuario_produtoDTO) {
        return usuario_produtoRepository.findById(id).map(usuario_produto -> {
            if(usuario_produtoDTO.getProduto() != null && usuario_produtoDTO.getUsuario() != null){
                usuario_produto.setProduto(usuario_produtoDTO.getProduto());
                usuario_produto.setUsuario(usuario_produtoDTO.getUsuario());
            }
            UsuarioProdutoDTO returnDto = usuarioProdutoMapper.convertToDTO(usuario_produtoRepository.save(usuario_produto));
            return returnDto;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        usuario_produtoRepository.deleteById(id);
    }


    public UsuarioProduto patcUser_Produto(Long id, UsuarioProduto usuario_produto) {
        return usuario_produtoRepository.findById(id).map(user_prod -> {
            if(usuario_produto.getProduto() != null && usuario_produto.getUsuario() != null){
                user_prod.setAtivo(false);
            }
            return usuario_produtoRepository.save(user_prod);
        }).orElseThrow(ResourceNotFoundException::new);
    }


    public UsuarioProduto generateProdutoAndUsuario(UsuarioProduto newUser) {

        List<UsuarioProduto> list = usuario_produtoRepository.findAll();

        for (UsuarioProduto usuario_produto : list) {
            if(usuario_produto.getProduto().getId().equals(newUser.getProduto().getId())
                    && usuario_produto.getUsuario().getId().equals(newUser.getUsuario().getId())){

                patcUser_Produto(usuario_produto.getId(), usuario_produto);
                break;
            }
        }
        return  usuario_produtoRepository.save(newUser);
    }

    public List<UsuarioProdutoDTO> getUsuarioProdutoByIdProduto(Long id) {
        return usuario_produtoRepository.findbyUsuariosPorProduto(id)
                .stream()
                .map(user_prod -> {
                    UsuarioProdutoDTO usuario_produtoDTO = usuarioProdutoMapper.convertToDTO(user_prod);
                    return usuario_produtoDTO;
                })
                .collect(Collectors.toList());
    }

    public UsuarioByProdutosListDTO getProdutosByIdUsuario(Long id) {
        UsuarioByProdutosListDTO usuario_produtoDTO = new UsuarioByProdutosListDTO();
        List<ProdutoDTO> p = usuario_produtoRepository.findProdutosByIdUsuario(id)
                .stream()
                .map(produto -> {
                    ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);
                    return produtoDTO;
                })
                .collect(Collectors.toList());
        usuario_produtoDTO.setProdutoDTOS(p);
        usuario_produtoDTO.setUsuario(usuarioRepository.findById(id).get());
        return usuario_produtoDTO;
    }
}
