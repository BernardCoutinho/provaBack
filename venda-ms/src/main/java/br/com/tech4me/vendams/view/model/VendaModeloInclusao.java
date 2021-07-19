package br.com.tech4me.vendams.view.model;

import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


public class VendaModeloInclusao {
    
    @NotBlank(message = "Campo obrigatorio, preencha-o")
    @NotEmpty(message = "Campo obrigatório, preencha-o")
    private String idProduto;
    @Min(value = 1, message = "Venda mínima: 1 unidade")
    private int quantidadeProduto;
    private LocalDate data;   
    
    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }
    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }
   
    
     
}
