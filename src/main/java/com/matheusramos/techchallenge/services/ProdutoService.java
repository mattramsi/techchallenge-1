package com.matheusramos.techchallenge.services;

import com.matheusramos.techchallenge.models.Categoria;
import com.matheusramos.techchallenge.models.Produto;
import com.matheusramos.techchallenge.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto editarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void removerProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<Produto> buscarPorCategoria(Long id) {
        return produtoRepository.findByCategoriaId(id);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }
}
