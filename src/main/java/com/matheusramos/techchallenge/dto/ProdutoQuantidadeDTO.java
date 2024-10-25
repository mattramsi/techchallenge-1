package com.matheusramos.techchallenge.dto;
import lombok.Data;
@Data
public class ProdutoQuantidadeDTO {
    private Long produtoId;
    private Integer quantidade = 1;
    private Double valor;
}