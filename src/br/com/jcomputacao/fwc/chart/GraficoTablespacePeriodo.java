package br.com.jcomputacao.fwc.chart;

import br.com.jcomputacao.fwc.model.MonTablespace;
import br.com.jcomputacao.fwc.model.RelConfiguracao;
import java.awt.BasicStroke;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * 06/06/2011 11:53:16
 * @author Murilo
 */
public class GraficoTablespacePeriodo extends Grafico {

    public GraficoTablespacePeriodo(List<MonTablespace> lista, String nomeServidor) {
        RelConfiguracao config = new RelConfiguracao();
        config.setTipo(GraficoTipo.TABLESPACE_PERIODO);
        config.setTitulo("Ocupação de tablespace ");
        config.setCorFundo(0xFFFFFF);
        config.setCorPlot(0xFFFFFF);

        titulo = config.getTitulo()+nomeServidor;
        final double proporcao = 1024 * 1024L;
        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        String nomeTablespace = null;
        TimeSeries serie = null;
        
        for (MonTablespace atual : lista) {
            if(nomeTablespace==null || !nomeTablespace.equalsIgnoreCase(atual.getNomTablespace())) {
                if (serie != null) {
                    dataset.addSeries(serie);
                }
                nomeTablespace = atual.getNomTablespace();
                serie = new TimeSeries(nomeTablespace);
            }
            
            Second sec = new Second(atual.getDatColeta());
            double util = (atual.getValAlocado() - atual.getValLivre()) / proporcao;
            serie.addOrUpdate(sec, util);
        }
        
        if (serie != null) {
            dataset.addSeries(serie);
        }
        

        JFreeChart chart = ChartFactory.createXYLineChart(titulo, "Data", "Ocupação em MBytes",
                dataset,
                PlotOrientation.VERTICAL,
                true, // legend
                true, // tool tips
                false // URLs
                );
        if (config.isExibeBorda() != null) {
            chart.setBorderVisible(config.isExibeBorda().booleanValue());
        }
        if (config.getCorFundo() != null) {
            chart.setBackgroundPaint(config.getCorFundoColor());
        }
        
        final XYPlot plot = chart.getXYPlot();

        final ValueAxis domainAxis = new DateAxis("Data");
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5f);

        final XYItemRenderer renderer = plot.getRenderer();
        plot.setBackgroundPaint(config.getCorPlotColor());
        BasicStroke stroke = new BasicStroke(4.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1.0f, null, 0.0f);
        for (int i = 1; i <= dataset.getSeries().size(); i++) {
            plot.getRenderer().setSeriesStroke(i, stroke);
        }
        
        renderer.setBaseStroke(stroke);
        renderer.setToolTipGenerator(
                new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("dd-MMM-yy"), new DecimalFormat("#,##0.00")));
        this.content = getAsByteArray(chart);
    }
}
