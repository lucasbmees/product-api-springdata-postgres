package com.example.gerenciador_produtos.repository;

import com.example.gerenciador_produtos.model.Categoria;
import com.example.gerenciador_produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findProdutoByNomeContainingIgnoreCase(String nome);

    List<Produto> findProdutoByCategoria(Optional<Categoria> categoria);

    List<Produto> findProdutoByPrecoGreaterThan(Double valor);

    List<Produto> findProdutoByPrecoLessThan(Double valor);

    List<Produto> findByCategoriaNomeOrderByPreco(String nome);

    List<Produto> findByCategoriaNomeOrderByPrecoDesc(String nome);

    Integer countByCategoriaNomeContainingIgnoreCase(String nome);

    Integer countByPrecoGreaterThan(Double valor);

    List<Produto> findByPrecoGreaterThanOrNomeContainingIgnoreCase(Double valor, String nome);

    List<Produto> findTop3ByOrderByPrecoDesc();

    List<Produto> findTop5ByCategoriaNomeContainingIgnoreCaseOrderByPrecoAsc(String nome);

    @Query("SELECT p FROM Produto p WHERE p.preco >= :valor")
    List<Produto> buscarProdutosComValorMaiorQue(Double valor);

    @Query("SELECT p FROM Produto p ORDER BY p.preco")
    List<Produto> produtosPeloPrecoCrescente();

    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC")
    List<Produto> produtosPeloPrecoDecrescente();

    @Query("SELECT p FROM Produto p WHERE p.nome ILIKE %:letra%")
    List<Produto> produtosQueComecamCom(String letra);

    //Crie uma consulta que retorne os pedidos feitos entre duas datas.
    @Query("SELECT p FROM Produto p JOIN pedido pe WHERE pe.data BETWEEN :dataInicial AND :dataFinal")
    List<Produto> produtosEntreDatas(LocalDate dataInicial, LocalDate dataFinal);

    @Query("SELECT AVG(p.preco) FROM Produto p")
    Double mediaDePrecosProdutos();

    //7 - Crie uma consulta que retorne o preço máximo de um produto em uma categoria
    @Query("SELECT MAX(p.preco) FROM Produto p JOIN categoria c WHERE c.nome ILIKE %:categoria%")
    Double valorMaximoCategoria(String categoria);

    @Query("SELECT COUNT(p) FROM Produto p JOIN categoria c WHERE c.nome ILIKE %:nomeCategoria%")
    Integer totalProdutos(String nomeCategoria);

    @Query("SELECT p FROM Produto p JOIN categoria c WHERE p.nome ILIKE %:nomeProduto% OR c.nome ILIKE %:nomeCategoria%")
    List<Produto> produtosPorNomeOuCategoria(String nomeCategoria, String nomeProduto);

    @Query(value = "SELECT * FROM produto ORDER BY valor DESC LIMIT 5", nativeQuery = true)
    List<Produto> produtosMaisCaros();
}
