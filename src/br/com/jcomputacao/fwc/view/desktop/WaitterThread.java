package br.com.jcomputacao.fwc.view.desktop;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * 29/09/2011 21:28:08
 * @author Murilo
 */
public class WaitterThread implements Runnable {
    private List<Thread> threads = new ArrayList<Thread>();
    
    public void add(Thread t) {
        this.threads.add(t);
    }

    @Override
    public void run() {
        boolean aindaTemThead = true;
        
        while(aindaTemThead) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(WaitterThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            boolean [] toRemove = new boolean[threads.size()];
            for(int i=0;i<threads.size();i++) {
                toRemove[i] = !threads.get(i).isAlive();
            }
            for (int i = toRemove.length - 1; i >= 0; i--) {
                if(toRemove[i]) {
                    System.out.println("Removendo thread");
                    threads.remove(i);
                }
            }
            aindaTemThead = (threads.size()>0);
            System.out.println("Ainda tem threads "+threads.size());
        }
        JOptionPane.showMessageDialog(null, "Execuções concluídas");
        
    }
    
}
