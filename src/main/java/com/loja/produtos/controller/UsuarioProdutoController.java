package com.loja.produtos.controller;

import com.loja.produtos.domains.Produto;
import com.loja.produtos.domains.Usuario;
import com.loja.produtos.domains.UsuarioProduto;
import com.loja.produtos.dtos.*;
import com.loja.produtos.mapper.ProdutoMapper;
import com.loja.produtos.mapper.UsuarioMapper;
import com.loja.produtos.service.ProdutoService;
import com.loja.produtos.service.UsuarioProdutoService;
import com.loja.produtos.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UsuarioProdutoController.BASE_URL)
public class UsuarioProdutoController {
    public static final String BASE_URL = "/usuario_produto";

    private final UsuarioProdutoService usuarioProdutoService;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;

    public UsuarioProdutoController(UsuarioProdutoService usuarioProdutoService, ProdutoService produtoService, UsuarioService usuarioService) {
        this.usuarioProdutoService = usuarioProdutoService;
        this.usuarioService = usuarioService;
        this.produtoService = produtoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UsuarioProdutoListDTO getAll(@RequestParam(name = "id", required = false) Long id) { // id do produto
        if (id != null) {
            return new UsuarioProdutoListDTO(usuarioProdutoService.getUsuarioProdutoByIdProduto(id));
        } else {
            return new UsuarioProdutoListDTO(usuarioProdutoService.getAll());
        }
    }

    @GetMapping({"produtosByUsuario/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public UsuarioByProdutosListDTO getProdutosByIdUsuario(@PathVariable Long id) { //id do usuario
        return usuarioProdutoService.getProdutosByIdUsuario(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveUsuarioInProduto(@RequestBody Long idProduto, Long idUsuario){
        ProdutoDTO p = produtoService.getById(idProduto);
        Produto prod = ProdutoMapper.INSTANCE.produtoDTOToProtudo(p);

        UsuarioDTO u = usuarioService.getById(idUsuario);
        Usuario user = UsuarioMapper.INSTANCE.usuarioDTOTousuario(u);

        UsuarioProduto user_prod = UsuarioProduto
                .builder()
                .produto(prod)
                .usuario(user)
                .build();

        usuarioProdutoService.generateProdutoAndUsuario(user_prod);

        return ResponseEntity.status(HttpStatus.CREATED).body(user_prod);
    }
}
