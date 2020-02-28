package com.loja.produtos.repository;

import com.loja.produtos.domains.Produto;
import com.loja.produtos.domains.UsuarioProduto;
import com.loja.produtos.dtos.ProdutoDTO;
import com.loja.produtos.dtos.UsuarioByProdutosListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Usuario_ProdutoRepository extends JpaRepository<UsuarioProduto, Long> {

    @Query(" SELECT up FROM UsuarioProduto up " +
            "JOIN Usuario u ON u.id = up.usuario.id AND u.ativo = TRUE " +
            "JOIN Produto p on p.id = up.produto.id " +
            "WHERE up.produto.id = ?1")
    List<UsuarioProduto> findbyUsuariosPorProduto(Long id);

    @Query(" SELECT p FROM UsuarioProduto up " +
            "JOIN Usuario u ON u.id = up.usuario.id AND u.ativo = TRUE " +
            "JOIN Produto p on p.id = up.produto.id " +
            "WHERE up.usuario.id = ?1")
    List<Produto> findProdutosByIdUsuario(Long id);
}
