/*
 * Frame que cuidará do CRUD do cliente
 */
package visaoteste;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class FrameCliente extends JFrame {

    public FrameCliente() {
        super("Formulário de Clientes");
        // setLookAndFeel();
        confInicialGridBag();
        componentesEmHashMap();
        inicializa();
    }

    private void inicializa() {

        // Seta 2ª coluna no GridBagConstraints
        c.gridx = 0;

        // Põe todos as labels referentes ao cliente na 1ª linha.
        for (int i = 0; i <= 7; i++) {
            c.gridy = i;
            painelEsq.add(hashComp.get("lbl" + campos[i]), c);
        }

        // Seta 2ª coluna no GridBagConstraints
        c.gridx = 1;

        // Põe todos os textfields referentes ao cliente na 2ª linha
        // e configura a largura.
        for (int i = 0; i <= 7; i++) {
            c.gridy = i;
            JTextField t = (JTextField) hashComp.get("txt" + campos[i]);
            t.setColumns(20);
            t.setSize(146, 25);
            t.setMargin(new Insets(1, 1, 1, 1));
            painelEsq.add(hashComp.get("txt" + campos[i]), c);
        }

        // Seta 3ª coluna no GridBagConstraints
        c.gridx = 2;

        // Põe todos os botões referentes ao crud do cliente na 3ª linha
        for (int i = 0; i <= 7; i++) {
            c.gridy = i;
            JButton b = (JButton) hashComp.get("btn" + botoes[i]);
            painelEsq.add(hashComp.get("btn" + botoes[i]), c);
        }


        /*
         c.gridy = 0;
         c.gridx = 0;
         c.gridy = 2;
         btnOk = new JButton("Ok");

         painelEsq.add(btnOk, c);
         c.gridx = 1;
         c.gridy = 2;
         btnCancelar = new JButton("Cancelar");

         painelEsq.add(btnCancelar, c);
         */
        setContentPane(painelEsq);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
        System.out.println(lblNome.getBounds());
        System.out.println(lblTelefoneSecundario.getBounds());
    }

    private void confPainelDir(){
        painelDir = new JPanel(g);
        //tabela = new JTable();
    }
    
    
    private void confInicialGridBag() {
        g = new GridBagLayout();
        painelEsq = new JPanel(g);
        painelDir = new JPanel(g);
        painelTotal = new JPanel(g);
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
    }

    // 
    private void componentesEmHashMap() {
        campos[0] = "Nome";
        campos[1] = "Cpf";
        campos[2] = "Rua";
        campos[3] = "Bairro";
        campos[4] = "Numero";
        campos[5] = "Complemento";
        campos[6] = "TelefonePrincipal";
        campos[7] = "TelefoneSecundario";

        botoes[0] = "Ok";
        botoes[1] = "Cancelar";
        botoes[2] = "Inserir";
        botoes[3] = "Alterar";
        botoes[4] = "Consultar";
        botoes[5] = "Deletar";
        botoes[6] = "MostraTabela";
        botoes[7] = "OcultaTabela";

        hashComp.put("lblNome", lblNome);
        hashComp.put("lblCpf", lblCpf);
        hashComp.put("lblRua", lblRua);
        hashComp.put("lblBairro", lblBairro);
        hashComp.put("lblNumero", lblNumero);
        hashComp.put("lblComplemento", lblComplemento);
        hashComp.put("lblTelefonePrincipal", lblTelefonePrincipal);
        hashComp.put("lblTelefoneSecundario", lblTelefoneSecundario);

        hashComp.put("txtNome", txtNome);
        hashComp.put("txtCpf", txtCpf);
        hashComp.put("txtRua", txtRua);
        hashComp.put("txtBairro", txtBairro);
        hashComp.put("txtNumero", txtNumero);
        hashComp.put("txtComplemento", txtComplemento);
        hashComp.put("txtTelefonePrincipal", txtTelefonePrincipal);
        hashComp.put("txtTelefoneSecundario", txtTelefoneSecundario);

        hashComp.put("btnOk", btnOk);
        hashComp.put("btnCancelar", btnCancelar);
        hashComp.put("btnInserir", btnInserir);
        hashComp.put("btnAlterar", btnAlterar);
        hashComp.put("btnConsultar", btnConsultar);
        hashComp.put("btnDeletar", btnDeletar);
        hashComp.put("btnMostraTabela", btnMostraTabela);
        hashComp.put("btnOcultaTabela", btnOcultaTabela);

    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception exc) {
            // ignora erro
        }
    }
    //***************************//
    //  DECLARAÇÃO DE VARIÁVEIS  //
    //***************************//
    private HashMap<String, JComponent> hashComp = new HashMap<>();
    private JLabel lblNome = new JLabel("Nome");
    private JLabel lblCpf = new JLabel("Cpf");
    private JLabel lblRua = new JLabel("Rua");
    private JLabel lblBairro = new JLabel("Bairro");
    private JLabel lblNumero = new JLabel("Número");
    private JLabel lblComplemento = new JLabel("Complemento");
    private JLabel lblTelefonePrincipal = new JLabel("Telefone Principal");
    private JLabel lblTelefoneSecundario = new JLabel("Telefone Secundário");
    private JTextField txtNome = new JTextField();
    private JTextField txtCpf = new JTextField();
    private JTextField txtRua = new JTextField();
    private JTextField txtBairro = new JTextField();
    private JTextField txtNumero = new JTextField();
    private JTextField txtComplemento = new JTextField();
    private JTextField txtTelefonePrincipal = new JTextField();
    private JTextField txtTelefoneSecundario = new JTextField();
    private String[] campos = new String[8];
    private String[] botoes = new String[8];
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancelar = new JButton("Cancelar");
    private JButton btnInserir = new JButton("Inserir");
    private JButton btnAlterar = new JButton("Alterar");
    private JButton btnConsultar = new JButton("Consultar");
    private JButton btnDeletar = new JButton("Deletar");
    private JButton btnMostraTabela = new JButton("Mostra Tabela");
    private JButton btnOcultaTabela = new JButton("Oculta Tabela");
    private JPanel painelEsq;
    private JPanel painelDir;
    private JPanel painelTotal;
    /*
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.util.List<visao.TbCliente> tbClienteList;
    private javax.persistence.Query tbClienteQuery;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    private JTable tabela;
    */
    private GridBagLayout g;
    private GridBagConstraints c;
}
