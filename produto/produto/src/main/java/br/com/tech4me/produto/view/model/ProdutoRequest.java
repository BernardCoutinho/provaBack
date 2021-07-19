package br.com.tech4me.produto.view.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ProdutoRequest {
    @NotBlank(message = "Campo obrigatorio, preencha-o")
    @NotEmpty(message = "Campo obrigatório, preencha-o")
    private String nome;
    private Double valor;
    @Min(value = 1, message = "Mínimo de uma unidade")
    private int quantidadeEstoque;
    
 
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    

    
}
