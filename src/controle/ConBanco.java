/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utilitarios.HibernateUtil;

/**
 *
 * @author aluno
 */
public class ConBanco {

    static Session sessao;
    static Transaction transacao;

    public static void abrirConexao() {
        if (ConBanco.sessao == null || !ConBanco.sessao.isOpen()) {
            ConBanco.sessao = HibernateUtil.getSessionFactory().openSession();
            ConBanco.transacao = ConBanco.sessao.beginTransaction();
        }
    }

    public static void fecharConexao() {
        if (ConBanco.sessao != null && ConBanco.sessao.isOpen()) {
            ConBanco.sessao.close();
        }
    }

    public static Session getSessao() {
        return sessao;
    }

    public static void setSessao(Session sessao) {
        ConBanco.sessao = sessao;
    }

    public static Transaction getTransacao() {
        return transacao;
    }

    public static void setTransacao(Transaction transacao) {
        ConBanco.transacao = transacao;
    }
}
