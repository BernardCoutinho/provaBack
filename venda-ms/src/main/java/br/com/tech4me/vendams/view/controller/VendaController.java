package br.com.tech4me.vendams.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.vendams.service.VendaService;
import br.com.tech4me.vendams.shared.VendaDto;
import br.com.tech4me.vendams.view.model.VendaModeloInclusao;
import br.com.tech4me.vendams.view.model.VendaModeloRelatorio;
@RestController
@RequestMapping("/api/vendas")
public class VendaController {
    @Autowired
    private VendaService servico;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}")String porta){
        return String.format("Serviço ativo e executando na porta %s", porta);

    }

    @GetMapping
    public ResponseEntity<List<VendaModeloRelatorio>> consultarVendas(){
        List<VendaDto> dtos = servico.consultarVendas();
        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        ModelMapper mapper = new ModelMapper();
        List<VendaModeloRelatorio> relat = dtos.stream()
        .map(d -> mapper.map(d, VendaModeloRelatorio.class)).collect(Collectors.toList());

        return new ResponseEntity<>(relat, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<VendaModeloRelatorio> consultarVendaPorId(@PathVariable String id) {
        Optional<VendaDto> vend = servico.consultarVendaPorId(id);

        if(vend.isPresent()) 
        {
            return new ResponseEntity<>(
                new ModelMapper().map(vend.get(), VendaModeloRelatorio.class), 
                HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> cancelarVenda(@PathVariable String id) 
    {
        servico.cancelarVenda(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
    
    @PostMapping
    public ResponseEntity<VendaModeloRelatorio> realizarVenda(@RequestBody @Valid VendaModeloInclusao venda){
        ModelMapper mapa  = new ModelMapper();
        VendaDto dto = mapa.map(venda, VendaDto.class); 
        Optional<VendaDto> op = servico.realizarVenda(dto); 
        
         if(op.isPresent())
         {
            return new ResponseEntity<>(mapa.map(op.get(), VendaModeloRelatorio.class), HttpStatus.CREATED); 
         } 
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

        
}


