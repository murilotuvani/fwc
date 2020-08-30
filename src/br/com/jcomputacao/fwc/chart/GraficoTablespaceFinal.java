package br.com.jcomputacao.fwc.chart;

import br.com.jcomputacao.fwc.model.MonBancoDados;
import br.com.jcomputacao.fwc.model.MonServidor;
import br.com.jcomputacao.fwc.model.MonTablespace;
import br.com.jcomputacao.fwc.model.RelConfiguracao;
import java.awt.Color;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 06/06/2011 11:53:04
 * @author Murilo
 */
public class GraficoTablespaceFinal extends Grafico {
    
    public GraficoTablespaceFinal(List<MonTablespace> lista, MonServidor servidor, MonBancoDados banco) {
        RelConfiguracao config = new RelConfiguracao();
        config.setTipo(GraficoTipo.TABLESPACE_FINAL);
        config.setTitulo("Tablespaces no final do período ");
        config.setCorFundo(0xFFFFFF);
        config.setCorPlot(0xFFFFFF);
        
        this.titulo = config.getTitulo()+banco.getNomBancoDados()+" "+servidor.getNomServidor();
        final double proporcao = 1024*1024L;
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(MonTablespace mon:lista) {
            double max = mon.getValMaximo()/proporcao;
            double aloc = mon.getValAlocado()/proporcao;
            double util = (mon.getValAlocado()-mon.getValLivre())/proporcao;
            aloc = aloc - util;
            max = max - (util+aloc);
            dataset.addValue(util, "Utilizado", mon.getNomTablespace());
            dataset.addValue(aloc, "Alocado", mon.getNomTablespace());
            dataset.addValue(max, "Máximo", mon.getNomTablespace());
        }
        
        JFreeChart chart = ChartFactory.createStackedBarChart(
//        JFreeChart chart = ChartFactory.createBarChart(            
                "Status tablespaces", // chart title 
                "", // domain axis label 
                "Espaço MBytes", // range axis label 
                dataset, // data 
                PlotOrientation.HORIZONTAL, // the plot orientation 
                true, // legend 
                false, // tooltips 
                false // urls 
                );

        if (config.isExibeBorda() != null) {
            chart.setBorderVisible(config.isExibeBorda().booleanValue());
        }
        if (config.getCorFundo() != null) {
            chart.setBackgroundPaint(config.getCorFundoColor());
        }

        CategoryPlot plot = chart.getCategoryPlot();
        if(config.getCorPlot()!=null) {
            plot.setBackgroundPaint(config.getCorPlotColor());
        }

        plot.getRenderer().setSeriesPaint(0, new Color(79, 98, 40));
        plot.getRenderer().setSeriesPaint(1, new Color(119, 147, 60));
        plot.getRenderer().setSeriesPaint(2, new Color(195, 214, 155));
        
        int width  = 696;
        int height = 458;
        if (lista.size() > 7) {
            height += (lista.size()-7)*13;
        }
        this.content = getAsByteArray(chart, width, height);
    }
    
}
