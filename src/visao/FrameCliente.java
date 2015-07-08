/*
 * Frame que cuidará do CRUD do cliente
 */
package visao;

import controle.Fabrica;
import controle.GenericoDAOAntigo;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import modelo.Cliente;
import modelo.Endereco;
import utilitarios.ModeloTabela;

public class FrameCliente extends JFrame implements ActionListener {

    public FrameCliente() throws ParseException {
        super("Formulário de Clientes");
        iniciaGridBag();
        todosComponentesNoHashMap();
        confPainelEsq();
        configuraContainer();
        configuraJanela();
    }

    private void iniciaGridBag() {
        g = new GridBagLayout();
        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
    }

    // Coloca labels, textfields e botões na 1ª, 2ª e 3ª colunas.
    private void confPainelEsq() throws ParseException {
        painelEsq = new JPanel(g);
        fabrica = new Fabrica();
        c.gridx = 0;                // 1ª coluna
        configuraJLabels();         // jlabels na 1ª coluna
        c.gridx = 1;                // 2ª coluna
        configuraCamposDeTexto();   // textfields na 2ª coluna
        c.ipady = 0;                // normaliza a altura dos componentes
        c.gridx = 2;                // 3ª coluna
        configuraBotoes();          // botões na 3ª coluna
    }

    private void configuraContainer() {
        container = new Container();
        borderLayout = new BorderLayout();
        container.setLayout(borderLayout);
        container.add(painelEsq, BorderLayout.CENTER);
        //container.add(painelDir);
        setContentPane(container);
    }

    private void configuraJanela() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void configuraJLabels() {
        // Põe todos as labels referentes ao cliente na 1ª linha.
        for (int i = 0; i <= 7; i++) {
            c.gridy = i;
            painelEsq.add(hashComp.get("lbl" + campos[i]), c);
        }
    }

    private void configuraCamposDeTexto() throws ParseException {

        configuraMáscaras();

        for (int i = 0; i <= 7; i++) {
            JFormattedTextField t
                    = (JFormattedTextField) hashComp.get("txt" + campos[i]);
            if (i == 7 || i == 6) {
                c.ipady = 3;       // Configura a largura.
            }
            c.gridy = i;
            t.setColumns(20);
            t.setValue("");
            t.setSize(146, 25);
            t.setMargin(new Insets(1, 1, 1, 1));
            if (i == 1) {
                formatoCnpj.install(t);
                t.setName("cnpj");
            }
            if (i == 4) {
                formatoNumero.install(t);
                t.setName("numero");
            }

            if (i == 6 || i == 7) {
                if (i == 6) {
                    formatoTelefonePrimario.install(t);
                    t.setName("telefonePrimario");
                } else if (i == 7) {
                    formatoTelefoneSecundario.install(t);
                    t.setName("telefoneSecundario");
                }
            }
            painelEsq.add(t, c);
        }

    }

    private void configuraMáscaras() throws ParseException {

        // configura a máscara do cnpj 
        formatoCnpj = new MaskFormatter("##.###.###/####-##");
        formatoCnpj.setPlaceholder("  .   .   /    -  ");
        //formatoCnpj.setPlaceholderCharacter('_');
        formatoCnpj.setValueContainsLiteralCharacters(true);

        // configura a máscara do número
        formatoNumero = new MaskFormatter("######");
        formatoNumero.setPlaceholder("      ");
        //formatoNumero.setPlaceholderCharacter('_');
        formatoNumero.setValueContainsLiteralCharacters(true);

        // configura a máscara do telefone principal
        formatoTelefonePrimario = new MaskFormatter("(##)####.####");
        formatoTelefonePrimario.setPlaceholder("(  )    .    ");
        //formatoTelefonePrimario.setPlaceholderCharacter('_');
        formatoTelefonePrimario.setValueContainsLiteralCharacters(true);

        // configura a máscara do telefone secundário
        formatoTelefoneSecundario = new MaskFormatter("(##)####.####");
        formatoTelefoneSecundario.setPlaceholder("(  )    .    ");
        formatoTelefoneSecundario.setValueContainsLiteralCharacters(true);
    }

    private void configuraBotoes() {
        // Põe botões na 3ª linha percorrendo a array botoes.
        for (int i = 0; i <= 4; i++) {
            c.gridy = i;
            painelEsq.add(hashComp.get("btn" + botoes[i]), c);
        }
    }
    /*
     private void preencherTabela() throws Exception {
     ArrayList registros = new ArrayList();
     String[] colunas = {"Nome", "Cnpj", "Logradouro", "Bairro", "Número", "Compl.",
     "Tel Principal", "Tel Secundário"};
     registros = (ArrayList) GenericoDAOAntigo.listarTodos(Cliente.class);
     ModeloTabela modTabela = new ModeloTabela(registros, colunas);
     tabela.setModel(modTabela);
     //pack();
     }

     private void colocaTabela() throws Exception {
     painelDir = new JPanel();
     tabela = new JTable();
     preencherTabela();
     tabela.setVisible(true);
     painelDir.add(tabela);
     painelDir.setVisible(true);
     container.add(painelDir, BorderLayout.EAST);
     super.pack();
     super.repaint();
     super.validate();
     }
     */

    private void iniciaArraysCampos_Botoes() {
        /* 
         Aqui temos duas arrays: campos e botoes, que servirão para armazenar 
         os textos principais de cada string usada nas labels e textfields, 
         que fazem parte da array campos, e armazenar os textos principais 
         de cada string usada nos botões, que fazem parte da array botoes. 
         */
        campos[0] = "Nome";
        campos[1] = "Cnpj";
        campos[2] = "Logradouro";
        campos[3] = "Bairro";
        campos[4] = "Numero";
        campos[5] = "Complemento";
        campos[6] = "TelefonePrincipal";
        campos[7] = "TelefoneSecundario";

        botoes[0] = "Inserir";
        botoes[1] = "Alterar";
        botoes[2] = "Consultar";
        botoes[3] = "Deletar";
        botoes[4] = "MostrarTabela";
        // botoes[5] = "MostraTabela";
    }

    private void todosComponentesNoHashMap() {
        /*  
         Esse hashmap tem como objetivo facilitar o acesso aos componentes
         através das arrays de strings 'campos' e 'botoes', estas sendo 
         usadas como chave através de um for e colocando-se antes na string 
         o tipo componente.
        
         Exemplos de uso estão nos métodos configuraBotoes, configuraJLabels 
         e configuraCamposDeTexto.
         */

        iniciaArraysCampos_Botoes();    // inicia arrays campos[] e botoes[]

        // labels no hashmap:
        hashComp.put("lblNome", lblNome);
        hashComp.put("lblCnpj", lblCnpj);
        hashComp.put("lblLogradouro", lblLogradouro);
        hashComp.put("lblBairro", lblBairro);
        hashComp.put("lblNumero", lblNumero);
        hashComp.put("lblComplemento", lblComplemento);
        hashComp.put("lblTelefonePrincipal", lblTelefonePrincipal);
        hashComp.put("lblTelefoneSecundario", lblTelefoneSecundario);

        // textfields no hashmap:
        hashComp.put("txtNome", txtNome);
        hashComp.put("txtCnpj", txtCnpj);
        hashComp.put("txtLogradouro", txtLogradouro);
        hashComp.put("txtBairro", txtBairro);
        hashComp.put("txtNumero", txtNumero);
        hashComp.put("txtComplemento", txtComplemento);
        hashComp.put("txtTelefonePrincipal", txtTelefonePrincipal);
        hashComp.put("txtTelefoneSecundario", txtTelefoneSecundario);

        // botões no hashmap:
        hashComp.put("btnMostrarTabela", btnMostrarTabela);
        hashComp.put("btnInserir", btnInserir);
        hashComp.put("btnAlterar", btnAlterar);
        hashComp.put("btnConsultar", btnConsultar);
        hashComp.put("btnDeletar", btnDeletar);

        adicionaActionListenersAosBotoes();     // adiciona actionlisteners! =)
    }

    private void adicionaActionListenersAosBotoes() {
        // tirando temporariamente para fazer o projeto apenas com JDBC.
        // o normal aqui seria k<7
        for (int k = 0; k < 4; k++) {
            JButton b = (JButton) hashComp.get("btn" + botoes[k]);
            b.addActionListener(this);
        }
    }

    private void percorreCamposDeTexto(String comando) {
        // função que faz atividades específicas dependendo do argumento
        switch (comando) {
            case "limpar": {
                for (int k = 0; k <= 7; k++) {
                    JFormattedTextField t = (JFormattedTextField) hashComp.get("txt" + campos[k]);
                    t.setText("");
                }
                break;
            }
            case "mostraTexto": {
                for (int k = 0; k <= 7; k++) {
                    JFormattedTextField t = (JFormattedTextField) hashComp.get("txt" + campos[k]);
                    System.out.println(t.getText());
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println(comando);
        switch (comando) {

            case "Inserir": {
                // verifica os campos e diz se um deles está vazio
                verificaCampos();
                // o que diabos o método a seguir faz??
                //Fabrica.camposEstaoVazios("txt", hashComp, campos);

                // inserir o endereço
                Endereco endereco = new Endereco();
                endereco.setId(4); // só temporariamente, p completar o obj
                endereco.setLogradouro(txtLogradouro.getText());
                endereco.setBairro(txtBairro.getText());
                endereco.setNumero(Integer.parseInt(txtNumero.getText().trim()));
                endereco.setComplemento(txtComplemento.getText());
                
                
                Cliente cliente = new Cliente();
                cliente.setNome(txtNome.getText());
                cliente.setCnpj(txtCnpj.getText());
                cliente.setTelefonePrincipal(txtTelefonePrincipal.getText());
                cliente.setTelefoneSecundario(txtTelefoneSecundario.getText());
                endereco.setCliente(cliente);
                try {
                    GenericoDAOAntigo.inserir(cliente);
                    GenericoDAOAntigo.inserir(endereco);
                } catch (Exception ex) {
                    //JOptionPane.showMessageDialog(null, "Não foi possível inserir"
                           // + "o endereço.");
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                           
                }

                break;
            }
            case "Alterar": {

                break;
            }
            case "Consultar": {

                break;
            }
            case "Deletar": {

                break;
            }
            case "Mostrar Tabela": {
                percorreCamposDeTexto("limpar");
                //percorreTextFields("mostraTexto");
                break;
            }
        }
    }

    private void verificaCampos() {
        for (int i = 0; i <= 7; i++) {
            JFormattedTextField t
                    = (JFormattedTextField) hashComp.get("txt" + campos[i]);
            if (i != 7 || i != 6) {
                if (t.getText().equals("")
                        || t.getText().equals("(  )    .    )")
                        // método replace com String
                        // texto.replace("(","");
                        || t.getText().equals("  .   .   /    -  ")
                        || t.getText().equals("      ")) {
                    JOptionPane.showMessageDialog(null, "O campo "
                            + campos[i] + " está vazio.");
                    break;
                }
            }
        }
    }

    //  DECLARAÇÃO DE VARIÁVEIS  //
    // HashMap dos componentes label, textfield, button e togglebutton
    private HashMap<String, JComponent> hashComp = new HashMap<>();
    private JLabel lblNome = new JLabel("Nome");
    private JLabel lblCnpj = new JLabel("Cnpj");
    private JLabel lblLogradouro = new JLabel("Logradouro");
    private JLabel lblBairro = new JLabel("Bairro");
    private JLabel lblNumero = new JLabel("Número");
    private JLabel lblComplemento = new JLabel("Complemento");
    private JLabel lblTelefonePrincipal = new JLabel("Telefone Principal");
    private JLabel lblTelefoneSecundario = new JLabel("Telefone Secundário");
    private JFormattedTextField txtNome = new JFormattedTextField();
    private JFormattedTextField txtCnpj = new JFormattedTextField();
    private JFormattedTextField txtLogradouro = new JFormattedTextField();
    private JFormattedTextField txtBairro = new JFormattedTextField();
    private JFormattedTextField txtNumero = new JFormattedTextField();
    private JFormattedTextField txtComplemento = new JFormattedTextField();
    private JFormattedTextField txtTelefonePrincipal = new JFormattedTextField();
    private JFormattedTextField txtTelefoneSecundario = new JFormattedTextField();
    private MaskFormatter formatoCnpj;
    private MaskFormatter formatoNumero;
    private MaskFormatter formatoTelefonePrimario;
    private MaskFormatter formatoTelefoneSecundario;
    private MaskFormatter formatoNome;
    private String[] campos = new String[8];
    private String[] botoes = new String[5]; //era 6
    private JButton btnMostrarTabela = new JButton("Mostrar Tabela");
    private JButton btnInserir = new JButton("Inserir");
    private JButton btnAlterar = new JButton("Alterar");
    private JButton btnConsultar = new JButton("Consultar");
    private JButton btnDeletar = new JButton("Deletar");
    private JTable tabela;
    private JPanel painelEsq;
    private JPanel painelDir;
    private Container container;
    private BorderLayout borderLayout;
    private GridBagLayout g;
    private GridBagConstraints c;
    private Fabrica fabrica;
}
