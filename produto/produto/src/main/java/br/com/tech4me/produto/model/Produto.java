package br.com.tech4me.produto.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("produto")
public class Produto {
    @Id
    private String id;
    private double valor;
    private String nome;
    private int quantidadeEstoque;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    
    /*@Override
    public atualizarEstoque(){
        int quantididadeAux = quantidadeEstoque - quantidadeVendida;
        quantidadeEstoque = quantidadeAux.save(repositorio);
    }*/

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", quantidadeEstoque=" + quantidadeEstoque + ", valor="
                + valor + "]";
    }
    
    
}
