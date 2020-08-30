package br.com.jcomputacao.fwc.pdf;

import br.com.jcomputacao.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 03/07/2011 15:38:34
 * @author Murilo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "table")
public class Tabela {
    @XmlElement(name = "tr")
    List<Linha> linhas = new ArrayList<Linha>();

    public List<Linha> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Linha> linhas) {
        this.linhas = linhas;
    }

    int getColumnCount() {
        int colunas = 0;
        for (Linha linha : linhas) {
            int colunasLinha = linha.getColunas();
            if (colunas < colunasLinha) {
                colunas = colunasLinha;
            }
        }
        return colunas;
    }
    
    float[] getColumnsSizes() {
        float[] colSizes = null;
        if (linhas != null && !linhas.isEmpty()) {
            colSizes = new float[getColumnCount()];
            for (Linha linha : linhas) {
                int colunasLinha = linha.getColunas();
                int i = 0;
                do {
                    Celula celula = linha.celulas.get(i);
                    int largura = celula.colspan;
                    if (StringUtil.isNotNull(celula.width)) {
                        float width = Float.parseFloat(celula.width.replace("%", "").trim());
                        width = width / largura;
                        for (int k = i; k < (i + largura); k++) {
                            if (colSizes[k] < width) {
                                colSizes[k] = width;
                            }
                        }
                    }
                    i += largura;
                } while (i < colunasLinha);
            }
            int colunasZeradas = colunasZeradas(colSizes);
            if (colunasZeradas > 0) {
                float soma = soma(colSizes);
                float teto = 1;
                while (soma > teto)
                    teto *= 10;
                
                float resto = teto - soma;
                for (int i = 0; i < colSizes.length; i++)
                    if (colSizes[i] == 0)
                        colSizes[i] = resto / colunasZeradas;                
            }
        }
        return colSizes;
    }

    private int colunasZeradas(float[] colSizes) {
        int colunasZeradas = 0;
        for (float size : colSizes) 
            if (size == 0) colunasZeradas++;
        
        return colunasZeradas;
    }

    private float soma(float[] colSizes) {
        float soma = 0;
        for(float f : colSizes)
            soma += f;
        
        return soma;
    }
    
}