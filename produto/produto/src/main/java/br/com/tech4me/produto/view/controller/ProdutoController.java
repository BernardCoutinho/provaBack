package br.com.tech4me.produto.view.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

import br.com.tech4me.produto.service.ProdutoService;
import br.com.tech4me.produto.sherad.ProdutoDto;
import br.com.tech4me.produto.view.model.ProdutoRequest;
import br.com.tech4me.produto.view.model.ProdutoResponse;




@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    @Autowired
    ProdutoService servico;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodosProdutos() {
        ModelMapper mapa = new ModelMapper();
        List<ProdutoDto> prodDtos = servico.obterTodos();
        List<ProdutoResponse> prodResponse = prodDtos.stream()
            .map(pes -> mapa.map(pes, ProdutoResponse.class))
            .collect(Collectors.toList());

        return new ResponseEntity<>(prodResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> obterUmProduto(@PathVariable String id){
        Optional<ProdutoDto> prod = servico.obterPorId(id);

        if (prod.isPresent()) {
            return new ResponseEntity<>(new ModelMapper().map(prod.get(), ProdutoResponse.class), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/status")
    public String status(@Value("${local.server.port}") String porta){
        return String.format("Serviço rodando na porta %s", porta);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criarUmaProduto(@RequestBody @Valid ProdutoRequest produto) {
        ModelMapper mapa = new ModelMapper();
        ProdutoDto prodDto = mapa.map(produto, ProdutoDto.class);
        prodDto = servico.cadastrarProduto(prodDto);
        return new ResponseEntity<>(mapa.map(prodDto, ProdutoResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> remover(@PathVariable String id){
        Optional<ProdutoDto> prod = servico.obterPorId(id);
        if(prod.isPresent()){
            servico.removerProduto(id);
        return new ResponseEntity<>("Produto exluido com sucesso.",HttpStatus.NO_CONTENT); 
        } 
        return new ResponseEntity<>("Produto não encontrado",HttpStatus.NOT_FOUND); 
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable String id, @RequestBody @Valid ProdutoRequest produto) {
        ModelMapper mapa = new ModelMapper();
        ProdutoDto prodDto = mapa.map(produto, ProdutoDto.class);
        prodDto = servico.atualizarProduto(id, prodDto);
        return new ResponseEntity<>(mapa.map(prodDto, ProdutoResponse.class), HttpStatus.OK);
    }
}