package br.com.jcomputacao.fwc.pdf;

import br.com.jcomputacao.dao.DaoException;
import br.com.jcomputacao.fwc.dao.ClienteContatoDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioGraficoDao;
import br.com.jcomputacao.fwc.dao.RelRelatorioTabelaDao;
import br.com.jcomputacao.fwc.dao.ReportElement;
import br.com.jcomputacao.fwc.model.Cliente;
import br.com.jcomputacao.fwc.model.ClienteContato;
import br.com.jcomputacao.fwc.model.RelElemento;
import br.com.jcomputacao.fwc.model.RelRelatorio;
import br.com.jcomputacao.fwc.model.RelRelatorioGrafico;
import br.com.jcomputacao.fwc.model.RelRelatorioTabela;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfShading;
import com.itextpdf.text.pdf.PdfShadingPattern;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;


/**
 * 27/06/2011 01:07:47
 * @author Murilo
 */
public class ReportToPdf {
//    private Font catFont = new Font(FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.WHITE);
    private Font subFont =   new Font(FontFamily.HELVETICA, 16, Font.BOLD);
    private Font tabFont =   new Font(FontFamily.HELVETICA, 8, Font.NORMAL);
    private Font tabFontBold =   new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.WHITE);
    private final RelRelatorio relatorio;
    private final Cliente cliente;
//    private ByteArrayOutputStream baos;
    private final String uriBase;
    private RelElemento elementoAtual;

    public ReportToPdf(String uri, RelRelatorio relatorio, Cliente cliente) {
        this.relatorio = relatorio;
        this.cliente   = cliente;
        this.uriBase   = uri;
    }
    
    private void criaCapa(Document document, PdfWriter writer) throws DocumentException, DaoException, IOException, BadElementException {
        document.setMargins(0, 0, 0, 0);

        boolean test = Boolean.parseBoolean(System.getProperty("fwc.report.test", "false"));
        PdfContentByte cb = writer.getDirectContent();
        Image image = null;
        if (test) {
            image = Image.getInstance("./web/arquivos/consulting.png");
        } else {
            //URL url = new URL("http://localhost:8080/fwcGroup/arquivos/consulting.png");
            URL url = new URL(uriBase+"/arquivos/consulting.png");
            image = Image.getInstance(url);
        }
        
        float largura = image.getWidth();
        float altura  = image.getHeight();
        System.out.println("Largura antes : "+largura);
        System.out.println("Altura antes : "+altura);
        float scalePercent = 30f;
        image.scalePercent(scalePercent);
        
        largura = image.getWidth();
        altura  = image.getHeight();
        System.out.println("Largura depois : "+largura);
        System.out.println("Altura depois : "+altura);
        
        largura = image.getWidth()*scalePercent/100;
        altura  = image.getHeight()*scalePercent/100;
        System.out.println("Largura depois : "+largura);
        System.out.println("Altura depois : "+altura);
        
        float iheight = altura;
        float iwidth  = largura/2;
        float pwidth = document.getPageSize().getWidth()/2;
        float x = pwidth-iwidth;
        float y = document.getPageSize().getHeight()-(iheight+140);
        image.setAbsolutePosition(x,y);
        float logoBase = y;
        cb.addImage(image);

        if (test) {
            image = Image.getInstance("./web/oracleCertifiedPartner.jpg");
        } else {
            //URL url = new URL("http://localhost:8080/fwcGroup/oracleCertifiedPartner.jpg");
            URL url = new URL(uriBase+"/oracleCertifiedPartner.jpg");
            image = Image.getInstance(url);
        }
        image.scalePercent(70);
        iheight = image.getHeight()*0.7f;
        iwidth  = image.getWidth()*0.7f;
        pwidth = document.getPageSize().getWidth();
        x = pwidth-(iwidth);
        y = document.getPageSize().getHeight()-(iheight);
        image.setAbsolutePosition(x,y);
        cb.addImage(image);
        
        BaseColor color = new BaseColor(0, 91, 58);
        cb.setColorFill(color);
        //Determina a cor da bora do retangulo
        //cb.setColorStroke(BaseColor.BLUE);
//        cb.setColorStroke(BaseColor.RED);
        PdfShading shading = PdfShading.simpleAxial(writer, 2, (logoBase-60), (pwidth-4), 30f, color, BaseColor.BLACK);
        PdfShadingPattern shadingPattern = new PdfShadingPattern(shading);
        cb.setShadingFill(shadingPattern);
        cb.rectangle(2, (logoBase-60), (pwidth-4), 55f);
        cb.closePathFillStroke();
        
        String tituloPadrao = "Relatório Gerencial de Monitoramento de";
        cb.beginText();
        BaseFont fontBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false);
        cb.setFontAndSize(fontBold, 20);
        cb.moveText(90, (logoBase-25));
        cb.setColorFill(BaseColor.WHITE);
        cb.showText(tituloPadrao);
        cb.endText();
        
        cb.beginText();
        tituloPadrao = "Ambiente de Banco de Dados";
        cb.moveText(135, (logoBase-55));
        cb.showText(tituloPadrao);
        cb.endText();
        
        
        PdfPTable table = new PdfPTable(4);
        Font font = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setBorder(Rectangle.BOX);

        table.setWidths(new int[]{2, 2, 4, 4});
        table.setTotalWidth(527);

        PdfPCell cell = new PdfPCell(new Phrase("Data inicial", font));
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("Data final", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cliente", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Responsável", font));
        table.addCell(cell);
        
        font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL);
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        
        String aux = df.format(relatorio.getDatInicio());
        cell = new PdfPCell(new Phrase(aux, font));
        table.addCell(cell);
        
        aux = df.format(relatorio.getDatFim());
        cell = new PdfPCell(new Phrase(aux, font));
        table.addCell(cell);
        
        //aux = relatorio.getNomUsuario();
        aux = relatorio.getNomCliente();
        cell = new PdfPCell(new Phrase(aux, font));
        table.addCell(cell);
        
        //aux = "Claudir Naia";
        aux = cliente.getNomResponsavel();
        cell = new PdfPCell(new Phrase(aux, font));
        table.addCell(cell);

        x = 34;
        y = 100;
        table.writeSelectedRows(0, 3, x, y, cb);
    }

    public ByteArrayOutputStream execute() throws DocumentException, DaoException, IOException, BadElementException {
        Document docInicio = new Document(PageSize.A4);
        ByteArrayOutputStream baosInicio = new ByteArrayOutputStream();

        ClienteContatoDao ccDao = new ClienteContatoDao();
        List<ClienteContato> contatos = ccDao.listar(" WHERE TIP_CONTATO='TI' AND COD_CLIENTE="+cliente.getCodCliente());
        String contato = "";
        for(ClienteContato ctt:contatos) {
            if(!"".equals(contato)) {
                contato += ", ";
            }
            contato += ctt.getNomContato();
        }
        
        DateFormat df = DateFormat.getDateInstance();
        String titulo = cliente.getNomRazaoSocial();
        titulo += " de " + df.format(relatorio.getDatInicio()) + " a " + df.format(relatorio.getDatFim());

        PdfWriter writer = PdfWriter.getInstance(docInicio, baosInicio);
        writer.setLinearPageMode();
        docInicio.open();
        
        Document docCorpo = new Document(PageSize.A4);
        docCorpo.setMargins(36, 36, 90, 60);
        ByteArrayOutputStream baosCorpo = new ByteArrayOutputStream();
        PdfWriter writerCorpo = PdfWriter.getInstance(docCorpo, baosCorpo);
        docCorpo.open();        
        
        writer.setPageEvent(new GeneralHeader());
        writerCorpo.setPageEvent(new BodyHeaderFooter());

        criaCapa(docInicio, writer);
        docInicio.setMargins(36, 36, 90, 60);
        docInicio.newPage();

        RelRelatorioGraficoDao gdao = new RelRelatorioGraficoDao();
        RelRelatorioTabelaDao tdao = new RelRelatorioTabelaDao();
        String where = " WHERE COD_RELATORIO=" + relatorio.getCodRelatorio();
        where += "\nORDER BY NUM_ORDEM";
        List<RelRelatorioGrafico> gs = gdao.listar(where);
        List<RelRelatorioTabela> ts = tdao.listar(where);
        List<RelElemento> es = new ArrayList<RelElemento>();
        es.addAll(gs);
        es.addAll(ts);
        Collections.sort(es);
                
        Paragraph p = new Paragraph("Índice", subFont);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        p.setSpacingAfter(20);
        docInicio.add(p);
        
        int i = 1;
        for (RelElemento e : es) {
            this.elementoAtual = e;

            //Reaproveitando a letra apenas porque eh branca
            Chapter capitulo = new Chapter(new Paragraph(e.getTitulo(), tabFontBold), i++);

            Section secao = null;
            if (e instanceof RelRelatorioGrafico) {
                p = new Paragraph("Gráfico "+e.getTitulo());
                p.setSpacingAfter(30);
                secao = capitulo.addSection(p);
                RelRelatorioGrafico g = (RelRelatorioGrafico) e;
                Image image = Image.getInstance(g.getBloConteudo());
                image.setAlignment(Image.ALIGN_CENTER);
                image.scaleToFit(450f, 450f);
                secao.add(image);
            } else if (e instanceof RelRelatorioTabela) {
                p = new Paragraph("Tabela "+e.getTitulo());
                p.setSpacingAfter(30);
                secao = capitulo.addSection(p);
                RelRelatorioTabela t = (RelRelatorioTabela) e;
                htmlParaPdf(secao, t.getDesConteudo());
            }
            
            if(e.getDesAnalise()!=null && !"".equals(e.getDesAnalise())) {
                p = new Paragraph("Análise");
                p.setSpacingBefore(30);
                p.setSpacingAfter(20);
                secao = capitulo.addSection(p);
                secao.add(new Paragraph(e.getDesAnalise(), tabFont));
            }
            docCorpo.add(capitulo);
        }

        docCorpo.close();
        docInicio.add(criaIndice(es));
        docInicio.close();
        
        Document docFinal = new Document(PageSize.A4);
        docFinal.addTitle(titulo);
        docFinal.addSubject("Monitoramento");
        docFinal.addKeywords("Monitoramento banco de dados oracle");
        if (relatorio.getNomUsuario() != null) {
            docFinal.addAuthor(relatorio.getNomUsuario());
            docFinal.addCreator(relatorio.getNomUsuario());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        List<InputStream> isList = new ArrayList<InputStream>();
        isList.add(new ByteArrayInputStream(baosInicio.toByteArray()));
        isList.add(new ByteArrayInputStream(baosCorpo.toByteArray()));
        concatenaPdfs(isList, docFinal, baos);
        return baos;
    }

    private void htmlParaPdf(Section secao, String conteudo) throws DocumentException {
        if (conteudo.toLowerCase().startsWith("<table>")) {
            Tabela tabela = parseTabela(conteudo);
            int columns = tabela.getColumnCount();
            float colSizes [] = tabela.getColumnsSizes();

            PdfPTable table = new PdfPTable(columns);
            if (colSizes != null) {
                table.setWidths(colSizes);
            }
            table.setSpacingBefore(30f);
            table.getDefaultCell().setBorderWidth(0);
            table.getDefaultCell().setBorderColor(BaseColor.WHITE);
            table.getDefaultCell().setPadding(5);
////                table.setSpacing(5);

            boolean first = true;
            for (Linha linha : tabela.getLinhas()) {
                Font font = null;
                for (Celula celula : linha.celulas) {
                    if (first) {
                        font = tabFontBold;
                    } else {
                        font = tabFont;
                    }
                    String texto = celula.conteudo.replace("<br/>", "\n");
                    Paragraph cp = new Paragraph(texto, font);
                    PdfPCell c = new PdfPCell(cp);
                    if (first) {
                        //c.setBackgroundColor(new BaseColor(240, 240, 240));
                        //0, 91, 58
                        c.setBackgroundColor(new BaseColor(0, 91, 58));
                        c.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                    }
                    
                    if ("right".equals(celula.align)) {
                        cp.setAlignment(Element.ALIGN_RIGHT);
                        c.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    } else if ("center".equals(celula.align)) {
                        cp.setAlignment(Element.ALIGN_CENTER);
                        c.setHorizontalAlignment(Element.ALIGN_CENTER);
                    } else if ("left".equals(celula.align)) {
                        cp.setAlignment(Element.ALIGN_LEFT);
                        c.setHorizontalAlignment(Element.ALIGN_LEFT);
                    }
                    
                    c.setColspan(celula.colspan);
                    table.addCell(c);
                }
                if(first) {
                    first = false;
                }
            }
            secao.add(table);

        } else if (conteudo.toLowerCase().startsWith("<ol>")) {
            com.itextpdf.text.List list = parseLista(conteudo);
            secao.add(list);
        }
    }
    
    private com.itextpdf.text.List parseLista(String conteudo) {
        ListaOrdenada lo = null;
        try {
            JAXBContext context = JAXBContext.newInstance(ListaOrdenada.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream is = new ByteArrayInputStream((conteudo).getBytes("UTF-8"));
            lo = unmarshaller.unmarshal(new StreamSource(is), ListaOrdenada.class).getValue();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, conteudo, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, conteudo, ex);
        }
        com.itextpdf.text.List list = new com.itextpdf.text.List(true, false, 10);
        for (ListaItem item : lo.getLinhas()) {
            list.add(new ListItem(item.conteudo, tabFont));
        }
        return list;
    }

    private Tabela parseTabela(String conteudo) {
        Tabela tabela = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Tabela.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            InputStream is = new ByteArrayInputStream((conteudo).getBytes("UTF-8"));
            tabela = unmarshaller.unmarshal(new StreamSource(is), Tabela.class).getValue();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, conteudo, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, conteudo, ex);
        }
        return tabela;
    }

    private PdfPTable criaIndice(List<RelElemento> es) {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(80f);
        try {
            table.setWidths(new int[]{85, 15});
        } catch (DocumentException ex) {
            Logger.getLogger(ReportToPdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.getDefaultCell().setBorderWidth(0);
        table.getDefaultCell().setBorderColor(BaseColor.WHITE);
        table.getDefaultCell().setPadding(5);
        
        for (RelElemento item : es) {
            Phrase p = new Phrase(item.getTitulo());
            PdfPCell c = new PdfPCell(p);
            c.setBorderWidth(0);
            c.setBorderColor(BaseColor.WHITE);
            c.setPadding(5);
            table.addCell(c);
            
            Paragraph pa = new Paragraph(Integer.toString(item.getPagina()));
            pa.setAlignment(Element.ALIGN_RIGHT);
            c = new PdfPCell(pa);
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c.setBorderWidth(0);
            c.setBorderColor(BaseColor.WHITE);
            c.setPadding(5);
            table.addCell(c);
        }
        return table;
    }
    
    class GeneralHeader extends PdfPageEventHelper {
        private RelElemento ultimoElemento = null;

        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            if (elementoAtual != null) {
                if (ultimoElemento == null || !ultimoElemento.getTitulo().equals(elementoAtual.getTitulo())) {
                    ultimoElemento = elementoAtual;
                    ultimoElemento.setPagina(writer.getPageNumber());
                }
            }
            
            try {

                boolean test = Boolean.parseBoolean(System.getProperty("fwc.report.test", "false"));
                PdfContentByte cb = writer.getDirectContent();
                Image image = null;
                if (test) {
                    image = Image.getInstance("./web/arquivos/green_logo.png");
                } else {
                    URL url = new URL(uriBase + "/arquivos/green_logo.png");
                    image = Image.getInstance(url);
                }
                
                image.scalePercent(45f);
                image.setAbsolutePosition(25, document.getPageSize().getHeight()-68);
                cb.addImage(image);
            
                cb.beginText();
                BaseFont fontBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, "Cp1252", false);
                BaseFont fontNormal = BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false);
                
                int ident = 90;

                cb.moveText(ident, document.getPageSize().getHeight()-34);
                int fontSize = 16;
                cb.setFontAndSize(fontNormal, fontSize);
                cb.showText("Cliente: ");
                cb.setFontAndSize(fontBold, fontSize);
                cb.showText(cliente.getNomCliente());
                cb.endText();
                
                cb.beginText();
                cb.moveText(ident, document.getPageSize().getHeight()-57);
                cb.setFontAndSize(fontNormal, fontSize);
                cb.showText("Responsável Cliente: ");
                cb.setFontAndSize(fontBold, fontSize);
                cb.showText(cliente.getNomResponsavel());
                cb.endText();                
            } catch (Exception de) {
                throw new ExceptionConverter(de);
            }
        }
    }
    
    private class BodyHeaderFooter extends GeneralHeader {

        @Override
        public void onEndPage(PdfWriter writer, Document document) {            
            PdfPTable table = new PdfPTable(3);
            Font font = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.WHITE);
            try {
                table.getDefaultCell().setBorderColor(BaseColor.WHITE);
                table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.getDefaultCell().setBorder(Rectangle.BOX);
                
                table.setWidths(new int[]{20, 40, 20});
                table.setTotalWidth(527);

                PdfPCell cell = new PdfPCell(new Paragraph("CONFIDENCIAL", font));
                cell.setBorder(Rectangle.BOX);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBackgroundColor(new BaseColor(new Color(117,197,211)));
                table.addCell(cell);
                
//                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                Paragraph p = new Paragraph(String.format("Pág %d", writer.getPageNumber()),font);
                p.add(new Phrase("\nCopyright ©FWC GROUP Inc. All rights reserved.",font));
                cell = new PdfPCell(p);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
                table.getDefaultCell().setBackgroundColor(new BaseColor(new Color(97,149,174)));
                cell.setBackgroundColor(new BaseColor(new Color(97,149,174)));
                table.addCell(p);
                
//                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                p = new Paragraph("Data " +DateFormat.getDateInstance(DateFormat.SHORT).format(new java.util.Date()),font);
                p.add(new Phrase("\nVersão 2.0",font));
                cell = new PdfPCell(p);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.getDefaultCell().setBackgroundColor(new BaseColor(new Color(65,105,115)));
                cell.setBackgroundColor(new BaseColor(new Color(65,105,115)));
                table.addCell(p);
                int heigth = 37;
                table.writeSelectedRows(0, 2, 34, heigth, writer.getDirectContent());
            } catch (DocumentException de) {
                throw new ExceptionConverter(de);
            }
        }
    }
    
    private void concatenaPdfs(List<InputStream> streamOfPdfFiles, Document document, OutputStream outputStream) throws IOException, DocumentException {

        try {
            List<InputStream> pdfs = streamOfPdfFiles;
            List<PdfReader> readers = new ArrayList<PdfReader>();
            Iterator<InputStream> iteratorPDFs = pdfs.iterator();

            // Create Readers for the pdfs.
            while (iteratorPDFs.hasNext()) {
                InputStream pdf = iteratorPDFs.next();
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
            }
            // Create a writer for the outputstream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
            // data

            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;
            Iterator<PdfReader> iteratorPDFReader = readers.iterator();

            // Loop through the PDF files and add to the output.
            while (iteratorPDFReader.hasNext()) {
                PdfReader pdfReader = iteratorPDFReader.next();

                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);
                }
                pageOfCurrentReaderPDF = 0;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }
}
