/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilitarios;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTabela extends AbstractTableModel{

    private ArrayList linhas = null;
    private String[] colunas = null;
    
    public ModeloTabela(ArrayList lin, String[] col){
        setLinhas(lin);
        setColunas(col);
    }
    
    public ArrayList getLinhas(){
        return this.linhas;
    }
    public String[] getColunas(){
        return this.colunas;
    }
    public void setLinhas(ArrayList l){
        this.linhas = l;
    }
    public void setColunas(String[] c){
        this.colunas = c;
    }
    
    public String getColumnName(int index){
        return colunas[index];
    }
    
    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int indiceLinha, int indiceColuna) {
        Object[] linhaObj = (Object[]) getLinhas().get(indiceLinha);
        return linhaObj[indiceColuna];
    }
    
}
