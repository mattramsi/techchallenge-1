package com.matheusramos.techchallenge.repositories;

import com.matheusramos.techchallenge.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}
