package br.com.jcomputacao.util;

/**
 * 03/12/2010 17:37:19
 * @author Murilo
 */
public class StringUtil {

    public final static int ALINHAMENTO_ESQUERDA = 0;
    public final static int ALINHAMENTO_DIREITA = 1;

    public static String preenche(int i, char caracter) {
        String aux = "";
        for (int j = 0; j < i; j++) {
            aux += caracter;
        }
        return aux;
    }

    /** Creates a new instance of StringUtil */
    public static String prepareString(String aux) {
        if (aux == null) {
            aux = "";
        } else {
            aux = replace(aux, "\\", "\\\\");
            aux = replace(aux, "'", "\'");
        }
        return aux;
    }

    public static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();

        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }

    public static String ajusta(String aux, int tamanho, int alinhamento) {
        if (aux == null) {
            aux = espaco(tamanho);
        }

        if (aux.length() > tamanho) {
            aux = aux.substring(0, tamanho);
        } else if (aux.length() < tamanho) {
            if (alinhamento == 0) {
                aux += espaco(tamanho - aux.length());
            } else {
                aux = espaco(tamanho - aux.length()) + aux;
            }
        }
        return aux;
    }

    public static String ajusta(String aux, int tamanho, int alinhamento, char caracter) {
        if (aux == null) {
            aux = preenche(tamanho, caracter);
        }

        if (aux.length() > tamanho) {
            if (alinhamento == ALINHAMENTO_ESQUERDA) {
                aux = aux.substring(0, tamanho);
            } else {
                int tam = aux.length();
                aux = aux.substring(tam - tamanho, tam);
            }
        } else if (aux.length() < tamanho) {
            if (alinhamento == ALINHAMENTO_ESQUERDA) {
                aux += preenche(tamanho - aux.length(), caracter);
            } else {
                aux = preenche(tamanho - aux.length(), caracter) + aux;
            }
        }
        return aux;
    }

    public static String espaco(int i) {
        String aux = "";
        for (int j = 0; j < i; j++) {
            aux += " ";
        }
        return aux;
    }

    public static boolean isNull(String str) {
        return (str == null || str.trim().equals(""));
    }

    public static boolean isNotNull(String str) {
        return !isNull(str);
    }

    public static String noDeadKeysToUpperCase(String str) {
        return str.toUpperCase().replace("�", "A").replace("�", "A").replace("�", "A").replace("�", "A").replace("�", "A").replace("�", "C").replace("�", "N").replace("�", "E").replace("�", "E").replace("�", "E").replace("�", "E").replace("�", "I").replace("�", "I").replace("�", "I") //                .replace("?", "I")
                .replace("�", "I").replace("�", "O").replace("�", "O").replace("�", "O").replace("�", "O").replace("�", "O").replace("�", "U").replace("�", "U").replace("�", "U").replace("�", "U");
//                .replace("?", "U");
    }

    public static String noSpecialKeys(String str) {
        return noSpecialKeys(str, null);
    }

    public static String noSpecialKeys(String str, String ignorados[]) {
        if (str == null) {
            return null;
        }
        String substituidos[][] = new String[][]{
            {"�", " "}, {",", " "}, {"�", " "}, {"-", " "}, {"&", "e"}, {"!", " "}, {"@", " "}, {"#", " "}, {"$", " "}, {"%", " "}, {"*", " "}, {"{", " "}, {"}", " "}, {"_", " "}, {"=", " "}, {"+", " "}, {"<", " "}, {">", " "}, {";", " "}, {":", " "}, {"�", " "} //,{"", " "}
            , {"}", " "}, {"[", " "}, {"]", " "}, {"|", " "}, {"'", " "}, {"`", " "}, {"^", " "}, {".", " "}, {"~", " "}};
        String aux = noDeadKeysToUpperCase(str);
        for (String[] substituicao : substituidos) {
            boolean naoIgnorada = true;
            if (ignorados != null) {
                for (String ignorado : ignorados) {
                    if (ignorado.equals(substituicao[0])) {
                        naoIgnorada = false;
                    }
                }
            }
            if (naoIgnorada) {
                aux = aux.replace(substituicao[0], substituicao[1]);
            }
        }
        return soEspacoSimples(aux).trim();
    }

    public static String somenteNumeros(String string) {
        if (isNull(string)) {
            return string;
        }
        char[] characters = string.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char character : characters) {
            if (Character.isDigit(character)) {
                sb.append(character);
            }
        }
        return sb.toString();
    }

    public static String somenteNumerosELetras(String string) {
        return somenteNumerosELetras(string, false);
    }

    public static String somenteNumerosELetras(String string, boolean ignoraEspacos) {
        if (isNull(string)) {
            return string;
        }
        char[] characters = string.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char character : characters) {
            if (Character.isLetterOrDigit(character)
                    || (ignoraEspacos && Character.isSpaceChar(character))) {
                sb.append(character);
            }
        }
        return sb.toString();
    }

    public static String ieInvalido(String ie) {
        if (ie.length() < 14) {
            ie = "";
        }
        return ie;
    }

    public static String limitaTamanho(String string, int tamanhoMaximo) {
        if (isNull(string)) {
            return string;
        }
        if (string.length() > tamanhoMaximo) {
            string = string.substring(0, tamanhoMaximo);
        }
        return string;
    }

    public static String notNull(String str) {
        return (str == null ? "" : str);
    }

    public static String soEspacoSimples(String astring) {
        if (astring == null) {
            return null;
        }
        while (astring.contains("\t")) {
            astring = astring.replace("\t", " ");
        }
        while (astring.contains("  ")) {
            astring = astring.replace("  ", " ");
        }
        return astring;
    }

    public static String soPrimeiraMaiuscula(String astring) {
        if (astring == null) {
            return null;
        }
        String palavras[] = astring.split(" ");
        StringBuilder sb = new StringBuilder();
        boolean primeira = true;
        for (String palavra : palavras) {
            if (primeira) {
                primeira = false;
            } else {
                sb.append(" ");
            }
            String tratada = soPrimeiraMaiusculaPalavra(palavra);
            sb.append(tratada);
        }
        return sb.toString();
    }

    public static String soPrimeiraMaiusculaPalavra(String astring) {
        if (astring == null) {
            return null;
        }
        String string = astring.toLowerCase();
        if (string.length() == 1) {
            return string;
        }
        if (string.equals("da") || string.equals("de") || string.equals("do")) {
            return string;
        }
        return (string.length() > 2 ? string.substring(0, 1).toUpperCase() + string.substring(1) : string);
    }

    public static boolean isNullOrEmpty(String string) {
        return (string == null || "".equals(string.trim()));
    }

    public static boolean validaCpf(String cpf) {
        cpf = StringUtil.somenteNumeros(cpf);
//        cpf = cpf.replace(".", "").replace("-", "").replace("/", "").replace(" ", "");
        if (cpf.length() != 11) {
            return false;
        }
        if (cpf.equals("00000000000")
                || cpf.equals("11111111111")
                || cpf.equals("22222222222")
                || cpf.equals("33333333333")
                || cpf.equals("44444444444")
                || cpf.equals("55555555555")
                || cpf.equals("66666666666")
                || cpf.equals("77777777777")
                || cpf.equals("88888888888")
                || cpf.equals("99999999999")) {
            return false;
        }
        char[] digitosCpf = cpf.toCharArray();
        int[] valores = new int[9];
        valores[0] = Integer.parseInt(Character.toString(digitosCpf[0])) * 10;
        valores[1] = Integer.parseInt(Character.toString(digitosCpf[1])) * 9;
        valores[2] = Integer.parseInt(Character.toString(digitosCpf[2])) * 8;
        valores[3] = Integer.parseInt(Character.toString(digitosCpf[3])) * 7;
        valores[4] = Integer.parseInt(Character.toString(digitosCpf[4])) * 6;
        valores[5] = Integer.parseInt(Character.toString(digitosCpf[5])) * 5;
        valores[6] = Integer.parseInt(Character.toString(digitosCpf[6])) * 4;
        valores[7] = Integer.parseInt(Character.toString(digitosCpf[7])) * 3;
        valores[8] = Integer.parseInt(Character.toString(digitosCpf[8])) * 2;
        int primeiroDigitoControle = 0;
        int x = 0;
        while (x < 9) {
            primeiroDigitoControle = primeiroDigitoControle + valores[x];
            x++;
        }
        primeiroDigitoControle = ((primeiroDigitoControle * 10) % 11);
        if (primeiroDigitoControle > 9) {
            primeiroDigitoControle = primeiroDigitoControle - 10;

        }
        valores[0] = Integer.parseInt(Character.toString(digitosCpf[1])) * 10;
        valores[1] = Integer.parseInt(Character.toString(digitosCpf[2])) * 9;
        valores[2] = Integer.parseInt(Character.toString(digitosCpf[3])) * 8;
        valores[3] = Integer.parseInt(Character.toString(digitosCpf[4])) * 7;
        valores[4] = Integer.parseInt(Character.toString(digitosCpf[5])) * 6;
        valores[5] = Integer.parseInt(Character.toString(digitosCpf[6])) * 5;
        valores[6] = Integer.parseInt(Character.toString(digitosCpf[7])) * 4;
        valores[7] = Integer.parseInt(Character.toString(digitosCpf[8])) * 3;
        valores[8] = primeiroDigitoControle * 2;
        int segundoDigitoControle = 0;
        x = 0;
        while (x < 9) {
            segundoDigitoControle = segundoDigitoControle + valores[x];
            x++;
        }
        segundoDigitoControle = ((segundoDigitoControle * 10) % 11);
        if (segundoDigitoControle > 9) {
            segundoDigitoControle = segundoDigitoControle - 10;
        }
        if ((primeiroDigitoControle + "" + segundoDigitoControle).equals(cpf.substring(9, 11))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validaCnpj(String cnpj) {
        boolean validaCnpj = Boolean.parseBoolean(System.getProperty("Validadores.validaCnpj", "true"));
        if (!validaCnpj) {
            return true;
        }
        cnpj = StringUtil.somenteNumerosELetras(cnpj, true);

        if (cnpj.length() != 14) {
            return false;
        }
        if (cnpj.equals("0000000000000")
                || cnpj.equals("1111111111111")
                || cnpj.equals("2222222222222")
                || cnpj.equals("3333333333333")
                || cnpj.equals("4444444444444")
                || cnpj.equals("5555555555555")
                || cnpj.equals("6666666666666")
                || cnpj.equals("7777777777777")
                || cnpj.equals("8888888888888")
                || cnpj.equals("9999999999999")) {
            return false;
        }

        char[] digitosCnpj = cnpj.toCharArray();
        int[] valores = new int[12];

        valores[0] = Integer.parseInt(Character.toString(digitosCnpj[0])) * 5;
        valores[1] = Integer.parseInt(Character.toString(digitosCnpj[1])) * 4;
        valores[2] = Integer.parseInt(Character.toString(digitosCnpj[2])) * 3;
        valores[3] = Integer.parseInt(Character.toString(digitosCnpj[3])) * 2;
        valores[4] = Integer.parseInt(Character.toString(digitosCnpj[4])) * 9;
        valores[5] = Integer.parseInt(Character.toString(digitosCnpj[5])) * 8;
        valores[6] = Integer.parseInt(Character.toString(digitosCnpj[6])) * 7;
        valores[7] = Integer.parseInt(Character.toString(digitosCnpj[7])) * 6;
        valores[8] = Integer.parseInt(Character.toString(digitosCnpj[8])) * 5;
        valores[9] = Integer.parseInt(Character.toString(digitosCnpj[9])) * 4;
        valores[10] = Integer.parseInt(Character.toString(digitosCnpj[10])) * 3;
        valores[11] = Integer.parseInt(Character.toString(digitosCnpj[11])) * 2;

        int primeiroDigitoControle = 0;
        int x = 0;
        while (x < 12) {
            primeiroDigitoControle = primeiroDigitoControle + valores[x];
            x++;
        }
        primeiroDigitoControle = ((primeiroDigitoControle * 10) % 11);
        if (primeiroDigitoControle > 9) {
            primeiroDigitoControle = primeiroDigitoControle - 10;
        }
        valores = new int[13];
        valores[0] = Integer.parseInt(Character.toString(digitosCnpj[0])) * 6;
        valores[1] = Integer.parseInt(Character.toString(digitosCnpj[1])) * 5;
        valores[2] = Integer.parseInt(Character.toString(digitosCnpj[2])) * 4;
        valores[3] = Integer.parseInt(Character.toString(digitosCnpj[3])) * 3;
        valores[4] = Integer.parseInt(Character.toString(digitosCnpj[4])) * 2;
        valores[5] = Integer.parseInt(Character.toString(digitosCnpj[5])) * 9;
        valores[6] = Integer.parseInt(Character.toString(digitosCnpj[6])) * 8;
        valores[7] = Integer.parseInt(Character.toString(digitosCnpj[7])) * 7;
        valores[8] = Integer.parseInt(Character.toString(digitosCnpj[8])) * 6;
        valores[9] = Integer.parseInt(Character.toString(digitosCnpj[9])) * 5;
        valores[10] = Integer.parseInt(Character.toString(digitosCnpj[10])) * 4;
        valores[11] = Integer.parseInt(Character.toString(digitosCnpj[11])) * 3;
        valores[12] = primeiroDigitoControle * 2;
        int segundoDigitoControle = 0;
        x = 0;
        while (x < 13) {
            segundoDigitoControle = segundoDigitoControle + valores[x];
            x++;
        }
        segundoDigitoControle = ((segundoDigitoControle * 10) % 11);
        if (segundoDigitoControle > 9) {
            segundoDigitoControle = segundoDigitoControle - 10;
        }
        //Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.FINER, " 1: "+primeiroDigitoControle+"2: "+segundoDigitoControle);
        if ((primeiroDigitoControle + "" + segundoDigitoControle).equals(cnpj.substring(12, 14))) {
            return true;
        } else {
            return false;
        }
    }

    public static String deModeda(String text) {
        text = text.replace(",", ".");
        text = text.replace("R", "");
        text = text.replace("$", "");
        text = text.replace(" ", "");
        return text;
    }
}
