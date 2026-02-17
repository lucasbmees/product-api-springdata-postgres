package com.example.gerenciador_produtos;

import com.example.gerenciador_produtos.principal.Principal;
import com.example.gerenciador_produtos.repository.CategoriaRepository;
import com.example.gerenciador_produtos.repository.PedidoRepository;
import com.example.gerenciador_produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GerenciadorProdutosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorProdutosApplication.class, args);
	}

   	@Autowired
   	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		Principal principal = new Principal(produtoRepository, categoriaRepository, pedidoRepository);
		principal.salvarDados();
	}
}
