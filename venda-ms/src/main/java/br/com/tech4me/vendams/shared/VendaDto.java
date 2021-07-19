package br.com.tech4me.vendams.shared;

import java.time.LocalDate;


public class VendaDto {
    private String id; 
    private String idProduto;    
     private String produtoNome;
     private int quantidadeProduto;
     private LocalDate data;
     private Double valorTotal;
    
    
     
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }
    
    public String getProdutoNome() {
        return produtoNome;
    }
    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }
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
   
    
    public Double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
     
     
     

    
}
