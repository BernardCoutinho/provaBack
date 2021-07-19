package br.com.tech4me.produto.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.produto.sherad.ProdutoDto;

public interface ProdutoService {
    List<ProdutoDto> obterTodos();
    ProdutoDto cadastrarProduto(ProdutoDto pessoa);
    void removerProduto(String id);
    Optional<ProdutoDto> obterPorId(String id);
    ProdutoDto atualizarProduto(String id, ProdutoDto pessoa);
}
