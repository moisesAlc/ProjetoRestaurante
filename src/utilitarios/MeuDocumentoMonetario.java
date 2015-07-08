/*
 * MonetarioDocument.java
 *
 * Formata uma caixa de texto para aparecer [0,00] quando vazia.
 * Ao inserir dados, preenche da direita para esquerda conservando
 * apenas os zeros iniciais e o cursor sempre a direita da vírgula.
 * Por exemplo, para digitar o numero 10,20:
 *
 * [ 0,00|]
 * [ 0,01|]
 * [ 0,10|]
 * [ 1,02|]
 * [ 10,20|]
 *
 * Também permite apagar os números na ordem inversa, mantendo os
 * zeros iniciais. Por exemplo, para apagar 10,20:
 *
 * [ 10,20|]
 * [ 1,02|]
 * [ 0,10|]
 * [ 0,01|]
 * [ 0,00|]
 *
 * Para usar esta classe, apenas crie uma caixa de texto formatada e
 * configure uma instância dessa classe como atributo tipo Document.
 * Melhor também alinhar a caixa a direita. Por exemplo:
 *
 * FormattedTextField valor = new FormattedTextField();
 * valor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
 * valor.setDocument(new MonetarioDocument());
 *
 * Obs.: a caixa texto permite apenas 12 digitos.
 *
 */
package utilitarios;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MeuDocumentoMonetario extends PlainDocument {

    public static final int NUMERO_DIGITOS_MAXIMO = 12;

    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {

        String texto = getText(0, getLength()); // pega o texto todo

        for (int i = 0; i < str.length(); i++) { // para cada catactere
            char c = str.charAt(i);              // transforma em char
            if (!Character.isDigit(c)) {         // se não for dígito
                return;
            }
        }

        if (texto.length() < this.NUMERO_DIGITOS_MAXIMO) { //se o tamanho do texto
            // for menor que 12
            super.remove(0, getLength());
            texto = texto.replace(".", "").replace(",", "");
            StringBuffer s = new StringBuffer(texto + str);

            if (s.length() > 0 && s.charAt(0) == '0') {
                s.deleteCharAt(0);
            }

            if (s.length() < 3) {
                if (s.length() < 1) {
                    s.insert(0, "000");
                } else if (s.length() < 2) {
                    s.insert(0, "00");
                } else {
                    s.insert(0, "0");
                }
            }

            s.insert(s.length() - 2, ",");

            if (s.length() > 6) {
                s.insert(s.length() - 6, ".");
            }

            if (s.length() > 10) {
                s.insert(s.length() - 10, ".");
            }

            super.insertString(0, s.toString(), a);
        }
    }

    public void remove(int offset, int length) throws BadLocationException {
        super.remove(offset, length);
        String texto = getText(0, getLength());
        texto = texto.replace(",", "");
        texto = texto.replace(".", "");
        super.remove(0, getLength());
        insertString(0, texto, null);
    }

}
