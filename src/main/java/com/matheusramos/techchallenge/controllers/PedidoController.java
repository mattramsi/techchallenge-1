package com.matheusramos.techchallenge.controllers;

import com.matheusramos.techchallenge.Exceptions.ResourceNotFoundException;
import com.matheusramos.techchallenge.dto.PedidoDTO;
import com.matheusramos.techchallenge.dto.ProdutoQuantidadeDTO;
import com.matheusramos.techchallenge.models.Pedido;
import com.matheusramos.techchallenge.models.Produto;
import com.matheusramos.techchallenge.services.PedidoService;
import com.matheusramos.techchallenge.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ProdutoService produtoService;

    @Autowired // Injeção automática do serviço
    public PedidoController(PedidoService pedidoService, ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.produtoService = produtoService; // Agora temos uma referência ao ProdutoService
    }
    @PostMapping("/checkout")
    public ResponseEntity<Pedido> checkout(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setClienteId(pedidoDTO.getClienteId());
        pedido.setTotal(pedidoDTO.getTotal());

        // Mapeia cada DTO para Produto
        List<Produto> produtos = pedidoDTO.getProdutos().stream()
                .map(dto -> {
                    // Busca o produto como um Optional
                    Optional<Produto> optionalProduto = produtoService.buscarPorId(dto.getProdutoId());

                    // Verifica se o produto existe
                    Produto produto = optionalProduto.orElseThrow(() ->
                            new ResourceNotFoundException("Produto não encontrado: " + dto.getProdutoId())
                    );

                    // Aqui você pode definir a quantidade como desejar, mas precisa ajustar a lógica
                    // Adicione um método setQuantidade em Produto, caso necessário
                    // Exemplo: produto.setQuantidade(dto.getQuantidade() == 0 ? 1 : dto.getQuantidade());

                    return produto;
                })
                .collect(Collectors.toList());

        pedido.setProdutos(produtos);

        // Salva o pedido usando o serviço
        return ResponseEntity.ok(pedidoService.checkout(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }
}