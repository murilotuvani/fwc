package br.com.jcomputacao.fwc.chart;

/**
 *
 * @author oracle
 */
public abstract class GraficoUtilizacaoCpu extends Grafico {
    protected double utilizacaoMedia = 0;
    
    
    public double getUtilizacaoMedia() {
        return this.utilizacaoMedia;
    }
}
