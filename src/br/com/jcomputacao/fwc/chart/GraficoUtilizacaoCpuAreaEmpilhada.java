package br.com.jcomputacao.fwc.chart;

import br.com.jcomputacao.fwc.model.MonCpu;
import br.com.jcomputacao.fwc.model.RelConfiguracao;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SeriesRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYAreaRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeTableXYDataset;

/**
 * 2013-11-12
 * @author Murilo
 */
public class GraficoUtilizacaoCpuAreaEmpilhada extends GraficoUtilizacaoCpu {
    private static final String SERIES_IO = "IO";
    private static final String SERIES_SYSTEM = "System";
    private static final String SERIES_USER = "User";
    private static final String SERIES_NICE = "Nice";
    private static final String SERIES_STEAL = "Steal";

    public GraficoUtilizacaoCpuAreaEmpilhada(List<MonCpu> lista, String nomeServidor) {
        RelConfiguracao config = new RelConfiguracao();
        config.setTipo(GraficoTipo.CPU);
        config.setTitulo("Utilização de CPU ");
        config.setCorFundo(0xFFFFFF);
        config.setCorPlot(0xFFFFFF);
        titulo = config.getTitulo() + nomeServidor;
        
        final TimeTableXYDataset dataset = new TimeTableXYDataset();
        List<Double> utilizacoes = new ArrayList<Double>();
        for (MonCpu atual : lista) {
            Second sec = new Second(atual.getDatColeta());
            dataset.add(sec, atual.getPctUser()/100, SERIES_USER);
            dataset.add(sec, atual.getPctSystem()/100, SERIES_SYSTEM);
            dataset.add(sec, atual.getPctIowait()/100, SERIES_IO);
            dataset.add(sec, atual.getPctNice()/100, SERIES_NICE);
            dataset.add(sec, atual.getPctSteal()/100, SERIES_STEAL);

            double utilizacao = atual.getPctIowait()+
                    atual.getPctSystem()+atual.getPctUser()+
                    atual.getPctNice()+atual.getPctSteal();
            utilizacoes.add(utilizacao);
        }
        
        final JFreeChart chart = ChartFactory.createStackedXYAreaChart(titulo
                , "Data/Hora"
                , "Utilização"
                , dataset
                , PlotOrientation.VERTICAL
                , true, true, false);
        if (config.isExibeBorda() != null) {
            chart.setBorderVisible(config.isExibeBorda().booleanValue());
        }
        if (config.getCorFundo() != null) {
            chart.setBackgroundPaint(config.getCorFundoColor());
        }

        final XYPlot plot = chart.getXYPlot();

        if (config.getCorPlot() != null) {
            plot.setBackgroundPaint(config.getCorPlotColor());
        }
        final StackedXYAreaRenderer render = new StackedXYAreaRenderer();
        int i = 0;
        render.setSeriesPaint(i++, Color.RED);
        render.setSeriesPaint(i++, Color.BLUE);
        render.setSeriesPaint(i++, Color.ORANGE);
        render.setSeriesPaint(i++, Color.GREEN);
        render.setSeriesPaint(i++, Color.PINK);
        

        final ValueAxis domainAxis = new DateAxis("Data");
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5f);
        
        plot.setRenderer(render);
        plot.setDomainAxis(domainAxis);
        plot.setSeriesRenderingOrder(SeriesRenderingOrder.FORWARD);
        plot.setForegroundAlpha(0.5f);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        NumberFormat nf = NumberFormat.getPercentInstance();
        rangeAxis.setTickUnit(new NumberTickUnit(0.05, nf));
        rangeAxis.setRangeWithMargins(0.05d, 0.95d);
        rangeAxis.setAutoRangeIncludesZero(true);
        this.content = getAsByteArray(chart);
    }
    
}
