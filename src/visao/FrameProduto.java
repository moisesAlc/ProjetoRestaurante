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
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.text.MaskFormatter;
import utilitarios.MeuDocumentoMonetario;

/*
 * Frame que cuidará do CRUD do produto
 */
public class FrameProduto extends JFrame implements ActionListener {

    public FrameProduto() throws ParseException {
        super("Formulário de Produtos");
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
        configuraCheckBox();
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
        for (int i = 0; i < NUM_CAMPOS; i++) {
            c.gridy = i;
            painelEsq.add(hashComp.get("lbl" + campos[i]), c);
        }
    }

    private void configuraCamposDeTexto() throws ParseException {

        configuraMáscaras();

        for (int i = 0; i < NUM_CAMPOS - 1; i++) {
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
            if (i == 2) {
                //formatoDecimal.install(t);
                t.setDocument(doc);
                t.setName("valor");
            }
            painelEsq.add(t, c);
            System.out.println("gridy = " + c.gridy);
            System.out.println("gridx = " + c.gridx);
        }
    }

    private void configuraCheckBox() {
        c.gridy = 3;
        c.gridx = 1;
        painelEsq.add(chkDispEntrega, c);
    }

    private void configuraMáscaras() throws ParseException {
        // configura a máscara do cnpj 
        formatoDecimal = new MaskFormatter("##.###.###/####-##");
        //formatoDecimal.setPlaceholder("  .   .   /    -  ");
        //formatoNome.setPlaceholderCharacter('_');
        formatoDecimal.setValueContainsLiteralCharacters(true);
    }

    private void configuraBotoes() {
        // Põe botões na 3ª linha percorrendo a array botoes.
        for (int i = 0; i <= 4; i++) {
            c.gridy = i;
            painelEsq.add(hashComp.get("btn" + botoes[i]), c);
        }
    }

    private void iniciaArraysCampos_Botoes() {
        /* 
         Aqui temos duas arrays: campos e botoes, que servirão para armazenar 
         os textos principais de cada string usada nas labels e textfields, 
         que fazem parte da array campos, e armazenar os textos principais 
         de cada string usada nos botões, que fazem parte da array botoes. 
         */
        campos[0] = "Codigo";
        campos[1] = "Nome";
        campos[2] = "Valor";
        campos[3] = "DispEntrega";

        botoes[0] = "Inserir";
        botoes[1] = "Alterar";
        botoes[2] = "Consultar";
        botoes[3] = "Deletar";
        botoes[4] = "MostrarTabela";

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
        hashComp.put("lblCodigo", lblCodigo);
        hashComp.put("lblNome", lblNome);
        hashComp.put("lblValor", lblValor);
        hashComp.put("lblDispEntrega", lblDispEntrega);

        // textfields no hashmap:
        hashComp.put("txtCodigo", txtCodigo);
        hashComp.put("txtNome", txtNome);
        hashComp.put("txtValor", txtValor);

        //combobox:
        hashComp.put("cbbDispEntrega", chkDispEntrega);

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
        switch (comando) {
            case "limpar": {
                for (int k = 0; k < NUM_CAMPOS; k++) {
                    JFormattedTextField t = (JFormattedTextField) hashComp.get("txt" + campos[k]);
                    t.setText("");
                }
                break;
            }
            case "mostraTexto": {
                for (int k = 0; k < NUM_CAMPOS; k++) {
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

            case "Mostrar Tabela": {
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

    private void verificaCampos() {
        for (int i = 0; i <= 7; i++) {
            JFormattedTextField t
                    = (JFormattedTextField) hashComp.get("txt" + campos[i]);
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
    private final JLabel lblCodigo = new JLabel("Código");
    private final JLabel lblNome = new JLabel("Nome");
    private final JLabel lblValor = new JLabel("Valor");
    private final JLabel lblDispEntrega = new JLabel("Disponível para entrega");
    private JFormattedTextField txtCodigo = new JFormattedTextField();
    private JFormattedTextField txtNome = new JFormattedTextField();
    private JFormattedTextField txtValor = new JFormattedTextField();
    private MeuDocumentoMonetario doc = new MeuDocumentoMonetario();
    private JCheckBox chkDispEntrega = new JCheckBox("");
    private MaskFormatter formatoDecimal;
    private final int NUM_CAMPOS = 4;
    private final int NUM_DIGITOS_MAX = 12;
    private String[] campos = new String[NUM_CAMPOS];
    private String[] botoes = new String[5];
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
