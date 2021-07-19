package br.com.tech4me.vendams.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.vendams.model.Venda;
import br.com.tech4me.vendams.repository.ProdutoRepository;
import br.com.tech4me.vendams.repository.VendaRepository;
import br.com.tech4me.vendams.shared.Produto;
import br.com.tech4me.vendams.shared.VendaDto;

@Service
public class VendaServiceImpl implements VendaService{
    @Autowired 
    private VendaRepository repositorio;
    @Autowired
    private ProdutoRepository repositorioProduto;

    

    @Override
    public List<VendaDto> consultarVendas() {
        List<Venda> vendas = repositorio.findAll();
        
        return vendas.stream()
        .map(v -> new ModelMapper().map(v, VendaDto.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<VendaDto> consultarVendaPorId(String id) {
        Optional<Venda> vend = repositorio.findById(id);

        if(vend.isPresent()) {
            VendaDto dto = new ModelMapper().map(vend.get(), VendaDto.class);
            
            return Optional.of(dto);
        }
 
        return Optional.empty();
     
        
        
    }

    @Override
    public  void cancelarVenda(String id) {
        Optional<Venda> venda = repositorio.findById(id);
        
        List<Produto> produtos = repositorioProduto.findAll();
        if(venda.isPresent())
        {
            for(Produto produto : produtos)
            {
                if(produto.getId().equals(venda.get().getIdProduto()))
                {

                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque()+venda.get().getQuantidadeProduto());
                repositorioProduto.save(produto);        
                repositorio.deleteById(id);
            
                }           
            }      
        }
        
    }

    @Override
    public Optional<VendaDto> realizarVenda(VendaDto venda) {
        ModelMapper mapper = new ModelMapper();
        Venda vend = mapper.map(venda, Venda.class);
        
        List<Produto> produtos = repositorioProduto.findAll();
        

        for(Produto produto : produtos){
            if(produto.getId().equals(venda.getIdProduto())){
                
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque()-venda.getQuantidadeProduto());
                repositorioProduto.save(produto);
                vend.setProdutoNome(produto.getNome());
                vend.setValorTotal(venda.getQuantidadeProduto() * produto.getValor());
                vend = repositorio.save(vend);


                return Optional.of(mapper.map(vend, VendaDto.class));
            }

        }
        
        return Optional.empty();
    }

    

    
}
