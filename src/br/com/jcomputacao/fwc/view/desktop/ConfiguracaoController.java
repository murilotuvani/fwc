package br.com.jcomputacao.fwc.view.desktop;

import br.com.jcomputacao.fwc.chart.GraficoTipo;
import br.com.jcomputacao.util.ColorUtil;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JColorChooser;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;

/**
 * 02/10/2011 23:31:06
 * @author Murilo
 */
public class ConfiguracaoController implements MouseListener, ActionListener {
    private final JDesktopPane desktop;
    private final JFrame pai;
    private final GraficoTipo tipo;
    private final ConfiguracaoPanel panel = new ConfiguracaoPanel();

    public ConfiguracaoController(GraficoTipo tipo, JDesktopPane desktop, JFrame pai) {
        this.desktop = desktop;
        this.pai     = pai;
        this.tipo = tipo;
        
        panel.tCorFundo.addMouseListener(this);
        panel.tCorPlotagem.addMouseListener(this);
        
        JInternalFrame iFrame = new JInternalFrame("Relatório", true, true);
        iFrame.setContentPane(panel);
        desktop.add(iFrame);
        iFrame.setSize(desktop.getSize());
        iFrame.setVisible(true);
        iFrame.toFront();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            Color color = JColorChooser.showDialog(pai, "Selecione a cor", Color.yellow);
//            JColorChooser jcc = new JColorChooser();
//            jcc.setLocation(e.getLocationOnScreen());
//            jcc.setVisible(true);
//            System.out.println("Color : " + jcc.getColor());
            Object source = e.getSource();
            if(source instanceof JTextField) {
                JTextField textField = (JTextField) source;
                textField.setBackground(color);
                textField.setText(ColorUtil.rgbToHex(color.getRGB()));
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
    
}
