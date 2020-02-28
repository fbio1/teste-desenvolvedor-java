package com.loja.produtos.controller;

import com.loja.produtos.dtos.ProdutoDTO;
import com.loja.produtos.dtos.ProdutoListDTO;
import com.loja.produtos.dtos.UsuarioDTO;
import com.loja.produtos.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ProdutoController.BASE_URL)
public class ProdutoController {
    public static final String BASE_URL = "/produtos";

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Lista de todos os produtos", response = ProdutoListDTO.class)
    public ProdutoListDTO getAll() {
        return new ProdutoListDTO(produtoService.getAll());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca pelo id do produto")
    public ProdutoDTO getById(@PathVariable Long id) {
        return produtoService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO createNew(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.createNew(produtoDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO update(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.saveByDTO(id, produtoDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public ProdutoDTO patch(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
        return produtoService.patch(id, produtoDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        produtoService.deleteById(id);
    }
}
