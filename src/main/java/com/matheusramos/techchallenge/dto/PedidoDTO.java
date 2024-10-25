package com.matheusramos.techchallenge.dto;

import lombok.Data;
import java.util.List;

@Data
public class PedidoDTO {
    private Long clienteId; // ID do cliente, pode ser nulo
    private List<ProdutoQuantidadeDTO> produtos; // Lista de produtos com quantidade
    private Double total; // Total do pedido

    // Construtor
    public PedidoDTO() {
        
    }
    public PedidoDTO(Long clienteId, List<ProdutoQuantidadeDTO> produtos, Double total) {
        this.clienteId = clienteId; // clienteId pode ser nulo
        this.produtos = produtos;
        this.total = total;
    }
}