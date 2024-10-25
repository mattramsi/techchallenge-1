package com.matheusramos.techchallenge.services;

import com.matheusramos.techchallenge.models.Pedido;
import com.matheusramos.techchallenge.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido checkout(Pedido pedido) {
        // Lógica para enviar pedido para a fila (Fake checkout)
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}