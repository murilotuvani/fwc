package br.com.jcomputacao.fwc.business;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.chart.Grafico;
import br.com.jcomputacao.fwc.chart.GraficoTablespaceFinal;
import br.com.jcomputacao.fwc.chart.GraficoTablespacePeriodo;
import br.com.jcomputacao.fwc.chart.GraficoUtilizacaoCpu;
import br.com.jcomputacao.fwc.chart.GraficoUtilizacaoCpuAreaEmpilhada;
import br.com.jcomputacao.fwc.chart.GraficoUtilizacaoDisco;
import br.com.jcomputacao.fwc.chart.GraficoUtilizacaoMemoria;
import br.com.jcomputacao.fwc.dao.AtividadeDao;
import br.com.jcomputacao.fwc.dao.MonBancoDadosDao;
import br.com.jcomputacao.fwc.dao.MonBancoDadosServidorDao;
import br.com.jcomputacao.fwc.dao.MonCpuDao;
import br.com.jcomputacao.fwc.dao.MonDiscoDao;
import br.com.jcomputacao.fwc.dao.MonDisponibilidadeDao;
import br.com.jcomputacao.fwc.dao.MonMemoriaDao;
import br.com.jcomputacao.fwc.dao.MonServidorDao;
import br.com.jcomputacao.fwc.dao.MonTablespaceDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioGraficoDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioTabelaDao;
import br.com.jcomputacao.fwc.dao.ReportData;
import br.com.jcomputacao.fwc.dao.ReportElement;
import br.com.jcomputacao.fwc.dao.ReportGraph;
import br.com.jcomputacao.fwc.dao.ReportTable;
import br.com.jcomputacao.fwc.model.Atividade;
import br.com.jcomputacao.fwc.model.Cliente;
import br.com.jcomputacao.fwc.model.MonBancoDados;
import br.com.jcomputacao.fwc.model.MonBancoDadosServidor;
import br.com.jcomputacao.fwc.model.MonCpu;
import br.com.jcomputacao.fwc.model.MonDisco;
import br.com.jcomputacao.fwc.model.MonDisponibilidade;
import br.com.jcomputacao.fwc.model.MonMemoria;
import br.com.jcomputacao.fwc.model.MonServidor;
import br.com.jcomputacao.fwc.model.MonTablespace;
import br.com.jcomputacao.fwc.model.RelRelatorio;
import br.com.jcomputacao.fwc.model.RelRelatorioGrafico;
import br.com.jcomputacao.fwc.model.RelRelatorioTabela;
import br.com.jcomputacao.fwc.table.Tabela;
import br.com.jcomputacao.fwc.table.TabelaAtividade;
import br.com.jcomputacao.fwc.table.TabelaDisponibilidade;
import br.com.jcomputacao.fwc.table.TabelaTablespacePeriodo;
import br.com.jcomputacao.util.TimeUtil;
import java.io.IOException;
import java.text.ChoiceFormat;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 24/08/2011 23:45:17
 * @author Murilo
 */
public class RelatorioLogic {

    private static final DateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Cliente cliente;
    private final Date inicio;
    private final Date fim;
    private MonServidor servidor;
    private ReportData data = new ReportData();

    public RelatorioLogic(Cliente c, Date inicio, Date fim) {
        this.cliente = c;
        this.inicio = inicio;
        this.fim = fim;
    }

    public RelatorioLogic(Cliente c, Date inicio, Date fim, MonServidor s) {
        this(c, inicio, fim);
        this.servidor = s;
    }

    public ReportData execute() throws DaoException, BusinessException, IOException {
        MonBancoDadosDao bDao = new MonBancoDadosDao();
        String where = "\nWHERE M.COD_CLIENTE=" + cliente.getCodCliente();
        List<MonBancoDados> bancos = bDao.listar(where);
        Map<Long, MonBancoDados> bancoPorCodigo = new HashMap<Long, MonBancoDados>();
        List<MonBancoDadosServidor> bancosPorServidor = null;
        if (!bancos.isEmpty()) {
            MonBancoDadosServidorDao bdsDao = new MonBancoDadosServidorDao();
            where = "\nWHERE M.COD_BANCO_DADOS IN (";
            boolean first = true;
            for (MonBancoDados banco : bancos) {
                bancoPorCodigo.put(banco.getCodBancoDados(), banco);
                if (first) {
                    first = false;
                } else {
                    where += ",";
                }
                where += banco.getCodBancoDados();
            }
            where += ")";
            bancosPorServidor = bdsDao.listar(where);
        }

        MonServidorDao mDao = new MonServidorDao();
        if (servidor == null) {
            List<MonServidor> ss = mDao.listar(" WHERE M.FLG_ATIVO='S' AND M.COD_CLIENTE=" + cliente.getCodCliente());
            if (ss.isEmpty()) {
                throw new BusinessException(")N\u00e3o foram encontrados servidores em monitoramento para este cliente");
            } else {
                for (MonServidor s : ss) {
                    colocaBancosNoServidor(s, bancoPorCodigo, bancosPorServidor);
                    trataServidor(s);
                }
            }
        } else {
            colocaBancosNoServidor(servidor, bancoPorCodigo, bancosPorServidor);
            trataServidor(servidor);
        }
        return data;
    }

    private void trataServidor(MonServidor s) throws DaoException, IOException {
        //Utilizado pelo swing para saber quais servidores foram
        //colocados neste relatorio
        data.addServidor(s);
        trataDisco(s);
        trataCpu(s);
        trataMemoria(s);
        Set<MonBancoDados> instancias = s.getInstancias();
        for (MonBancoDados instancia : instancias) {
            trataTablespaces(s, instancia);
            trataDisponibilidade(s, instancia);
        }
        trataAtividades(s);
    }

    private void trataDisco(MonServidor s) throws DaoException, IOException {
        MonDiscoDao dDao = new MonDiscoDao();
        String where = "\nWHERE COD_SERVIDOR=" + s.getCodServidor()
                + "\n   AND DAT_COLETA>=TO_DATE('" + getInicio()
                + " 00:00:00','YYYY-MM-DD HH24:MI:SS')"
                + "\n   AND DAT_COLETA<=TO_DATE('" + getFim()
                + " 23:59:59','YYYY-MM-DD HH24:MI:SS')";

        List<MonDisco> listar = dDao.listar(where);
        if (listar.isEmpty()) {
            return;
        }
        Map<String, List<MonDisco>> mapaDiscos = new HashMap<String, List<MonDisco>>();

        for (MonDisco mDisco : listar) {
            List<MonDisco> monPontoMontagem = null;
            if (mapaDiscos.containsKey(mDisco.getNomPontoMontagem())) {
                monPontoMontagem = mapaDiscos.get(mDisco.getNomPontoMontagem());
            } else {
                monPontoMontagem = new ArrayList<MonDisco>();
                mapaDiscos.put(mDisco.getNomPontoMontagem(), monPontoMontagem);
            }
            monPontoMontagem.add(mDisco);
        }

        Set<String> ospontos = mapaDiscos.keySet();
        List<String> pontos = new ArrayList<String>();
        pontos.addAll(ospontos);
        Collections.sort(pontos);
        
        for (String ponto : pontos) {
            List<MonDisco> monPonto = mapaDiscos.get(ponto);
            GraficoUtilizacaoDisco gud = new GraficoUtilizacaoDisco(monPonto, s.getNomServidor());
            ReportElement graph = createReportGraph(gud);
            double numero = gud.getUtilizacaoMaxima();
            String conteudo = "Utilização máxima do disco ";

            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            conteudo += nf.format(numero) + " " + gud.getTamanhoEscalaAlcancada();

            numero = gud.getUtilizacaoMedia();
            conteudo += ".\nUtilização média do disco " + nf.format(numero) + " " + gud.getTamanhoEscalaAlcancada();

            numero = gud.getUtilizacaoMediaPorcentagem();
            nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            conteudo += ".\nUtilização média de " + nf.format(numero) + " da capacidade no período.";

            //< 90% A ocupação do disco X está dentro do limite tolerável.
            //> 90% A ocupação do disco X está acima do limite tolerável. Deve ser analisado o motivo desta ocupação e, se for o caso, considerar a disponibilização de novos discos.
            if (numero < 0.90) {
                conteudo += "\nA ocupação do disco " + ponto + " está dentro do limite tolerável no período analisado.";
            } else {
                conteudo += "\nA ocupação do disco " + ponto + " está acima do limite tolerável no período analisado. Deve ser analisado o motivo desta ocupação e, se for o caso, considerar a disponibilização de novos discos.";
            }
            graph.setTitulo(gud.getTitulo());
            graph.setAnalise(conteudo);
            data.addReportElement(graph);
        }
    }

    private void trataCpu(MonServidor s) throws DaoException, IOException {
        MonCpuDao dDao = new MonCpuDao();
        String where = "\nWHERE M.COD_SERVIDOR=" + s.getCodServidor()
                + "\n   AND M.DAT_COLETA>=TO_DATE('" + getInicio()
                + " 00:00:00','YYYY-MM-DD HH24:MI:SS')"
                + "\n   AND DAT_COLETA<=TO_DATE('" + getFim()
                + " 23:59:59','YYYY-MM-DD HH24:MI:SS')";

        List<MonCpu> listar = dDao.listar(where);
        if (listar.isEmpty()) {
            return;
        }

        GraficoUtilizacaoCpu gud = new GraficoUtilizacaoCpuAreaEmpilhada(listar, s.getNomServidor());
        ReportGraph graph = createReportGraph(gud);
        graph.setTitulo(gud.getTitulo());
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setGroupingUsed(false);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);

        String conteudo = "A média de uso de CPU no período analisado foi de "
                + nf.format(gud.getUtilizacaoMedia()) + ". Este valor está ";
        //< 25% A média de uso de CPU no  período analisado foi de X%. Este valor está adequado para o limite tolerável.
        //> 25% A média de uso de CPU no período analisado foi de X%. Este valor está acima do limite tolerável.
        if (gud.getUtilizacaoMedia() <= 0.25) {
            conteudo += "adequado para o limite tolerável.";
        } else {
            conteudo += " acima do limite tolerável.";
        }

        graph.setAnalise(conteudo);
        data.addReportElement(graph);
    }

    private void trataMemoria(MonServidor s) throws DaoException, IOException {
        MonMemoriaDao dDao = new MonMemoriaDao();
        String where = " WHERE COD_SERVIDOR=" + s.getCodServidor()
                + "\n   AND DAT_COLETA>=TO_DATE('" + getInicio()
                + " 00:00:00','YYYY-MM-DD HH24:MI:SS')"
                + "\n   AND DAT_COLETA<=TO_DATE('" + getFim()
                + " 23:59:59','YYYY-MM-DD HH24:MI:SS')";

        List<MonMemoria> listar = dDao.listar(where);
        if (listar.isEmpty()) {
            return;
        }
        GraficoUtilizacaoMemoria gud = new GraficoUtilizacaoMemoria(listar, s.getNomServidor());
        ReportGraph graph = createReportGraph(gud);
        graph.setTitulo(gud.getTitulo());
        String conteudo = "Utilização de memória dentro da normalidade no período analisado.";
        /*
         * Se Linux:
         * Se Windows < 70%: A média de alocação de memória no servidor é de X, que é considerado um nível aceitável.
         * Se Windows > 70%: A média de alocação de memória no servidor é de X, acima do nível tolerável. Deve ser analisado o motivo desta alocação
         */
        if ("WINDOWS".equalsIgnoreCase(s.getTipSistemaOperacional())) {
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setGroupingUsed(false);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            double numero = gud.getUtilizacaoMediaPorcentagem();
            if (numero < 70) {
                conteudo = "A média de alocação de memória no servidor no período analisado foi de " + nf.format(numero) + ", que é considerado um nível aceitável.";
            } else {
                conteudo = "A média de alocação de memória no servidor no período analisado foi  de " + nf.format(numero) + ", acima do nível tolerável. Deve ser analisado o motivo desta alocação";
            }
            nf = NumberFormat.getNumberInstance();
            nf.setGroupingUsed(false);
            nf.setMinimumFractionDigits(2);
            nf.setMaximumFractionDigits(2);
            numero = gud.getUtilizacaoMedia();
            conteudo += "\nA utilização média é de " + nf.format(numero) + " " + gud.getTamanhoEscalaAlcancada();

        }
        graph.setAnalise(conteudo);
        data.addReportElement(graph);
    }

    private void trataTablespaces(MonServidor s, MonBancoDados banco) throws DaoException, IOException {
        Date inicioMais6 = TimeUtil.addMonthsToDate(inicio, 6);
        Date inicioPeriodo = inicio;
        if (inicioMais6.getTime() > fim.getTime()) {
            inicioPeriodo = TimeUtil.subtractMonthsToDate(fim, 6);
        }

        String where = "\n   WHERE M.COD_BANCO_DADOS=" + banco.getCodBancoDados()
                + "\n   AND M.NOM_TABLESPACE NOT LIKE '%TEMP%' AND M.NOM_TABLESPACE NOT IN ('REDO')"
                + "\n   AND M.DAT_COLETA>=TO_DATE('" + sqlDateFormat.format(inicioPeriodo)
                + " 00:00:00','YYYY-MM-DD HH24:MI:SS')"
                + "\n   AND M.DAT_COLETA<=TO_DATE('" + getFim()
                + " 23:59:59','YYYY-MM-DD HH24:MI:SS')"
                + "\nORDER BY M.NOM_TABLESPACE, M.DAT_COLETA";
        MonTablespaceDao dao = new MonTablespaceDao();
        List<MonTablespace> lista = dao.listar(where);
        
        String titulo = " banco " + banco.getNomBancoDados()+
                " em "+s.getNomServidor()+" (6 meses)";
        Grafico gud = new GraficoTablespacePeriodo(lista, titulo);
        ReportElement element = createReportGraph(gud);
        element.setTitulo(gud.getTitulo());
        data.addReportElement(element);

        List<MonTablespace> listaPeriodo = filtraPeriodo(lista);
        Tabela tabela = new TabelaTablespacePeriodo(listaPeriodo, banco);
        element = createReportTable(tabela);
        element.setTitulo(tabela.getTitulo());
        data.addReportElement(element);

        Map<String, MonTablespace> map = new TreeMap<String, MonTablespace>();

        for (MonTablespace monTable : lista) {
            if (map.containsKey(monTable.getNomTablespace())) {
                MonTablespace atual = map.get(monTable.getNomTablespace());
                if (monTable.getDatColeta().after(atual.getDatColeta())) {
                    map.put(monTable.getNomTablespace(), monTable);
                }
            } else {
                map.put(monTable.getNomTablespace(), monTable);
            }
        }

        List<MonTablespace> listaFinal = new ArrayList<MonTablespace>(map.values());
        gud = new GraficoTablespaceFinal(listaFinal, s, banco);
        element = createReportGraph(gud);
        element.setAnalise(analiseTableSpaces(listaFinal));
        data.addReportElement(element);
    }
    
    private String analiseTableSpaces(List<MonTablespace> listaFinal) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (MonTablespace tableSpace : listaFinal) {
            if (!tableSpace.getNomTablespace().toUpperCase().startsWith("UNDO")) {
                double ocupacao = (tableSpace.getValAlocado() - tableSpace.getValLivre()) / tableSpace.getValMaximo();
                if (ocupacao >= 0.85d) {
                    if (first) {
                        first = false;
                    } else {
                        sb.append("\n");
                    }
                    double livreTamanho = tableSpace.getValMaximo() - (tableSpace.getValAlocado() - tableSpace.getValLivre());
                    double livrePorcentagem = livreTamanho / tableSpace.getValMaximo();
                    sb.append(fraseOcupacaoTableSpace(tableSpace.getNomTablespace(), ocupacao, livrePorcentagem, livreTamanho));
                }
            }
        }
        if (first) {
            sb.append("Não há tablespaces com ocupação próxima do seu limite.");
        }
        return sb.toString();
    }

    private String fraseOcupacaoTableSpace(String tableSpace, double ocupacao, double livrePorcentagem, double livreTamanho) {
        long tamanhoEscalaAtual = 1;
        long dimensaoEscala = 1024;
        String escalas [] = new String[]{"bytes", "Kbytes", "Mbytes", "GBytes", "TBytes", "PBytes"};
        int escalaAlcancada = 0;
        
        for(int i=0;i<escalas.length && tamanhoEscalaAtual<livreTamanho;i++) {
            if(tamanhoEscalaAtual<(livreTamanho/dimensaoEscala)) {
                escalaAlcancada++;
                tamanhoEscalaAtual = tamanhoEscalaAtual*dimensaoEscala;
            }
        }
        double livreValor = livreTamanho / tamanhoEscalaAtual;
        
        String msg = "A ocupação da tablespace {0} está próxima do seu "
                + "limite, com somente {1} livres, isto é {2} {3}."
                + "\nDeve ser analisada a possibilidade de "
                + "criar novos datafiles ou aumentar os existentes.";
//        String msg = "A ocupação do tablespace foi de {0}, acima de 85%: A ocupação da tablespace {1} está próxima do seu"
//                +" limite, com somente {2} livre, isto é {3} {4}. Deve ser analisada a possibilidade de serem "
//                +"criados novos datafiles ou aumentar os existentes.";
        NumberFormat nfp = NumberFormat.getPercentInstance();
        nfp.setMinimumFractionDigits(2);
        nfp.setMaximumFractionDigits(2);
        nfp.setGroupingUsed(false);
        
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);
        
        
        Format[] testFormats = {null, nfp, nf, null};
        MessageFormat pattform = new MessageFormat(msg);
        pattform.setFormats(testFormats);
        Object[] testArgs = {tableSpace, livrePorcentagem, livreValor, escalas[escalaAlcancada]};
        return pattform.format(testArgs);
    }
    



    private void trataDisponibilidade(MonServidor s, MonBancoDados banco) throws DaoException, IOException {
        MonDisponibilidadeDao dDao = new MonDisponibilidadeDao();
        String where = "\nWHERE COD_SERVIDOR=" + s.getCodServidor() + " AND COD_BANCO_DADOS=" + banco.getCodBancoDados()
                + "\n   AND M.DES_EVENTO in ('SHUTDOWN','STARTUP')"
                + "\n   AND DAT_EVENTO>=TO_DATE('" + getInicio()
                + " 00:00:00','YYYY-MM-DD HH24:MI:SS')"
                + "\n   AND DAT_EVENTO<=TO_DATE('" + getFim()
                + " 23:59:59','YYYY-MM-DD HH24:MI:SS')"
                + "\nORDER BY COD_BANCO_DADOS, DAT_EVENTO";
        List<MonDisponibilidade> eventos = dDao.listar(where);

        Map<String, List<MonDisponibilidade>> disponibilidadePorEvento = new HashMap<String, List<MonDisponibilidade>>();
        for (MonDisponibilidade evento : eventos) {
            List<MonDisponibilidade> disponibilidades = null;
            if (disponibilidadePorEvento.containsKey(evento.getDesEvento())) {
                disponibilidades = disponibilidadePorEvento.get(evento.getDesEvento());
            } else {
                disponibilidades = new ArrayList<MonDisponibilidade>();
                disponibilidadePorEvento.put(evento.getDesEvento(), disponibilidades);
            }
            disponibilidades.add(evento);
        }

        TabelaDisponibilidade tabela = new TabelaDisponibilidade(eventos, s, banco);

        ReportTable element = createReportTable(tabela);
        String conteudo = "Não houve indisponibilidade do servidor no período analisado";
        if (!eventos.isEmpty()) {
            if (eventos.size() == 1) {
                conteudo = "Houve um evento que gerou indisponibilidade do serviço";
            } else {
                conteudo = "Houveram " + eventos.size() + " eventos que geraram indisponibilidade do serviço";
            }
            
            List<MonDisponibilidade> shutdowns = disponibilidadePorEvento.get("SHUTDOWN");
            List<MonDisponibilidade> startups = disponibilidadePorEvento.get("STARTUP");
            if (startups != null) {
                if (shutdowns == null) {
                    conteudo += "\n" + fraseDisponibilidadePreocupacao(startups.size());
                } else if (startups.size() > shutdowns.size()) {
                    conteudo += "\n" + fraseDisponibilidadePreocupacao(startups.size() - shutdowns.size());
                } else if (startups.size() == shutdowns.size()) {
                    conteudo += "\n" + fraseDisponibilidadeOk(startups.size());
                }
            }
        }
        element.setAnalise(conteudo);
        data.addReportElement(element);
    }

    private void trataAtividades(MonServidor s) throws DaoException, IOException {
        String where = " WHERE COD_SERVIDOR=" + s.getCodServidor()
                + "\n   AND DAT_INICIO>=TO_DATE('" + getInicio()
                + " 00:00:00','YYYY-MM-DD HH24:MI:SS')"
                + "\n   AND DAT_INICIO<=TO_DATE('" + getFim()
                + " 23:59:59','YYYY-MM-DD HH24:MI:SS')";

        AtividadeDao ad = new AtividadeDao();
        List<Atividade> lista = ad.listar(where);
        TabelaAtividade tabela = new TabelaAtividade(lista, s.getNomServidor());

        ReportTable element = createReportTable(tabela);
        element.setTitulo(tabela.getTitulo());

//        String conteudo = "Não houveram atividades relacionadas ao servidor " + 
//                s.getNomServidor() + " no período analisado";
//        if (!lista.isEmpty()) {
//            conteudo = "Houveram " + lista.size() + 
//                    " atividadas relacionadas ao servidor " + 
//                    s.getNomServidor() + " no período";
//        }
//        element.setAnalise(conteudo);
        data.addReportElement(element);
    }

    private ReportGraph createReportGraph(Grafico gud) {
        ReportGraph graph = new ReportGraph();
        graph.setTitulo(gud.getTitulo());
        graph.setContent(gud.getContent());
        return graph;
    }

    private ReportTable createReportTable(br.com.jcomputacao.fwc.table.Tabela tab) {
        ReportTable table = new ReportTable();
        table.setTitulo(tab.getTitulo());
        table.setContent(tab.getConteudo());
        return table;
    }

    private String fraseDisponibilidadePreocupacao(Integer desligamentos) {
        double[] filelimits = {1, 2};
        String[] filepart = {"desligamento inadequado", "{0} desligamentos inadequados"};
        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        Format[] testFormats = {fileform, null, NumberFormat.getIntegerInstance()};
        MessageFormat pattform = new MessageFormat("Situação de preocupação, {0}");
        pattform.setFormats(testFormats);
        Object[] testArgs = {desligamentos};
        return pattform.format(testArgs);
    }

    private String fraseDisponibilidadeOk(Integer desligamentos) {
        double[] filelimits = {1, 2};
        String[] filepart = {"desligamento adequado", "{0} desligamentos adequados"};
        ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
        Format[] testFormats = {fileform, null, NumberFormat.getIntegerInstance()};
        MessageFormat pattform = new MessageFormat("Situação normal, {0}");
        pattform.setFormats(testFormats);
        Object[] testArgs = {desligamentos};
        return pattform.format(testArgs);
    }
    
    /**
     * Pode haver mais de uma instancia para um servidor servidor 12 tem 4 instancias (Gandini
     * assi como mais de um servidor para uma instancia, banco de dados 2 esta nos servidores 14 e 15 (Julio Simoes)
     * @param s
     * @param bancoPorCodigo
     * @param bancosPorServidor 
     */
    private void colocaBancosNoServidor(MonServidor s, Map<Long, MonBancoDados> bancoPorCodigo, List<MonBancoDadosServidor> bancosPorServidor) {
        if (bancosPorServidor != null) {
            for (MonBancoDadosServidor bancoDadosServidor : bancosPorServidor) {
                if (s.getCodServidor() == bancoDadosServidor.getCodServidor()) {
                    s.getInstancias().add(bancoPorCodigo.get(bancoDadosServidor.getCodBancoDados()));
                }
            }
        }
    }

    private String getInicio() {
        return sqlDateFormat.format(inicio);
    }

    private String getFim() {
        return sqlDateFormat.format(fim);
    }

    public void salva() throws DaoException {
        RelRelatorio rel = new RelRelatorio();
        rel.setCodCliente(cliente.getCodCliente());
        rel.setDatCadastro(new java.util.Date());
        rel.setDatInicio(inicio);
        rel.setDatFim(fim);
        RelRelatorioDao rdao = new RelRelatorioDao();
        rel = rdao.salvar(rel);
        long codigo = rel.getCodRelatorio();

        List<ReportElement> elements = new ArrayList<ReportElement>(data.getReportElements().values());
        Collections.sort(elements);

        for (ReportElement element : elements) {
            if (element instanceof ReportGraph) {
                ReportGraph graph = (ReportGraph) element;
                RelRelatorioGrafico rrg = new RelRelatorioGrafico();
                rrg.setTitulo(graph.getTitulo());
                rrg.setNumOrdem(graph.getOrdem());
                rrg.setCodRelatorio(codigo);
                rrg.setDesAnalise(element.getAnalise());
                rrg.setBloConteudo(graph.getContent());
                RelRelatorioGraficoDao dao = new RelRelatorioGraficoDao();
                dao.salvar(rrg);
            } else if (element instanceof ReportTable) {
                ReportTable table = (ReportTable) element;
                RelRelatorioTabela rrg = new RelRelatorioTabela();
                rrg.setTitulo(table.getTitulo());
                rrg.setNumOrdem(table.getOrdem());
                rrg.setCodRelatorio(codigo);
                rrg.setDesAnalise(element.getAnalise());
                rrg.setDesConteudo(table.getContent());
                System.out.println(rrg.getTitulo()+"\n"+rrg.getNumOrdem()+"\n"+
                        rrg.getCodRelatorio()+"\n"+rrg.getDesAnalise()+"\n"+rrg.getDesConteudo());
                RelRelatorioTabelaDao dao = new RelRelatorioTabelaDao();
                dao.salvar(rrg);
            }
        }
    }

    private List<MonTablespace> filtraPeriodo(List<MonTablespace> lista) {
        List<MonTablespace> listaFiltrada = new ArrayList<MonTablespace>();
        for (MonTablespace mon : lista) {
            if (mon!=null && mon.getDatColeta().after(inicio) && mon.getDatColeta().before(fim)) {
                listaFiltrada.add(mon);
            }
        }
        return listaFiltrada;
    }
}
