package br.com.tech4me.vendams.repository;



import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.tech4me.vendams.shared.Produto;


public interface ProdutoRepository extends MongoRepository<Produto, String> {
    @Query(value="{'id' : ?0}")
    List<Produto> procurarId(String id);
}

