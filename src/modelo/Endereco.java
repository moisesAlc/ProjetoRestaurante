package modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "tbEndereco")

public class Endereco implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // autoincremento
    private Integer id;
    @Column
    private String logradouro; 
    @Column // é um atributo da minha própria classe
    private String bairro;
            
    // p/ fazer o relacionamento de um para um:
     // temos que escolher agora uma table que seria a "classe pai".
    // no caso, vemos que o endereço depende do funcionario, portanto, ao
    // deletar um funcionário, teremos que tirar o endereço em cascata
    // Para isso usamos a propriedade cascade
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable=false)
    private Cliente cliente;
    //@JoinColumn(unique = true, nullable = false) 
            // para mudar um atributo de uma chave estrangeira nós 
            //não usamos o @Column, usamos o JoinColumn
    
    @Column        
    private Integer numero;
    
    @Column
    private String complemento;

    public Endereco() {
    }

    public Endereco(Integer id, String rua, String bairro, Integer numero) {
        this.id = id;
        this.logradouro = rua;
        this.bairro = bairro;
        this.numero = numero;
    }
  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
       
}
