
import java.io.*;
import java.util.ArrayList;

public class SP5 {
    public static void main(String[] args) {
        ArrayList<String> ST = new ArrayList<>();
        ST.add("main");
        ArrayList<String> CT = new ArrayList<>();
        CT.add(" - ");
        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> CT1 = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();

        int line_no = 1;
        int ST_index = 0;
        int i = 0;
        int j = 0;
        String loc = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/tanishakodam/Documents/Tanisha/DSA/Input_5"));
            FileWriter fw = new FileWriter("/Users/tanishakodam/Documents/Tanisha/DSA/Output_5", true);

            do {
                loc = br.readLine();
            } while ((!loc.equals("")));

            while (br.ready()) {
                loc = br.readLine();

                tokens = tokens(loc);
                boolean normal = output_type(tokens);
                if (normal) {
                    temp = normal_output(line_no, tokens, ST, CT1);
                    if (temp.size() != 0) {
                        for (i = 0; i < temp.size(); i++) {
                            CT1.add(temp.get(i));
                        }
                    }

                } else {
                    temp = declaration_output(line_no, tokens, ST);

                    do {
                        ST.add(temp.get(i));
                        i++;
                        CT.add(temp.get(i));
                        i++;
                    } while (i < temp.size());
                }

                line_no++;
            }

            i = 0;
            fw.write("\n" + "Symbol Table: " + "\n");
            fw.write("Identifier | Value" + "\n");
            for (i = 0; i < ST.size(); i++) {
                fw.write(ST.get(i) + " | " + CT.get(i) + "\n");
            }

            i = 0;
            fw.write("\n" + "Other constants: " + "\n");
            for (i = 0; i < CT1.size(); i++) {
                fw.write(CT1.get(i) + "\n");
            }

            fw.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static ArrayList<String> tokens(String loc) {
        int i = 0;
        int j = 0;
        String token = "";
        int exit = 0;
        ArrayList<String> tokens = new ArrayList<>();
        //int a = 0;
        if (loc.charAt(i) == ' ') {
            do {
                i++;
            } while (loc.charAt(i) == ' ');
        }

        if (loc.length() == 1 || i + 1 == loc.length()) {
            token = token + loc.charAt(i);
            tokens.add(token);
        } else {
            do {

                do {
                    token = token + loc.charAt(i);
                    i++;
                } while (loc.charAt(i) != ' ' && loc.charAt(i) != ';' && loc.charAt(i) != '(' && loc.charAt(i) != ')');

                tokens.add(token);
                token = "";

                switch (loc.charAt(i)) {
                    case ' ':
                        i++;
                        break;

                    case ';', ')':
                        tokens.add(String.valueOf(loc.charAt(i)));
                        exit = 1;
                        break;

                    //if(a != b && a < c)
                    case '(':
                        tokens.add(String.valueOf(loc.charAt(i)));
                        i++;
                        if (loc.charAt(i) == ')') {
                            tokens.add(String.valueOf(loc.charAt(i)));
                            exit = 1;
                        }
                }
            } while (i < loc.length() && exit != 1);
        }

        return tokens;
    }

    static boolean output_type(ArrayList<String> tokens) {
        int j = 1;
        boolean normal = true;
        //int a = 5, b = 6;
        // int a = 6;
        if (tokens.get(0).equals("int") || tokens.get(0).equals("long") || tokens.get(0).equals("float") || tokens.get(0).equals("char")) {
            normal = false;
        }

        if (tokens.size() > 1) {
            if (tokens.get(1).equals("main")) {
                normal = true;
            }
        }

        return normal;
    }

    static ArrayList<String> declaration_output(int line_no, ArrayList<String> tokens, ArrayList<String> ST) {
        ArrayList<String> pairs = new ArrayList<>();
        //int a = 6;
        //int a = 5, b = 239;
        String lexeme = "";
        String token_type = "";
        int token_value = 0;
        String token_output = "";
        int count = 0;

        try {
            FileWriter fw = new FileWriter("/Users/tanishakodam/Documents/Tanisha/DSA/Output_5", true);
            lexeme = tokens.get(0);
            token_type = "keyword";
            token_value = keyword_no(tokens.get(0));
            token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                    " | " + String.valueOf(token_value);
            fw.write(token_output + "\n");

            lexeme = tokens.get(1);
            token_type = "identifier";
            pairs.add(tokens.get(1));
            count++;
            token_value = ST.size() + 1;
            token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                    " | " + String.valueOf(token_value);
            fw.write(token_output + "\n");

            lexeme = tokens.get(2);
            token_type = "operator";
            token_value = operator_no(tokens.get(2));
            token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                    " | " + String.valueOf(token_value);
            fw.write(token_output + "\n");

            lexeme = tokens.get(3);
            token_type = "constant";
            pairs.add(tokens.get(3));
            token_value = ST.size() + 1;
            token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                    " | " + String.valueOf(token_value);
            fw.write(token_output + "\n");

            //int a = 5, b = 5;
            lexeme = tokens.get(4);
            token_type = "delimiter";
            token_value = delimiter_no(tokens.get(4));
            token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                    " | " + String.valueOf(token_value);
            fw.write(token_output + "\n");

            if (tokens.get(4).equals(";")) {
                fw.close();
            } else {
                lexeme = tokens.get(5);
                token_type = "identifier";
                count++;
                pairs.add(tokens.get(5));
                token_value = ST.size() + 2;
                token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                        " | " + String.valueOf(token_value);
                fw.write(token_output + "\n");

                lexeme = tokens.get(6);
                token_type = "operator";
                token_value = operator_no(tokens.get(6));
                token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                        " | " + String.valueOf(token_value);
                fw.write(token_output + "\n");

                lexeme = tokens.get(7);
                token_type = "constant";
                pairs.add(tokens.get(7));
                token_value = ST.size() + 2;
                token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                        " | " + String.valueOf(token_value);
                fw.write(token_output + "\n");

                lexeme = tokens.get(8);
                token_type = "delimiter";
                token_value = delimiter_no(tokens.get(8));
                token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                        " | " + String.valueOf(token_value);
                fw.write(token_output + "\n");

                fw.close();
            }

        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        return pairs;
    }

    static ArrayList<String> normal_output(int line_no, ArrayList<String> tokens, ArrayList<String> ST, ArrayList<String> CT1) {
        String lexeme = "";
        String token_type = "";
        int token_value = 0;
        String token_output = "";
        ArrayList<String> CT2 = new ArrayList<>();

        FileWriter fw = null;
        try {
            fw = new FileWriter("/Users/tanishakodam/Documents/Tanisha/DSA/Output_5", true);
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals("main")) {
                    lexeme = tokens.get(i);
                    token_type = "identifier";
                    token_value = 1;
                    token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                            " | " + String.valueOf(token_value);
                    fw.write(token_output + "\n");
                } else if (keywords(tokens.get(i))) {
                    lexeme = tokens.get(i);
                    token_type = "keyword";
                    token_value = keyword_no(tokens.get(i));
                    token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                            " | " + String.valueOf(token_value);
                    fw.write(token_output + "\n");

                } else if (delimiter(tokens.get(i))) {
                    lexeme = tokens.get(i);
                    token_type = "delimiter";
                    token_value = delimiter_no(tokens.get(i));
                    token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                            " | " + String.valueOf(token_value);
                    fw.write(token_output + "\n");
                } else if (operator(tokens.get(i))) {
                    lexeme = tokens.get(i);
                    token_type = "operator";
                    token_value = operator_no(tokens.get(i));
                    token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                            " | " + String.valueOf(token_value);
                    fw.write(token_output + "\n");
                } else if (identifier(ST, tokens.get(i))) {
                    lexeme = tokens.get(i);
                    token_type = "identifier";
                    token_value = identifier_no(ST, tokens.get(i));
                    token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                            " | " + String.valueOf(token_value);
                    fw.write(token_output + "\n");
                } else {
                    lexeme = tokens.get(i);
                    token_type = "constant";
                    token_value = CT1.size() + 1;
                    CT2.add(tokens.get(i));
                    token_output = String.valueOf(line_no) + " | " + lexeme + " | " + token_type +
                            " | " + String.valueOf(token_value);
                    fw.write(token_output + "\n");
                }
            }
            fw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return CT2;
    }

    static boolean operator(String operator) {
        boolean o = false;
        String[] operators = {"+", "-", "*", "/", "%", "=", "<", ">", "!=", "<=", ">="};
        for (int i = 0; i < operators.length; i++) {
            if (operator.equals(operators[i])) {
                o = true;
                break;
            }
        }

        return o;
    }

    static int operator_no(String operator) {
        int operator_no = 0;
        String[] operators = {"+", "-", "*", "/", "%", "=", "<", ">", "!=", "<=", ">="};
        for (int i = 0; i < operators.length; i++) {
            if (operator.equals(operators[i])) {
                operator_no = i + 1;
                break;
            }
        }

        return operator_no;
    }

    static int constant_no(ArrayList<String> ST, String identifier) {
        int cons_no = 0;
        for (int i = 0; i < ST.size(); i++) {
            if (identifier.equals(ST.get(i))) {
                cons_no = i + 1;
                break;
            }
        }

        return cons_no;
    }

    private static boolean identifier(ArrayList<String> ST, String token) {
        boolean identifier = false;

        for (int i = 0; i < ST.size(); i++) {
            if (token.equals(ST.get(i))) {
                identifier = true;
                break;
            }
        }
        return identifier;
    }

    static int identifier_no(ArrayList<String> ST, String identifier) {
        int identifier_no = 0;
        for (int i = 0; i < ST.size(); i++) {
            if (identifier.equals(ST.get(i))) {
                identifier_no = i + 1;
                break;
            }
        }

        return identifier_no;
    }

    static boolean keywords(String token) {
        boolean keyword = false;

        String[] keywords = {
                "auto", "break", "case", "char",
                "const", "continue", "default", "do",
                "double", "else", "enum", "extern",
                "float", "for", "goto", "if",
                "int", "long", "register", "return",
                "short", "signed", "sizeof", "static",
                "struct", "switch", "typedef", "union",
                "unsigned", "void", "volatile", "while"
        };

        for (int i = 0; i < keywords.length; i++) {
            if (token.equals(keywords[i])) {
                keyword = true;
                break;
            }
        }

        return keyword;
    }

    static int keyword_no(String keyword) {
        String[] keywords = {
                "auto", "break", "case", "char",
                "const", "continue", "default", "do",
                "double", "else", "enum", "extern",
                "float", "for", "goto", "if",
                "int", "long", "register", "return",
                "short", "signed", "sizeof", "static",
                "struct", "switch", "typedef", "union",
                "unsigned", "void", "volatile", "while"
        };

        int k_no = 0;
        for (int i = 0; i < keywords.length; i++) {
            if (keyword.equals(keywords[i])) {
                k_no = i + 1;
                break;
            }
        }

        return k_no;
    }

    static boolean delimiter(String token) {
        String[] delimiters = {"{", "}", "(", ")", ";", ","};
        boolean delimiter = false;

        for (int i = 0; i < delimiters.length; i++) {
            if (token.equals(delimiters[i])) {
                delimiter = true;
                break;
            }
        }

        return delimiter;
    }

    static int delimiter_no(String delimiter) {
        String[] delimiters = {"{", "}", "(", ")", ";", ","};
        int d_no = 0;

        for (int i = 0; i < delimiters.length; i++) {
            if (delimiter.equals(delimiters[i])) {
                d_no = i + 1;
                break;
            }
        }
        return d_no;
    }

}
