//lc processing, symbol table generation, ic (final code)

import java.util.Scanner;
import java.util.ArrayList;

public class SP1 {
    public static void main(String[] args) {
        String code = "", s2 = "", mnemonic = "";
        String check1 = "end";
        String check2 = "END";

        String dc = "dc";
        String ds = "ds";
        String start = "start";
        String ic = "";

        String[] check = {};

        ArrayList<Integer> symbol_pointer = new ArrayList<>();
        ArrayList<String> symbol_name = new ArrayList<>();
        ArrayList<Integer> symbol_address = new ArrayList<>();

        int lc = 0, size = 0, st_pointer = 0, s_index = 0, s1 = 0, s3 = 0;

        System.out.println("Enter the code: ");
        do {
            lc = lc + size;
            Scanner input = new Scanner(System.in);
            code = input.nextLine();
            check = split(code);

            size = Integer.parseInt(check[0]);
            if (size != 100) {
                mnemonic = mnemonic + check[5];

                if(size != 100)
                {
                    System.out.printf("LC: %d\n", lc);
                }

                if (Integer.parseInt(check[1]) == 1) {
                    if (!symbol_name.contains(check[2])) {
                        st_pointer++;
                        symbol_pointer.add(st_pointer);
                        symbol_name.add(check[2]);
                        symbol_address.add(lc);
                    }
                }

                if (Integer.parseInt(check[3]) == 1) {
                    if (!symbol_name.contains(check[4])) {
                        st_pointer++;
                        symbol_pointer.add(st_pointer);
                        symbol_name.add(check[4]);
                        symbol_address.add(0);

                        if (check[5].toLowerCase().equals(dc) || check[5].toLowerCase().equals(ds)) {
                            symbol_address.set(st_pointer - 1, lc);
                        }

                    } else {
                        s_index = symbol_name.indexOf(check[4]);
                        if (check[5].toLowerCase().equals(dc) || check[5].toLowerCase().equals(ds)) {
                            symbol_address.set(s_index, lc);
                        }
                    }
                }

                ic = check[6];

                if (Integer.parseInt(check[7]) == 1) {
                    int index = symbol_name.indexOf(check[4]) + 1;
                    ic = ic + "(S," + index + ")";
                }

                System.out.print("IC: " + ic);
            }

//            System.out.print("IC: " + ic);

            if (size == 100) {
                System.out.print("Please enter valid mnemonics!");
            }
            System.out.println("\n");

        } while (!code.equals(check1) && !code.equals(check2) && size != 100);

        if(size != 100)
        {
            System.out.println("Symbol Table (Pointer, Name, Address): ");
            for (int i = 0; i < st_pointer; i++) {
                s1 = symbol_pointer.get(i);
                s2 = symbol_name.get(i);
                s3 = symbol_address.get(i);

                System.out.println(s1 + " | " + s2 + " | " + s3);
            }
        }
    }

    static int size(String mnemonic) {
        String[] inst = {"mov", "add", "sub", "mul", "jump", "print", "start", "ltorg", "end", "dc", "ds"};
        String[] type = {"IS", "IS", "IS", "IS", "IS", "IS", "AD", "AD", "AD", "DL", "DL"};
        int[] size = {2, 2, 2, 3, 3, 2, 0, 0, 0, 1, 1};
        int end = 0, i = 0, size1 = 0;

        do {
            if (mnemonic.equals(inst[i])) {
                end = 1;
                size1 = size[i];
            }
            i++;
        } while (end != 1);

        return size1;
    }

    static String op1_ic(String op1) {
        String ic = "";

        if (op1.length() == 1) {
            char reg = op1.charAt(0);
            switch (reg) {
                case 'A':
                    ic = ic + "R,1";
                    break;

                case 'B':
                    ic = ic + "R,2";
                    break;

                case 'C':
                    ic = ic + "R,3";
                    break;

                case 'D':
                    ic = ic + "R,4";
                    break;

                default:
                    ic = ic + "C," + reg;
            }
        } else {
            ic = ic + "C," + op1;
        }

        ic = "(" + ic + ")";
        return ic;
    }

    static String m_ic(String mnemonic) {
        String[] inst = {"mov", "add", "sub", "mul", "jump", "print", "start", "ltorg", "end", "dc", "ds"};
        String[] type = {"IS", "IS", "IS", "IS", "IS", "IS", "AD", "AD", "AD", "DL", "DL"};
        String[] opcode = {"04", "05", "06", "07", "08", "09", "01", "02", "03", "10", "11"};
        String ic = "";
        int end = 0, i = 0, index = 0;

        do {
            if (mnemonic.equals(inst[i])) {
                index = i;
                end = 1;
            }
            i++;
        } while (end != 1);

        ic = ic + type[index] + ",";
        ic = ic + opcode[index];
        ic = "(" + ic + ")";
        return ic;
    }

    static boolean mot(String mnemonic) {
        String[] inst = {"mov", "add", "sub", "mul", "jump", "print", "start", "ltorg", "end", "dc", "ds"};
//        String[] type = {"IS", "IS", "IS", "IS", "IS", "IS", "AD", "AD", "AD", "DL", "DL"};
//        String[] opcode = {"04", "05", "06", "07", "08", "09", "NA", "NA", "NA", "NA", "NA"};
        int[] size = {2, 2, 2, 3, 3, 2, 0, 0, 0, 1, 1};
        int end = 0, i = 0;
        boolean valid = false;

        do {
            if (mnemonic.equals(inst[i])) {
                valid = true;
                end = 1;
            }
            i++;
        } while (end != 1 && i != inst.length);
        return valid;
    }

    static String[] split(String code) {
        int j = 0;
        String check = "", label = "", mnemonic = "", op1 = "", op2 = "", symbol = "";
        int size = 0, end = 0, error = 0, literal = 0, label_yes = 0, symbol_yes = 0;
        int literal_yes = 0, lc_change = 0, symbol_ic = 0;
        String[] split1 = new String[8];
        String s1 = "A", s2 = "B", s3 = "C", s4 = "D";
        String start = "start";
        String ic = "", ic_m = "", ic_op1 = "", ic_op2 = "";
//        boolean valid;

        do {
            check = check + code.charAt(j);

            if (j == code.length() - 1) {
                end = 1;
                mnemonic = mnemonic + check;
                mnemonic = mnemonic.toLowerCase();
                if (mot(mnemonic)) {
                    size = size(mnemonic);
                    ic_m = m_ic(mnemonic);
                } else {
                    size = 100;
                }

            } else {
                switch (code.charAt(j + 1)) {
                    case ':':
                        end = 1;
                        label = label + check;
                        label_yes = 1;

                        j += 3;
                        while (code.charAt(j) != ' ') {
                            mnemonic = mnemonic + code.charAt(j);
                            mnemonic = mnemonic.toLowerCase();
                            j++;
                        }

                        int end1 = 0;
                        String check1 = "", value = "";
                        String check2 = "dc";
                        String check3 = "ds";

                        if (!mot(mnemonic)) {
                            j++;
                            check1 = check1 + code.charAt(j);
                            j++;
                            check1 = check1 + code.charAt(j);

                            if (check1.equals(check2) || check1.equals(check3)) {
                                symbol = mnemonic;
                                symbol_yes = 1;
                                mnemonic = check1;

                                j += 2;
                                do {
                                    value = value + code.charAt(j);
                                    j++;
                                } while (j < code.length());
                                ic_op1 = op1_ic(value);

                                if (check1.equals(check2)) {
                                    size = 1;
                                } else {
                                    size = Integer.parseInt(value);
                                }
                                ic_m = m_ic(mnemonic);

                                break;
                            } else {
                                j = j - 2;
                            }
                        }

                        j++;
                        while (code.charAt(j) != ',' && error != 1) {
                            op1 = op1 + code.charAt(j);
                            if (code.charAt(j) == ' ') {
                                error = 1;
                                j--;
                            }
                            j++;

                            if (j == code.length()) {
                                j = 0;
                                if (mnemonic.toLowerCase().equals(start)) {
                                    lc_change = 1;
                                } else {
                                    symbol_yes = 1;
                                    symbol_ic = 1;
                                    symbol = op1;
                                }
                                break;
                            }
                        }

                        if (symbol_yes != 1) {
                            ic_op1 = op1_ic(op1);
                        }

                        if (j == 0) {
                            if (mot(mnemonic)) {
                                size = size(mnemonic);
                                ic_m = m_ic(mnemonic);

                                if (mnemonic.equals(start) && lc_change == 1) {
                                    size = Integer.parseInt(op1);
                                }
                            } else {
                                size = 100;
                            }
                        }

                        if (j != 0) {
                            if (error == 0) {
                                j++;
                            }

                            while (j < code.length()) {
                                if (code.charAt(j) == '=') {
                                    j += 2;
                                    do {
                                        op2 = op2 + code.charAt(j);
                                        j++;
                                    } while (j < code.length() - 1);
                                    literal = Integer.parseInt(op2);
                                    literal_yes = 1;
                                    break;
                                }

                                op2 = op2 + code.charAt(j);
                                j++;
                            }

                            if (literal_yes != 1) {
                                if (!op2.equals(s1) && !op2.equals(s2) && !op2.equals(s3) && !op2.equals(s4)) {
                                    symbol = op2;
                                    symbol_yes = 1;
                                    symbol_ic = 1;
                                }
                            }

                            if (symbol_yes != 1) {
                                ic_op2 = op1_ic(op2);
                            }

                            if (mot(mnemonic)) {
                                size = size(mnemonic);
                                ic_m = m_ic(mnemonic);

                                if (lc_change == 1) {
                                    size = Integer.parseInt(op1);
                                }
                            } else {
                                size = 100;
                            }
                            if (error == 1) {
                                System.out.print("Comma missing after the first operand!\n");
                                System.exit(0);
                            }
                        }
                        break;

                    case ' ':
                        end = 1;
                        mnemonic = mnemonic + check;
                        mnemonic = mnemonic.toLowerCase();
                        end1 = 0;
                        check1 = "";
                        value = "";
                        check2 = "dc";
                        check3 = "ds";

                        if (!mot(mnemonic)) {
                            j += 2;
                            check1 = check1 + code.charAt(j);
                            j++;
                            check1 = check1 + code.charAt(j);

                            if (check1.equals(check2) || check1.equals(check3)) {
                                symbol = mnemonic;
                                symbol_yes = 1;
                                mnemonic = check1;

                                j += 2;
                                do {
                                    value = value + code.charAt(j);
                                    j++;
                                } while (j < code.length());
                                ic_op1 = op1_ic(value);

                                if (check1.equals(check2)) {
                                    size = 1;
                                } else {
                                    size = Integer.parseInt(value);
                                }
                                ic_m = m_ic(mnemonic);

                                break;
                            } else {
                                j = j - 3;
                            }
                        }

                        j += 2;
                        while (code.charAt(j) != ',' && error != 1) {
                            op1 = op1 + code.charAt(j);
                            if (code.charAt(j) == ' ') {
                                error = 1;
                                j--;
                            }
                            j++;

                            if (j == code.length()) {
                                j = 0;
                                if (mnemonic.toLowerCase().equals(start)) {
                                    lc_change = 1;
                                } else {
                                    symbol_yes = 1;
                                    symbol_ic = 1;
                                    symbol = op1;
                                }
                                break;
                            }
                        }

                        if (symbol_yes != 1) {
                            ic_op1 = op1_ic(op1);
                        }

                        if (j == 0) {
                            if (mot(mnemonic)) {
                                size = size(mnemonic);
                                ic_m = m_ic(mnemonic);

                                if (mnemonic.equals(start) && lc_change == 1) {
                                    size = Integer.parseInt(op1);
                                }
                            } else {
                                size = 100;
                            }
                        }

                        if (j != 0) {
                            if (error == 0) {
                                j++;
                            }

                            int lit = 0;
                            while (j < code.length()) {
                                if (code.charAt(j) == '=') {
                                    j += 2;
                                    do {
                                        op2 = op2 + code.charAt(j);
                                        j++;
                                    } while (j < code.length() - 1);
                                    literal = Integer.parseInt(op2);
//                                    System.out.printf("op2: %d", literal);
                                    lit = 1;
                                    break;
                                }

                                op2 = op2 + code.charAt(j);
                                j++;
                            }
                            if (lit == 0) {
                                if (!op2.equals(s1) && !op2.equals(s2) && !op2.equals(s3) && !op2.equals(s4)) {
                                    symbol = op2;
                                    symbol_yes = 1;
                                    symbol_ic = 1;
                                }

                                if (symbol_yes != 1) {
                                    ic_op2 = op1_ic(op2);
                                }
                            }

                            if (mot(mnemonic)) {
                                size = size(mnemonic);
                                ic_m = m_ic(mnemonic);

                                if (lc_change == 1) {
                                    size = Integer.parseInt(op1);
                                }
                            } else {
                                size = 100;
                            }

                            if (error == 1) {
                                System.out.print("Comma missing after the first operand!\n");
                                System.exit(0);
                            }
                        }
                        break;
                }
                j++;
            }
        } while (end != 1);

        //size of mnemonic, label_yes, label, symbol_yes, symbol, mnemonic, ic, symbol_ic
        split1[0] = String.valueOf(size);

        split1[1] = String.valueOf(label_yes);
        if (label_yes == 1) {
            split1[2] = label;
        } else {
            split1[2] = "no_label";
        }

        split1[3] = String.valueOf(symbol_yes);
        if (symbol_yes == 1) {
            split1[4] = symbol;
        } else {
            split1[4] = "no_symbol";
        }

        split1[5] = mnemonic;

        ic = ic + ic_m + ic_op1 + ic_op2;
        split1[6] = ic;
        split1[7] = String.valueOf(symbol_ic);

        return split1;
    }
}
