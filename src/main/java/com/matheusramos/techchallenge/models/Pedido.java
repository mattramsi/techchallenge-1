package com.matheusramos.techchallenge.models;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Table(name = "pedidos")
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long clienteId;

    @ManyToMany
    @JoinTable(
            name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;

    private Double total;

    @Column(name = "status_id")
    private Long statusId = 1L;
}