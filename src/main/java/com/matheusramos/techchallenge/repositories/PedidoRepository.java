package com.matheusramos.techchallenge.repositories;

import com.matheusramos.techchallenge.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}