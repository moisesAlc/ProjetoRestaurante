/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

public class Entrega {
    private Cliente     cliente;
    private ArrayList   produtos;
    private Calendar    dataEntrega;
    private BigDecimal  valorTotal;
    private BigDecimal  valorDisponivelDoCliente;
    private BigDecimal  troco;

    public Entrega() {
    }

    public Entrega(Cliente cliente, ArrayList produtos, Calendar dataEntrega, BigDecimal valorTotal, BigDecimal valorDisponivelDoCliente, BigDecimal troco) {
        this.cliente = cliente;
        this.produtos = produtos;
        this.dataEntrega = dataEntrega;
        this.valorTotal = valorTotal;
        this.valorDisponivelDoCliente = valorDisponivelDoCliente;
        this.troco = troco;
    }
    
    
}
