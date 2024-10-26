package com.matheusramos.techchallenge.models;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "cpf"))
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String email;
}
