package com.loja.produtos.boostrap;

import com.loja.produtos.domains.Produto;
import com.loja.produtos.domains.Usuario;
import com.loja.produtos.domains.UsuarioProduto;
import com.loja.produtos.repository.ProdutoRepository;
import com.loja.produtos.repository.UsuarioRepository;
import com.loja.produtos.repository.Usuario_ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

	private UsuarioRepository usuarioRepository;

	private ProdutoRepository produtoRepository;

	private Usuario_ProdutoRepository usuario_produtoRepository;

	public Bootstrap(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository, Usuario_ProdutoRepository usuario_produtoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.produtoRepository = produtoRepository;
		this.usuario_produtoRepository = usuario_produtoRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadUsuarios();
		loadProdutos();
		loadUsuario_Produtos();
	}

	private void loadUsuarios() {
		Usuario usuario1 = Usuario.builder()
				.nomeComposto("Joao")
				.cpf("81110588020")
				.email("joao@gmail.com")
				.endereco("Rua Aeroporto Cruzeiro do Sul")
				.telefone("(84) 998583058")
				.build();

		Usuario usuario2 = Usuario.builder()
				.nomeComposto("Maria")
				.cpf("89887466000")
				.email("maria@gmail.com")
				.endereco("Rua Aeroporto Cruzeiro do Sul")
				.telefone("(84) 998583058")
				.build();

		usuarioRepository.save(usuario1);
		usuarioRepository.save(usuario2);

		System.out.println("Usuarios loaded: " + usuarioRepository.count());
	}

	private void loadProdutos() {
		Produto produto1 = Produto.builder().nome("COMUM").build();
		Produto produto2 = Produto.builder().nome("ESCOLAR").build();
		Produto produto3 = Produto.builder().nome("SENIOR").build();

		produtoRepository.save(produto1);
		produtoRepository.save(produto2);
		produtoRepository.save(produto3);

		System.out.println("Produtos loaded: " + produtoRepository.count());

	}

	private void loadUsuario_Produtos() {
		Produto produto1 = Produto.builder().nome("TESTE").build();

		produtoRepository.save(produto1);

		Usuario usuario1 = Usuario.builder()
				.nomeComposto("teste")
				.cpf("62902593007")
				.email("teste@gmail.com")
				.endereco("Rua TESTE")
				.telefone("(84) 998583058")
				.build();

		usuarioRepository.save(usuario1);

		UsuarioProduto usuarioproduto = UsuarioProduto
				.builder()
				.produto(produto1)
				.usuario(usuario1)
				.build();

		usuario_produtoRepository.save(usuarioproduto);

		System.out.println("Usuario_Produtos loaded: " + usuario_produtoRepository.count());
	}
}
