package br.com.jcomputacao.fwc.view.desktop;

import br.com.jcomputacao.fwc.dao.ClienteDao;
import br.com.jcomputacao.fwc.model.Cliente;
import br.com.jcomputacao.fwc.model.RelRelatorio;
import br.com.jcomputacao.fwc.pdf.ReportToPdf;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

/**
 * 02/10/2011 23:20:45
 * @author Murilo
 */
class RelatorioExportacao extends SwingWorker {

    private final File basedir;
    private final RelRelatorio relatorio;
    private final JTextArea textArea;
    private String fname;

    RelatorioExportacao(File basedir, RelRelatorio relatorio, JTextArea textArea) {
        this.basedir = basedir;
        this.relatorio = relatorio;
        this.textArea = textArea;
    }

    @Override
    protected Object doInBackground() throws Exception {
        ClienteDao cDao = new ClienteDao();
        Cliente cliente = cDao.buscar(relatorio.getCodCliente());
        ReportToPdf instance = new ReportToPdf(null, relatorio, cliente);
        ByteArrayOutputStream baos = instance.execute();
        fname = "Relatório de Monitoramento " + relatorio.getNomCliente();
        SimpleDateFormat df = new SimpleDateFormat(".MMM.yy." + relatorio.getCodRelatorio());
        fname += df.format(relatorio.getDatFim());
        if (fname.length() >= 254) {
            fname = fname.substring(0, 254);
        }
        fname += ".pdf";
        File f = new File(basedir, fname);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(baos.toByteArray());
        fos.flush();
        fos.close();

        fname = f.getAbsolutePath();
        return null;
    }

    @Override
    protected void done() {
        String text = textArea.getText();
        if (text == null) {
            text = "";
        }
        text += "Arquivo " + fname + " exportado!!\n";
        textArea.setText(text);
    }
}
