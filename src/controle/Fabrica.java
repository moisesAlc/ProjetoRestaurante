package controle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Fabrica {

    public Fabrica() {

    }

    public Fabrica(JFrame f, String titulo, BorderLayout bdl, GridBagLayout gbl,
            GridBagConstraints gbc, Container cont) {
        //f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        f.setTitle(titulo);
        //f.setResizable(false);
        //f.setVisible(true); ?

        gbl = new GridBagLayout();
        this.gbl = gbl;
        cont = new Container();
        bdl = new BorderLayout();
        container.setLayout(bdl);
        this.container = cont;
        this.container.setLayout(gbl);
        this.container.setVisible(true);
        f.setContentPane(this.container);
        this.restricoes = new GridBagConstraints();
        
         gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        this.restricoes.insets = new Insets(2, 2, 2, 2);
        this.restricoes.anchor = GridBagConstraints.LINE_START;
        this.restricoes.fill = GridBagConstraints.BOTH;

    }

    public Fabrica(Container container, GridBagConstraints gridConst, JPanel pain) {
        this.container = container;
        this.restricoes = gridConst;
        this.restricoes.insets = new Insets(2, 2, 2, 2);
        this.restricoes.anchor = GridBagConstraints.LINE_START;
        this.restricoes.fill = GridBagConstraints.BOTH;
        this.painelUnico = pain;
        this.painelUnico.setLayout(new GridBagLayout());
        this.container.add(this.painelUnico);
    }

    public Fabrica(JFrame f, String titulo, int tamInset) {
        f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        f.setTitle(titulo);
        f.setBackground(new java.awt.Color(255, 250, 250));
        f.setResizable(false);
        this.container = f.getContentPane();
        this.painelUnico = new JPanel();
        this.painelUnico.setLayout(new GridBagLayout());
        this.container.add(this.painelUnico);
        this.restricoes = new GridBagConstraints();
        this.restricoes.insets = new Insets(tamInset, tamInset, tamInset, tamInset);
        this.restricoes.anchor = GridBagConstraints.LINE_START;
        this.restricoes.fill = GridBagConstraints.BOTH;
    }

    public Fabrica(JFrame f, String titulo) {
        f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        f.setTitle(titulo);
        f.setBackground(new java.awt.Color(255, 250, 250));
        f.setResizable(false);
        this.container = f.getContentPane();
        this.painelUnico = new JPanel();
        this.painelUnico.setLayout(new GridBagLayout());
        //this.container.add(this.painelUnico);
        this.restricoes = new GridBagConstraints();
        this.restricoes.insets = new Insets(2, 2, 2, 2);
        this.restricoes.anchor = GridBagConstraints.LINE_START;
        this.restricoes.fill = GridBagConstraints.BOTH;
    }

    public JFrame addJFrame(JFrame f, String titulo) { // até agr não usei?
        f.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        f.setTitle(titulo);
        f.setBackground(new java.awt.Color(255, 250, 250));
        f.setResizable(false);
        return f;
    }
    /*
     public JPanel addJPanelPrincipal() {
     this.painelPrincipal = new JPanel();
     this.painelPrincipal.setLayout(new GridBagLayout());
     this.painelPrincipal.setVisible(true);
     this.painelPrincipal.setBackground(corFonte);
     painelPrincipal.setSize(500,500); // testando
     this.container.add(this.painelPrincipal, BorderLayout.CENTER);
     return this.painelPrincipal;
     }
     */

    public JPanel addPainelVazio(JPanel vaz, int largura, int altura) {
        vaz = new JPanel();
        vaz.setSize(largura, altura);
        return vaz;
    }

    public JLabel addBarraStatus(JLabel l, String texto) {
        l = new JLabel(texto);
        l.setBackground(Color.GRAY);
        this.container.add(l, BorderLayout.SOUTH);
        return l;
    }

    public JPanel addJPanel(JPanel jp, int tamx, int tamy, int posx, int posy) {
        jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        jp.setOpaque(true);
        jp.setBounds(0, 0, tamx, tamy);
        jp.setVisible(true);
        this.restricoes.gridx = posx;
        this.restricoes.gridy = posy;
        this.container.add(jp, restricoes);  // dá certo??
        this.listaPaineis.add(jp);
        return jp;
    }

    public JPanel addJPanelDentroDeContainer(
            Container container, JPanel jp, int tamx, int tamy, int posx, int posy) {

        jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        jp.setOpaque(true);
        jp.setBounds(0, 0, tamx, tamy);
        jp.setVisible(true);
        this.restricoes.gridx = posx;
        this.restricoes.gridy = posy;
        this.container.add(jp, restricoes);  // dá certo??
        this.listaPaineis.add(jp);
        return jp;
    }

    private void configCompNoGridBag(JComponent comp, int posx, int posy, int tamx, int tamy) {
        this.restricoes.gridx = posx;
        this.restricoes.gridy = posy;
        this.restricoes.gridwidth = tamx;
        this.restricoes.gridheight = tamy;
        comp.setFont(fonte);
        comp.setForeground(corFonte);
        this.painelUnico.add(comp, this.restricoes);
        this.restricoes.gridwidth = 1;
        this.restricoes.gridheight = 1;
    }

    public JLabel addJLabel(String texto, int posx, int posy, int tamx, int tamy) {
        JLabel comp = new JLabel(texto);
        this.configCompNoGridBag(comp, posx, posy, tamx, tamy);
        return comp;
    }

    public JTextField addJTextField(String nome, int nColunas, int posx, int posy, int tamx, int tamy) {
        JTextField comp = new JTextField(nColunas);
        this.configCompNoGridBag(comp, posx, posy, tamx, tamy);
        return comp;
    }

    public JCheckBox addJCheckBox(String txt, int posx, int posy, int tamx, int tamy) {
        JCheckBox comp = new JCheckBox(txt);
        this.configCompNoGridBag(comp, posx, posy, tamx, tamy);
        return comp;
    }

    public JComboBox addJComboBox(String nome, String[] itens, int posx, int posy, int tamx, int tamy) {
        JComboBox comp = new JComboBox();
        comp.setName(nome);
        for (String item : itens) {
            comp.addItem(item);
        }
        this.configCompNoGridBag(comp, posx, posy, tamx, tamy);
        return comp;
    }

    public JRadioButton addJRadioButton(String txt, int posx, int posy, int tamx, int tamy) {
        JRadioButton comp = new JRadioButton(txt);
        this.configCompNoGridBag(comp, posx, posy, tamx, tamy);
        return comp;
    }

    public JButton addJButton(String txt, int posx, int posy, int tamx, int tamy) {
        JButton comp = new JButton(txt);
        this.configCompNoGridBag(comp, posx, posy, tamx, tamy);
        return comp;
    }

    public JPasswordField addJPasswordField(String msg, int nColunas, int posx, int posy, int tamx, int tamy) {
        JPasswordField comp = new JPasswordField(nColunas);
        comp.setName(msg);
        this.configCompNoGridBag(comp, posx, posy, tamx, tamy);
        return comp;
    }

    // Método feito em 26 de janeiro de 2015, para saber qual é o nome do BD
    // que estou usando logo no início do programa.
    public String mostraBD() throws SQLException {
        String dbURL = ConBanco.getSessao().connection().getMetaData().getURL().toString();

        String db;
        int nBarras = 0;
        int iUltBarra = 0;
        boolean jaFoiBarras = false;
        int iInter = 0; // índice onde está a interrogação
        for (int i = 0; i < dbURL.length(); i++) {
            if (dbURL.charAt(i) == '?') {
                iInter = i;
                break;
            }
            if (dbURL.charAt(i) == '/') {
                nBarras++;
            }
            if (nBarras == 3 && jaFoiBarras == false) {
                iUltBarra = ++i;
                jaFoiBarras = true;
            }

        }
        db = dbURL.substring(iUltBarra, iInter);

        return db;
    }
    /*
     public void proximaJanelaAdm(String login, String senha, JFrame f) throws Exception {
     if (GenericoDAO.temCpfSenha(login, senha, 'a') != null) {

     new JanelaAdm((Administrador) GenericoDAO.temCpfSenha(
     login, senha, 'a')).setVisible(true);
     f.dispose();
     }
     }

     public void proximaJanelaCli(String login, String senha, JFrame f) throws Exception {
     if (GenericoDAO.temCpfSenha(login, senha, 'c') != null) {
     new JanelaClienteAuto((Cliente) GenericoDAO.temCpfSenha(
     login, senha, 'c')).setVisible(true);
     f.dispose();
     }
     }
    
     */

    public static void camposEstaoVazios(String tipo, HashMap m, String[] arr) {
        for (String s : arr) {
            JFormattedTextField f = (JFormattedTextField) m.get(tipo.concat(s));
            System.out.println(tipo.concat(s));
            if (f.getValue() != null) {
                System.out.println(f.getValue());
            } else {
                System.out.println("nulo!");
            }
            /*
             if (f.getValue().equals(null)){
             return true;
             }
             */
        }
        //return false;
    }
    // DECLARAÇÃO DAS VARIÁVEIS

    private Container container;
    private GridBagConstraints restricoes;
    private GridBagLayout gbl;
    private List<JPanel> listaPaineis = new LinkedList();
    private HashMap<String, JComponent> hashComp = new HashMap<>();
    private JPanel painelUnico; // GridBagLayout
    private JPanel painelPrincipal; //BorderLayout
    private JPanel painelCentro; //GridBagLayout - fica no centro do painePrincipal
    private java.awt.Color corFonte = new java.awt.Color(1, 1, 1);
    private java.awt.Font fonte = new java.awt.Font("DejaVu Sans", 0, 14); // NOI18N
}
