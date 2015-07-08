/*
Um cliente terá, nome, CPF ,endereço, telefone principal e telefone secundário.
O cliente será identificado pelo seu número de telefone principal. Todos os 
campos com exceção de telefone secundário, são obrigatórios.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "tbClientes")
public class Cliente implements Serializable{
    @Column
    private String nome;                // OBRIGATÓRIO
    @Column
    private String cnpj;                 // OBRIGATÓRIO
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.PERSIST)//, fetch = FetchType.LAZY)
    private Endereco endereco;          // OBRIGATÓRIO
    @Id
    private String telefonePrincipal;   // CHAVE PRIMÁRIA
    @Column (nullable=true)
    private String telefoneSecundario;  // NÃO OBRIGATÓRIO

    public Cliente() {
    }

    public Cliente(String nome, String cpf, Endereco endereco, String telefonePrincipal, String telefoneSecundario) {
        this.nome = nome;
        this.cnpj = cpf;
        this.endereco = endereco;
        this.telefonePrincipal = telefonePrincipal;
        this.telefoneSecundario = telefoneSecundario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefonePrincipal() {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(String telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
    }

    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

}