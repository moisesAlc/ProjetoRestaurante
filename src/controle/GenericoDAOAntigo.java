/*

 */
package controle;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import modelo.Cliente;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
//import org.hibernate.criterion.Restrictions;

public class GenericoDAOAntigo {

    public static void inserir(Object entidade) throws Exception {
        try {
            ConBanco.abrirConexao();
            ConBanco.getSessao().save(entidade);
            ConBanco.getTransacao().commit();
            
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erro do banco de dados", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }finally{
            ConBanco.fecharConexao();
        }
    }

    public static void atualizar(Object entidade) throws Exception {
        try {
            ConBanco.abrirConexao();
            ConBanco.getSessao().update(entidade);
            ConBanco.getTransacao().commit();
            ConBanco.fecharConexao();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erro do banco de dados", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void deletar(Object entidade) throws Exception {
        try {
            ConBanco.abrirConexao();
            ConBanco.getSessao().delete(entidade);
            ConBanco.getTransacao().commit();
            ConBanco.fecharConexao();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erro do banco de dados", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static List listarTodos(Class classe) throws Exception { // o mais simples listar
        try {
            // aqui é onde mora a facilidade do Hibernate
            ConBanco.abrirConexao();
            // estou dizendo aqui que vou fazer uma busca na classe  que está mapeada
            // no banco
            Criteria crit = ConBanco.getSessao().createCriteria(classe);

            // as restrições ficam entre
            // Criteria crit =  ConBanco.getSessao().createCriteria(classe);
            // e
            // List<Object> listaEntidades = crit.list(); 
            //Restrictions.eq() -> Restrição de igualdade
            //crit.add(Restrictions.eq());
            String tipoConta = crit.toString();
            List<Object> listaEntidades = crit.list();
            ConBanco.fecharConexao();

            if (tipoConta.contains("Funcionario")
                    && listaEntidades.isEmpty()) {
                JOptionPane.showConfirmDialog(null, "Identificamos que não "
                        + "existe nenhum funcionário nesse sistema.\n"
                        + "Deseja cadastrar um?",
                        "Nenhum funcionário encontrado.", YES_NO_OPTION,
                        QUESTION_MESSAGE);
            }

            return listaEntidades;

        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Erro do banco de dados", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
    /*
     public static Object temCpfSenha(String cpf, String senha, Character tipo)
     throws Exception {
     switch (tipo) {
     case 'a': {
     List<Administrador> listaAdministrador
     = GenericoDAOAntigo.listarTodos(Administrador.class);
     for (Administrador a : listaAdministrador) {
     if (a.getCpf().equals(cpf)
     && (a.getSenha().equals(senha))) {
     return (a);
     }
     }
     throw new Exception("Login e/ou senha de administrador"
     + "\n    não encontrado(s).");

     }
     case 'c': {
     List<Cliente> listaCliente
     = GenericoDAOAntigo.listarTodos(Cliente.class);
     for (Cliente c : listaCliente) {
     if (c.getCpf().equals(cpf)
     && (c.getSenha().equals(senha))) {
     return (c);
     }
     }
     throw new Exception("Login e/ou senha de cliente"
     + "\n  não encontrado(s).");
     }
     default: {
     throw new Exception("Caractere não indica nem"
     + " cliente nem administrador.");
     }
     }
     }
     */

    public static boolean temCliente(String cpf) {
        try {
            List<Cliente> listaCliente = GenericoDAOAntigo.listarTodos(Cliente.class);
            for (Cliente c : listaCliente) {
                if (c.getCnpj().equals(cpf)) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            Logger.getLogger(GenericoDAOAntigo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return false;
    }

    public static Cliente pegaCliente(String cpf) {
        List<Cliente> listaCliente;
        try {
            listaCliente = GenericoDAOAntigo.listarTodos(Cliente.class);
            for (Cliente c : listaCliente) {
                if (c.getCnpj().equals(cpf)) {
                    return (c);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericoDAOAntigo.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;

    }

    public static Boolean verificaSeHa(Class classe) throws Exception {
        try {
            ConBanco.abrirConexao();
            Criteria crit = ConBanco.getSessao().createCriteria(classe);
            String tipoConta = crit.toString();
            List<Object> listaEntidades = crit.list();
            ConBanco.fecharConexao();

            if (tipoConta.contains("Funcionario")
                    && listaEntidades.isEmpty()) {
                return false;
            }
            if (tipoConta.contains("Funcionario")
                    && !listaEntidades.isEmpty()) {
                return true;
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
