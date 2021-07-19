package br.com.tech4me.vendams.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.vendams.shared.VendaDto;

public interface VendaService {
    
    Optional<VendaDto> realizarVenda(VendaDto venda);
    List<VendaDto> consultarVendas();
    Optional<VendaDto> consultarVendaPorId(String id);
    void cancelarVenda(String id);
    
    

}
