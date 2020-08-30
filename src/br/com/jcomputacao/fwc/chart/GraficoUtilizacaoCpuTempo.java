package br.com.jcomputacao.fwc.chart;

import br.com.jcomputacao.fwc.model.MonCpu;
import br.com.jcomputacao.fwc.model.RelConfiguracao;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * 01/06/2011 20:02:25
 * @author Murilo
 */
public class GraficoUtilizacaoCpuTempo extends GraficoUtilizacaoCpu {

    public GraficoUtilizacaoCpuTempo(List<MonCpu> lista, String nomeServidor) {
        RelConfiguracao config = new RelConfiguracao();
        config.setTipo(GraficoTipo.CPU);
        config.setTitulo("Utilização de CPU ");
        config.setCorFundo(0xFFFFFF);
        config.setCorPlot(0xFFFFFF);
//        RelConfiguracao globaclConfig = new RelConfiguracao();
        titulo = config.getTitulo()+nomeServidor;
        final TimeSeries serieIo = new TimeSeries("IO");
//        final TimeSeries seriePcWait = new TimeSeries("PcWait");
        final TimeSeries serieSystem = new TimeSeries("System");
        final TimeSeries serieUser = new TimeSeries("User");
        final TimeSeries serieNice = new TimeSeries("Nice");
//        final TimeSeries serieIdle = new TimeSeries("Idle");
        final TimeSeries serieSteal = new TimeSeries("Steal");

        List<Double> utilizacoes = new ArrayList<Double>();
        for (MonCpu atual : lista) {
            Second sec = new Second(atual.getDatColeta());
            serieIo.addOrUpdate(sec, atual.getPctIowait()/100);
            serieSystem.addOrUpdate(sec, atual.getPctSystem()/100);
            serieUser.addOrUpdate(sec, atual.getPctUser()/100);
            serieNice.addOrUpdate(sec, atual.getPctNice()/100);
//            serieIdle.addOrUpdate(sec, atual.getPctIdle());
            serieSteal.addOrUpdate(sec, atual.getPctSteal()/100);
            double utilizacao = atual.getPctIowait()+
                    atual.getPctSystem()+atual.getPctUser()+
                    atual.getPctNice()+atual.getPctSteal();
            utilizacoes.add(utilizacao);
        }
        
        double utilizacao = 0;
        int descartados = 0;
        for (Double ut : utilizacoes) {
            if (ut > 0) {
                utilizacao += ut;
            } else {
                descartados++;
            }
        }
        utilizacaoMedia = (utilizacao/(utilizacoes.size()-descartados))/100;

        final TimeSeriesCollection dataset = new TimeSeriesCollection(serieIo);
        dataset.addSeries(serieSystem);
        dataset.addSeries(serieUser);
        dataset.addSeries(serieNice);
//        dataset.addSeries(serieIdle);
        dataset.addSeries(serieSteal);

        JFreeChart chart = ChartFactory.createXYAreaChart(titulo, "Data", config.getTitulo(),
                dataset,
                PlotOrientation.VERTICAL,
                true, // legend
                true, // tool tips
                false // URLs
                );
        if(config.isExibeBorda()!=null) {
            chart.setBorderVisible(config.isExibeBorda().booleanValue());
        }
        if(config.getCorFundo()!=null) {
            chart.setBackgroundPaint(config.getCorFundoColor());
        }
        
        final XYPlot plot = chart.getXYPlot();
        
        if(config.getCorPlot()!=null) {
            plot.setBackgroundPaint(config.getCorPlotColor());
        }
        
        final ValueAxis domainAxis = new DateAxis("Data");
        domainAxis.setLowerMargin(0.0);
        domainAxis.setUpperMargin(0.0);
        plot.setDomainAxis(domainAxis);
        plot.setForegroundAlpha(0.5f);

        //ValueAxis rangeAxis = plot.getRangeAxis();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //NumberFormat nf = new DecimalFormat("# %");
        NumberFormat nf = NumberFormat.getPercentInstance();
        rangeAxis.setTickUnit(new NumberTickUnit(0.05, nf));
        rangeAxis.setRangeWithMargins(0.05d, 0.95d);
        rangeAxis.setAutoRangeIncludesZero(true);

//        IntervalMarker target = new IntervalMarker(0.8, 1);
//        target.setLabelFont(new Font("SansSerif", Font.ITALIC, 11));
//        target.setLabelAnchor(RectangleAnchor.LEFT);
//        target.setLabelTextAnchor(TextAnchor.CENTER_LEFT);
//        plot.addRangeMarker(target, Layer.BACKGROUND);

        final XYItemRenderer renderer = plot.getRenderer();
        renderer.setToolTipGenerator(
                new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("dd-MMM-yy"), new DecimalFormat("#,##0.00")));
        this.content = getAsByteArray(chart);
    }
}
