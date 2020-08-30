package br.com.jcomputacao.fwc.chart;

import br.com.jcomputacao.fwc.model.MonMemoria;
import br.com.jcomputacao.fwc.model.RelConfiguracao;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * 01/06/2011 22:02:22
 * @author Murilo
 */
public class GraficoUtilizacaoMemoria extends Grafico {
    private double utilizacaoMedia;
    private int escalaAlcancada = 0;
    private final double utilizacaoMediaPorcentagem;
    private double utilizacaoMaxima = 0;
    
    public GraficoUtilizacaoMemoria(List<MonMemoria> lista, String servidorNome) {
        RelConfiguracao config = new RelConfiguracao();
        config.setTipo(GraficoTipo.DISCO);
        config.setTitulo("Utilização de memória ");
        config.setCorFundo(0xFFFFFF);
        config.setCorPlot(0xFFFFFF);

        titulo = config.getTitulo()+servidorNome;
        final TimeSeries serie = new TimeSeries(titulo);
        MonMemoria ultimo = lista.get(lista.size()-1);
        double valTamanho = ultimo.getValDisponivel()+ultimo.getValUtilizado();
        long dimensaoEscala = 1024;
        long tamanhoEscalaAtual = 1;
        
        for(int i=0;i<escalas.length && tamanhoEscalaAtual<valTamanho;i++) {
            if(tamanhoEscalaAtual<(valTamanho/dimensaoEscala)) {
                escalaAlcancada++;
                tamanhoEscalaAtual = tamanhoEscalaAtual*dimensaoEscala;
            }
        }


        double somaUtilizacao = 0;
        double somaUtilizacaoPorcentagem = 0;
        double maiorDiponibilidade = 0;
        int amostras = 0;
        for(MonMemoria atual : lista) {
            Second sec = new Second(atual.getDatColeta());
            serie.addOrUpdate(sec, atual.getValUtilizado()/tamanhoEscalaAtual);
            somaUtilizacao += atual.getValUtilizado();
            if (maiorDiponibilidade < atual.getValDisponivel() + atual.getValUtilizado()) {
                maiorDiponibilidade = atual.getValDisponivel()+atual.getValUtilizado();
            }
            
            somaUtilizacaoPorcentagem += maiorDiponibilidade;
            amostras++;
            
            if (utilizacaoMaxima < atual.getValUtilizado()) {
                utilizacaoMaxima = atual.getValUtilizado();
            }
        }
        this.utilizacaoMedia = somaUtilizacao/(amostras*tamanhoEscalaAtual);
        this.utilizacaoMediaPorcentagem = somaUtilizacao/somaUtilizacaoPorcentagem;
        this.utilizacaoMaxima = utilizacaoMaxima/tamanhoEscalaAtual;
        maiorDiponibilidade = maiorDiponibilidade/tamanhoEscalaAtual;
        
        System.out.println("Maior disponibilidade : "+maiorDiponibilidade);

        
        final TimeSeriesCollection dataset = new TimeSeriesCollection(serie);
        JFreeChart chart = ChartFactory.createXYAreaChart(titulo,"Data", "Utilização em "+escalas[escalaAlcancada],
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
        rangeAxis.setRangeWithMargins(0.05d*maiorDiponibilidade, 0.95d*maiorDiponibilidade);
        rangeAxis.setAutoRangeIncludesZero(true);
        
        final XYItemRenderer renderer = plot.getRenderer();
        renderer.setToolTipGenerator(
            new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("dd-MMM-yy"), new DecimalFormat("#,##0.00")
            )
        );
        //#006400
        Color c = new Color(100, 100, 0);
        renderer.setSeriesPaint(0, c);
        
        this.content = getAsByteArray(chart);
    }

    public double getUtilizacaoMedia() {
        return this.utilizacaoMedia;
    }
    
    public String getTamanhoEscalaAlcancada() {
        return escalas[escalaAlcancada];
    }

    public double getUtilizacaoMediaPorcentagem() {
        return utilizacaoMediaPorcentagem;
    }

    public double getUtilizacaoMaxima() {
        return utilizacaoMaxima;
    }
}
