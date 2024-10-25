package com.matheusramos.techchallenge.controllers;

import com.matheusramos.techchallenge.dto.ProdutoDTO;
import com.matheusramos.techchallenge.models.Produto;
import com.matheusramos.techchallenge.models.Categoria;
import com.matheusramos.techchallenge.services.CategoriaService;
import com.matheusramos.techchallenge.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;

    public ProdutoController(ProdutoService produtoService, CategoriaService categoriaService) {
        this.produtoService = produtoService;
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.criarProduto(produto));
    }

    @PostMapping("/categoria")
    public ResponseEntity<List<ProdutoDTO>> buscarPorCategoria(@RequestBody Map<String, Long> requestBody) {
        Long categoriaId = requestBody.get("id");
        List<Produto> produtos = produtoService.buscarPorCategoria(categoriaId);

        // Converter Produto para ProdutoDTO
        List<ProdutoDTO> produtoDTOs = produtos.stream()
                .map(produto -> {
                    Categoria categoria = categoriaService.buscarPorId(produto.getCategoriaId()).orElse(null);
                    return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco(), categoria);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(produtoDTOs);
    }
}