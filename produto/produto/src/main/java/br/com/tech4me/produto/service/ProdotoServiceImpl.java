package br.com.tech4me.produto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.produto.model.Produto;
import br.com.tech4me.produto.repository.ProdutoRepository;
import br.com.tech4me.produto.sherad.ProdutoDto;


@Service
public class ProdotoServiceImpl implements ProdutoService{

    @Autowired
    private ProdutoRepository repositorio;

   

    @Override
    public List<ProdutoDto> obterTodos() {
        List<Produto> produto = repositorio.findAll();

        return produto.stream()
        .map(p -> new ModelMapper().map(p , ProdutoDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public ProdutoDto cadastrarProduto(ProdutoDto produto) {
        ModelMapper mapa = new ModelMapper();
        Produto pro = mapa.map(produto, Produto.class);
        pro = repositorio.save(pro);
        return mapa.map(pro, ProdutoDto.class);
    }

    @Override
    public void removerProduto(String id) {
        
        repositorio.deleteById(id);
        
    }

    @Override
    public Optional<ProdutoDto> obterPorId(String id) {
        Optional<Produto> prod = repositorio.procurarId(id);


        if (prod.isPresent()) {
            ProdutoDto prodDto = new ModelMapper().map(prod.get(), ProdutoDto.class);
            
          return Optional.of(prodDto);
        }
        return Optional.empty();
    }

    @Override
    public ProdutoDto atualizarProduto(String id, ProdutoDto produto) {
        ModelMapper mapa = new ModelMapper();
        Produto prod = mapa.map(produto, Produto.class);
        prod.setId(id);
        prod = repositorio.save(prod);

        return mapa.map(prod, ProdutoDto.class);
    }
}
