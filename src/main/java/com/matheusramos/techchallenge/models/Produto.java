package com.matheusramos.techchallenge.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "produto")
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private Double preco;

    @Column(name = "categoria_id")
    private Long categoriaId;

    public Produto() {
    }

    public Produto(String nome, String descricao, Double preco, Long categoriaId) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriaId = categoriaId;
    }
}
