package com.example.gerenciador_produtos.repository;

import com.example.gerenciador_produtos.model.Categoria;
import com.example.gerenciador_produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(String nomeCategoria);

    @Query("SELECT c FROM Categoria c JOIN c.produtos p GROUP BY c.id HAVING COUNT(p) >= 2")
    List<Categoria> categoriasComMaisDe2Produtos();

}
