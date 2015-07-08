/*
 Para produtos:
Um produto terá, código do produto, nome, valor e um indicador que poderá
estar ou não disponível para entrega. Todos os campos são obrigatórios.
 */

package modelo;

import java.math.BigDecimal;

public class Produto {
    private Integer codigo;
    private String nome;
    private BigDecimal valor;
    private Boolean disponivelParaEntrega;

    public Produto() {
    }

    public Produto(Integer codigo, String nome, BigDecimal valor, Boolean disponivelParaEntrega) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.disponivelParaEntrega = disponivelParaEntrega;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getDisponivelParaEntrega() {
        return disponivelParaEntrega;
    }

    public void setDisponivelParaEntrega(Boolean disponivelParaEntrega) {
        this.disponivelParaEntrega = disponivelParaEntrega;
    }
    
    
}
