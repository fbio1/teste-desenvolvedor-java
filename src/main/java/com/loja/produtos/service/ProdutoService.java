package com.loja.produtos.service;

import com.loja.produtos.controller.UsuarioController;
import com.loja.produtos.domains.Produto;
import com.loja.produtos.dtos.ProdutoDTO;
import com.loja.produtos.exception.ResourceNotFoundException;
import com.loja.produtos.mapper.ProdutoMapper;
import com.loja.produtos.repository.ProdutoRepository;
import com.loja.produtos.repository.Usuario_ProdutoRepository;
import com.loja.produtos.service.AbstractService;
import com.loja.produtos.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService implements AbstractService<ProdutoDTO, Long> {

    private final ProdutoMapper produtoMapper;
    private final ProdutoRepository produtoRepository;
    private final Usuario_ProdutoRepository usuario_produtoRepository;

    public ProdutoService(ProdutoMapper produtoMapper, ProdutoRepository produtoRepository, Usuario_ProdutoRepository usuario_produtoRepository) {
        this.produtoMapper = produtoMapper;
        this.produtoRepository = produtoRepository;
        this.usuario_produtoRepository = usuario_produtoRepository;
    }

    @Override
    public List<ProdutoDTO> getAll() {
        return produtoRepository.findAll()
                .stream()
                .map(produto -> {
                    ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produto);
                    return produtoDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO getById(Long id) {
        ProdutoDTO produtoDTO = produtoMapper.produtoToProdutoDTO(produtoRepository.findById(id).orElseThrow(ResourceNotFoundException::new));

        return produtoDTO;
    }

    @Override
    public ProdutoDTO createNew(ProdutoDTO produtoDTO) {
        return saveAndReturnDTO(produtoMapper.produtoDTOToProtudo(produtoDTO));
    }

    private ProdutoDTO saveAndReturnDTO(Produto produto) {
        Produto saved = produtoRepository.save(produto);
        ProdutoDTO returnDto = produtoMapper.produtoToProdutoDTO(saved);
        return returnDto;
    }

    @Override
    public ProdutoDTO saveByDTO(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoMapper.produtoDTOToProtudo(produtoDTO);
        produto.setId(id);
        return saveAndReturnDTO(produto);
    }

    @Override
    public ProdutoDTO patch(Long id, ProdutoDTO produtoDTO) {
        return produtoRepository.findById(id).map(produto -> {
            if(produtoDTO.getNome() != null){
                produto.setNome(produtoDTO.getNome());
            }
            ProdutoDTO returnDto = produtoMapper.produtoToProdutoDTO(produtoRepository.save(produto));
            returnDto.setUrl(UsuarioController.BASE_URL + id);
            return returnDto;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

}
