package br.com.jcomputacao.fwc.chart;

import br.com.jcomputacao.fwc.model.MonDisco;
import br.com.jcomputacao.fwc.model.RelConfiguracao;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
/**
 * 24/05/2011 15:04:07
 * @author Murilo
 */
public class GraficoUtilizacaoDisco extends Grafico {
    private double utilizacaoMaxima = 0;
    private double utilizacaoMedia = 0;
    private int escalaAlcancada = 0;
    private double utilizacaoMediaPorcentagem;
    
    public GraficoUtilizacaoDisco(List<MonDisco> lista, String servidorNome) {
        RelConfiguracao config = new RelConfiguracao();
        config.setTipo(GraficoTipo.DISCO);
        config.setTitulo("Utilização de disco ");
        config.setCorFundo(0xFFFFFF);
        config.setCorPlot(0xFFFFFF);
        
        this.titulo = config.getTitulo()+servidorNome;
        final TimeSeries serie = new TimeSeries("Utilização do disco");
        long valTamanho = lista.get(lista.size()-1).getValTamanho();
        long tamanhoEscalaAtual = 1;
        long dimensaoEscala = 1024;
        double tamanhoMaximo = 0;
        
        int multiplicadorEscala = Integer.parseInt(System.getProperty("grafico.disco.multiplicadorEscala", "8"));
        for (int i = 0; i < escalas.length && (multiplicadorEscala * tamanhoEscalaAtual) < valTamanho; i++) {
            if((multiplicadorEscala*tamanhoEscalaAtual)<(valTamanho/dimensaoEscala)) {
                escalaAlcancada++;
                tamanhoEscalaAtual = tamanhoEscalaAtual*dimensaoEscala;
            }
        }
        
        double somaUtilizacao = 0;
        double somaTamanhoTotal = 0;
        int amostras = 0;
        for(MonDisco atual : lista) {
            Second sec = new Second(atual.getDatColeta());
            if (utilizacaoMaxima < atual.getValUtilizado()) {
                utilizacaoMaxima = atual.getValUtilizado();
            }

            somaUtilizacao += atual.getValUtilizado();
            //somaUtilizacaoPorcentagem += ((double)atual.getValUtilizado())/((double)atual.getValTamanho());
            if (tamanhoMaximo < atual.getValTamanho()) {
                tamanhoMaximo = atual.getValTamanho();
            }
            somaTamanhoTotal += (double)atual.getValTamanho();
            amostras++;
            serie.addOrUpdate(sec, atual.getValUtilizado()/tamanhoEscalaAtual);
        }
        
        this.utilizacaoMedia = somaUtilizacao/(amostras*tamanhoEscalaAtual);
        tamanhoMaximo = tamanhoMaximo/tamanhoEscalaAtual;
        this.utilizacaoMediaPorcentagem = utilizacaoMedia/tamanhoMaximo;
        this.utilizacaoMaxima = utilizacaoMaxima/tamanhoEscalaAtual;
         
        if(!lista.isEmpty()) {
            titulo = "Utilização de disco "+lista.get(0).getNomPontoMontagem()+" em "+servidorNome;
        } else {
            titulo = "Utilização de disco em "+servidorNome;
        }
        
        final TimeSeriesCollection dataset = new TimeSeriesCollection(serie);
        JFreeChart chart = ChartFactory.createXYAreaChart(titulo,"Data", "Utilização "+escalas[escalaAlcancada],
            dataset,
            PlotOrientation.VERTICAL,
            true,  // legend
            true,  // tool tips
            false  // URLs
        );
        if(config.isExibeBorda()!=null) {
            chart.setBorderVisible(config.isExibeBorda().booleanValue());
        }
        if(config.getCorFundo()!=null) {
            chart.setBackgroundPaint(config.getCorFundoColor());
        }
        
        final XYPlot plot = chart.getXYPlot();
        
        if (config.getCorPlot() != null) {
            plot.setBackgroundPaint(config.getCorPlotColor());
        }

        final ValueAxis domainAxis = new DateAxis("Data");
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5f);
        
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//        NumberFormat nf = NumberFormat.getPercentInstance();
//        rangeAxis.setTickUnit(new NumberTickUnit(0.05, nf));
        rangeAxis.setRangeWithMargins(0.05d*utilizacaoMaxima, 0.95d*tamanhoMaximo);
        rangeAxis.setAutoRangeIncludesZero(true);

        IntervalMarker target = new IntervalMarker(0.9*tamanhoMaximo, tamanhoMaximo);
        target.setLabel("Margem de segurança");
        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
        target.setLabelAnchor(RectangleAnchor.LEFT);
        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
        target.setPaint(new Color(240,240,240));
        plot.addRangeMarker(target, Layer.BACKGROUND);
        
        final XYItemRenderer renderer = plot.getRenderer();
        renderer.setToolTipGenerator(
            new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("dd-MMM-yy"), new DecimalFormat("#,##0.00")
            )
        );
        //#006400
        Color c = new Color(0, 100, 0);
        renderer.setSeriesPaint(0, c);
        
        this.content = getAsByteArray(chart);
    }

    public double getUtilizacaoMaxima() {
        return this.utilizacaoMaxima;
    }
    
    public String getTamanhoEscalaAlcancada() {
        return escalas[escalaAlcancada];
    }

    public double getUtilizacaoMedia() {
        return utilizacaoMedia;
    }

    public double getUtilizacaoMediaPorcentagem() {
        return utilizacaoMediaPorcentagem;
    }
}
