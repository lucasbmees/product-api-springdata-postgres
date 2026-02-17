package com.example.gerenciador_produtos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Produto> produtosPedido;

    public Pedido() {
    }

    public Pedido( LocalDate data) {

        this.data = data;
        this.produtosPedido = new ArrayList<>();
    }

    public List<Produto> getProdutosPedido() {
        return produtosPedido;
    }

    public void setProdutosPedido(List<Produto> produtosPedido) {
        this.produtosPedido = produtosPedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
