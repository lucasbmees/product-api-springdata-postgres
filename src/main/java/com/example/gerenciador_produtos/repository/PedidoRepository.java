package com.example.gerenciador_produtos.repository;

import com.example.gerenciador_produtos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByDataIsNull();
    List<Pedido> findByDataIsNotNull();
    List<Pedido> findByDataAfter(LocalDate localDate);
    List<Pedido> findByDataBefore(LocalDate localDate);
    List<Pedido> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
}
