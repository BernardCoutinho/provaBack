package br.com.tech4me.produto.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.tech4me.produto.model.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
    @Query(value="{'id' : ?0}")
    Optional<Produto> procurarId(String id);
}
