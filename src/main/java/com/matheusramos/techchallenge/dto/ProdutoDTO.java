package com.matheusramos.techchallenge.dto;
import com.matheusramos.techchallenge.models.Categoria;
import lombok.Data;

@Data
public class ProdutoDTO {
    private Long id;
    private String nome;
    private Double preco;
    private Categoria categoria;

    public ProdutoDTO(Long id, String nome, Double preco, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }
}