package br.com.tech4me.produto.sherad;

public class ProdutoDto {
  private String id;
  private String nome;
  private double valor;
  private int quantidadeEstoque;
  
public String getNome() {
    return nome;
}
public void setNome(String nome) {
    this.nome = nome;
}
public double getValor() {
    return valor;
}
public void setValor(double valor) {
    this.valor = valor;
}
public int getQuantidadeEstoque() {
    return quantidadeEstoque;
}
public void setQuantidadeEstoque(int quantidadeEstoque) {
    this.quantidadeEstoque = quantidadeEstoque;
}
public String getId() {
    return id;
}
public void setId(String id) {
    this.id = id;
}
  

    
    
}
