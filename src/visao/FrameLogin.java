/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;
/*
import controle.ConBanco;
import controle.Fabrica;
import controle.GenericoDAO;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import modelo.Funcionario;

public class FrameLogin extends JFrame {

    public FrameLogin() throws Exception {
        super("Login");
        ConBanco.abrirConexao();
        fabrica = new Fabrica();
        JOptionPane.showMessageDialog(null, 
                "Banco de Dados usado:\n" + fabrica.mostraBD());
        if (GenericoDAO.verificaSeHa(Funcionario.class) == false) {
            int resposta = JOptionPane.showConfirmDialog(null, "Identificamos "
                    + "que não existe nenhum funcionário nesse sistema.\n"
                    + "Deseja cadastrar um?", "Nenhum funcionário encontrado.", 
                    YES_NO_OPTION,
                    QUESTION_MESSAGE);
            switch(resposta){
                // não
                case 1:
                // fechou janela
                case -1: System.exit(0);
                case 0: {
                    this.dispose();
                    new FrameFuncionario().setVisible(true);
                    break;
                }
            }
        } else if (GenericoDAO.verificaSeHa(Funcionario.class) == true) {
            this.dispose();
            new FramePrincipal().setVisible(true);
        } else if (GenericoDAO.verificaSeHa(Funcionario.class) == null) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar verificar"
                    + "se havia funcionário no banco de dados.",
                    "Erro do banco de dados", JOptionPane.ERROR_MESSAGE);
        }
        inicializa();
        ConBanco.fecharConexao();
    }

    private void inicializa() {
        setLookAndFeel();
        g = new GridBagLayout();
        painel = new JPanel(g);

        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        lblLogin = new JLabel("Login");
        painel.add(lblLogin, c);

        c.gridx = 1;
        c.gridy = 0;
        lblSenha = new JLabel("Senha");
        painel.add(lblSenha, c);

        c.gridx = 0;
        c.gridy = 1;
        txtLogin = new JTextField();
        txtLogin.setColumns(12);
        painel.add(txtLogin, c);

        c.gridx = 1;
        c.gridy = 1;
        txtSenha = new JPasswordField();
        txtSenha.setColumns(12);
        painel.add(txtSenha, c);

        c.gridx = 0;
        c.gridy = 2;
        btnOk = new JButton("Ok");
        painel.add(btnOk, c);
        c.gridx = 1;
        c.gridy = 2;
        btnCancelar = new JButton("Cancelar");
        painel.add(btnCancelar, c);

        setContentPane(painel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) {
            // ignora erro
        }
    }
    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JLabel lblLogin;
    private JLabel lblSenha;
    private JButton btnOk;
    private JButton btnCancelar;
    private JPanel painel;
    private GridBagLayout g;
    private GridBagConstraints c;
    private Fabrica fabrica;
}*/