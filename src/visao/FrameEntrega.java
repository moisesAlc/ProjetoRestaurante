/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.Fabrica;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author aluno
 */
public class FrameEntrega extends JFrame implements ActionListener {

    public FrameEntrega() throws ParseException {
        super("Formulário de Clientes");
        inicia();
    }

    private void inicia() throws ParseException {
        iniciaGridBag();
        todosComponentesNoHashMap();
        confPainelEsq();
        //confPainelDir();
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
            JFormattedTextField t = (JFormattedTextField) hashComp.get("txt" + campos[i]);
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
        for (int i = 0; i <= 5; i++) {
            c.gridy = i;
            painelEsq.add(hashComp.get("btn" + botoes[i]), c);
        }
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
        hashComp.put("lblCliente", lblCliente);
        hashComp.put("lblData", lblData);
        hashComp.put("lblDataLit", lblDataLit);
        hashComp.put("lblProdutos", lblProdutos);
        hashComp.put("lblHora", lblHora);
        hashComp.put("lblHoraLit", lblHoraLit);
        hashComp.put("lblValorTotalPedido", lblValorTotalPedido);
        hashComp.put("lblValTotPedidoLit", lblValTotPedidoLit);
        hashComp.put("lblRS", lblRS);
        hashComp.put("lblValorDispCliente", lblValorDispCliente);
        hashComp.put("lblTrocoRS", lblTrocoRS);
        hashComp.put("lblValTrocoLit", lblValTrocoLit);


        // textfields no hashmap:
        hashComp.put("txtCliente", txtCliente);
        hashComp.put("txtCnpj", txtCnpj);
        hashComp.put("txtLogradouro", txtLogradouro);
        hashComp.put("txtBairro", txtBairro);
        hashComp.put("txtNumero", txtNumero);
        hashComp.put("txtComplemento", txtComplemento);
        hashComp.put("txtTelefonePrincipal", txtTelefonePrincipal);

        // botões no hashmap:
        hashComp.put("btnLimpar", btnLimpar);
        hashComp.put("btnInserir", btnInserir);
        hashComp.put("btnAlterar", btnAlterar);
        hashComp.put("btnConsultar", btnConsultar);
        hashComp.put("btnDeletar", btnDeletar);
        hashComp.put("btnMostraTabela", btnMostraTabela);

        adicionaActionListenersAosBotoes();     // adiciona actionlisteners! =)
    }

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
        botoes[4] = "Limpar";
        botoes[5] = "MostraTabela";
    }
    
    private void adicionaActionListenersAosBotoes() {
        // tirando temporariamente para fazer o projeto apenas com JDBC.
        // o normal aqui seria k<7
        for (int k = 0; k < 5; k++) {
            JButton b = (JButton) hashComp.get("btn" + botoes[k]);
            b.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        System.out.println(comando);
        switch (comando) {
            case "Mostrar Tabela": {
                if (btnMostraTabela.isSelected()) {
                    System.out.println("está selecionado");
                    try {
                        //colocaTabela();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao colocar "
                                + "tabela");
                    }
                } else {
                    System.out.println("não está selecionado");
                    painelDir.removeAll();
                    super.repaint();
                    super.validate();
                }
                break;
            }  // fim do botão "Mostrar Tabela"
            case "Limpar": {
                percorreCamposDeTexto("limpar");
                //percorreTextFields("mostraTexto");
                break;
            }
            case "Inserir": {
                // tirando temporariamente p testar método da fábrica.
                verificaCampos();
                fabrica.camposEstaoVazios("txt", hashComp, campos);
                //JOptionPane.showMessageDialog(null, "Há um campo vazio");

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
        }
    }

    private void percorreCamposDeTexto(String comando) {
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
    
    private void verificaCampos() {
        for (int i = 0; i <= 7; i++) {
            JFormattedTextField t = (JFormattedTextField) hashComp.get("txt" + campos[i]);
            if (i != 7 || i != 6) {
                if (t.getText().equals("")
                        || t.getText().equals("(  )    .    )")
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
    private Double zero = 0.0;
    private JLabel lblCliente = new JLabel("Cliente");
    private JLabel lblData = new JLabel("Data");
    private JLabel lblDataLit = new JLabel("00/00/00");
    private JLabel lblProdutos = new JLabel("Produtos");
    private JLabel lblHora = new JLabel("Hora");
    private JLabel lblHoraLit = new JLabel("00:00:00");
    private JLabel lblValorTotalPedido = new JLabel("Valor Total do Pedido");
    private JLabel lblValTotPedidoLit = new JLabel(zero.toString());
    private JLabel lblRS = new JLabel("R$ ");
    private JLabel lblValorDispCliente = new JLabel("Valor disponível do cliente");
    private JLabel lblTrocoRS = new JLabel("Troco: R$ ");
    private JLabel lblValTrocoLit = new JLabel(zero.toString());
    private JFormattedTextField txtCliente = new JFormattedTextField();
    private JFormattedTextField txtCnpj = new JFormattedTextField();
    private JFormattedTextField txtLogradouro = new JFormattedTextField();
    private JFormattedTextField txtBairro = new JFormattedTextField();
    private JFormattedTextField txtNumero = new JFormattedTextField();
    private JFormattedTextField txtComplemento = new JFormattedTextField();
    private JFormattedTextField txtTelefonePrincipal = new JFormattedTextField();
    private JFormattedTextField txtRS = new JFormattedTextField();
    private MaskFormatter formatoCnpj;
    private MaskFormatter formatoNumero;
    private MaskFormatter formatoTelefonePrimario;
    private MaskFormatter formatoTelefoneSecundario;
    private MaskFormatter formatoNome;
    private String[] campos = new String[8];
    private String[] botoes = new String[6];
    private JButton btnLimpar = new JButton("Limpar");
    private JButton btnInserir = new JButton("Inserir");
    private JButton btnAlterar = new JButton("Alterar");
    private JButton btnConsultar = new JButton("Consultar");
    private JButton btnDeletar = new JButton("Deletar");
    private JToggleButton btnMostraTabela = new JToggleButton("Mostrar Tabela");
    private JTable tabela;
    private JPanel painelEsq;
    private JPanel painelDir;
    private Container container;
    private BorderLayout borderLayout;
    private GridBagLayout g;
    private GridBagConstraints c;
    private Fabrica fabrica;
}
