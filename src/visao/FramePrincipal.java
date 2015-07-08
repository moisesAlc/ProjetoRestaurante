package visao;

import controle.ConBanco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FramePrincipal extends JFrame implements ActionListener {

    public FramePrincipal() {
        ConBanco.abrirConexao();
        inicializa();
        ConBanco.fecharConexao();
        
    }

    private void inicializa() {

        barraMenu = new JMenuBar();

        mniCliente = new JMenuItem("Cliente");
        mniCliente.addActionListener(this);
        mniProduto = new JMenuItem("Produto");
        mniProduto.addActionListener(this);
        mniEntrega = new JMenuItem("Entrega");
        mniEntrega.addActionListener(this);
        mniRelatorio = new JMenuItem("Relatório");
        mniRelatorio.addActionListener(this);

        barraMenu.add(mniCliente);
        barraMenu.add(mniProduto);
        barraMenu.add(mniEntrega);
        barraMenu.add(mniRelatorio);

        URL URLimg = getClass().getResource("restaurant-equipment.jpg");
        ImageIcon imagem = new ImageIcon(URLimg);
        JLabel bemVindo = new JLabel(imagem);
        getContentPane().add(bemVindo);
        
        setJMenuBar(barraMenu);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "Cliente": {
                try {
                    new FrameCliente().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    break;
                }
            }
            case "Produto": {
                try {
                    new FrameProduto().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    break;
                }
            }
            case "Entrega": {
                try {
                    new FrameEntregaPrototipo().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    break;
                }
            }
            case "Relatório": {
                try {
                    new FrameRelatorio().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    break;
                }
            }
        }
    }
    private JMenuBar barraMenu;
    private JMenuItem mniCliente;
    private JMenuItem mniProduto;
    private JMenuItem mniEntrega;
    private JMenuItem mniRelatorio;
}
