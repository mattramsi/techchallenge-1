package com.matheusramos.techchallenge.controllers;

import com.matheusramos.techchallenge.dto.CpfRequest;
import com.matheusramos.techchallenge.models.Cliente;
import com.matheusramos.techchallenge.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.cadastrarCliente(cliente));
    }

    @PostMapping("/buscarPorCpf")
    public ResponseEntity<Cliente> buscarPorCpf(@RequestBody CpfRequest cpfRequest) {
        return clienteService.buscarPorCpf(cpfRequest.getCpf())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}