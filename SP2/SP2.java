//lc processing, literal table, pool table and IC

import java.util.Scanner;
import java.util.ArrayList;

public class SP2{
    public static void main(String[] args) {
        String code = "", l2 = "", mnemonic = "", ic = "";
        String ltorg = "ltorg";
        String check1 = "end";
        String check2 = "END";
        String[] check = {};

        ArrayList<Integer> literal_pointer = new ArrayList<>();
        ArrayList<String> literal_value = new ArrayList<>();
        ArrayList<Integer> literal_address = new ArrayList<>();

        ArrayList<Integer> pooltab_pointer = new ArrayList<>();
        ArrayList<Integer> pool_start_address = new ArrayList<> ();

        int lc = 0, size = 0, lt_pointer = 0, pt_pointer = 0, l_index = 0;
        int l1 = 0, l3 = 0, j = 0, p1 = 0, p2 = 0, i = 0, start1 = 0;

        System.out.println("Enter the code: ");
        do {
            lc = lc + size;
            Scanner input = new Scanner(System.in);
            code = input.nextLine();
            check = split(code);
            size = Integer.parseInt(check[0]);
            if (size != 100) {
                ic = check[4];
                mnemonic = mnemonic + check[5];

                System.out.printf("LC: %d", lc);

                if (Integer.parseInt(check[1]) == 1) {
                    lt_pointer++;
                    literal_pointer.add(lt_pointer);
                    literal_value.add(check[2]);
                    literal_address.add(0);
                    ic = ic + "(L," + lt_pointer + ")";
                }

                mnemonic = check[3].toLowerCase();
                if(mnemonic.equals(ltorg) || mnemonic.equals(check1))
                {
                    if(start1 < lt_pointer)
                    {
                        pt_pointer++;
                        pooltab_pointer.add(pt_pointer);
                        pool_start_address.add(start1 + 1);

                        do {
                            literal_address.add(start1,lc);
                            lc++;
                            start1++;
                        }while(start1 < lt_pointer);
                    }

                    if(mnemonic.equals(check1))
                    {
                        lc--;
                        System.out.printf("\nAfter storing literals at the end, the final LC is: %d", lc);
                    }
                }

                System.out.print("\nIC: " + ic);
            }

            if (size == 100) {
                System.out.print("\nPlease enter valid mnemonics!");
            }
            System.out.println("\n");
        } while (!code.equals(check1) && !code.equals(check2) && size != 100);

        if(size != 100)
        {
            System.out.println("Literal Table (Pointer, Value, Address): ");
            for (i = 0; i < lt_pointer; i++) {
                l1 = literal_pointer.get(i);
                l2 = literal_value.get(i);
                l3 = literal_address.get(i);

                System.out.println(l1 + " | " + l2 + " | " + l3);
            }
            System.out.print("\n");

            System.out.println("Pool Table (Pointer, Literal Table Pointer): ");
            for (i = 0; i < pt_pointer; i++) {
                p1 = pooltab_pointer.get(i);
                p2 = pool_start_address.get(i);
                System.out.println(p1 + " | " + p2);
            }
            System.out.print("\n");
        }

    }

    static int size(String mnemonic) {
        String[] inst = {"mov", "add", "sub", "mul", "jump", "print", "start", "ltorg", "end", "dc", "ds"};
        String[] type = {"IS", "IS", "IS", "IS", "IS", "IS", "AD", "AD", "AD", "DL", "DL"};
        int[] size = {2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 1};
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

    static String op1_ic(String op1)
    {
        String ic = "";

        if(op1.length() == 1)
        {
            char reg = op1.charAt(0);
            switch(reg)
            {
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
        }

        else
        {
            ic = ic + "C," + op1;
        }

        ic = "(" + ic + ")";
        return ic;
    }

    static String m_ic(String mnemonic)
    {
        String[] inst = {"mov", "add", "sub", "mul", "jump", "print", "start", "ltorg", "end", "dc", "ds"};
        String[] type = {"IS", "IS", "IS", "IS", "IS", "IS", "AD", "AD", "AD", "DL", "DL"};
        String[] opcode = {"04", "05", "06", "07", "08", "09", "01", "02", "03", "10", "11"};
        String ic = "";
        int end = 0, i = 0, index = 0;

        do {
            if(mnemonic.equals(inst[i]))
            {
                index = i;
                end = 1;
            }
            i++;
        }while(end != 1);

        ic = ic + type[index] + ",";
        ic = ic + opcode[index];
        ic = "(" + ic + ")";
        return ic;
    }

    static boolean mot(String mnemonic) {
        String[] inst = {"mov", "add", "sub", "mul", "jump", "print", "start", "ltorg", "end", "dc", "ds"};
        String[] type = {"IS", "IS", "IS", "IS", "IS", "IS", "AD", "AD", "AD", "DL", "DL"};
        String[] opcode = {"04", "05", "06", "07", "08", "09", "NA", "NA", "NA", "NA", "NA"};
        int[] size = {2, 2, 2, 2, 2, 2, 0, 0, 0, 1, 1};
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
        boolean valid;

        do {
            check = check + code.charAt(j);

            if (j == code.length() - 1) {
                end = 1;
                mnemonic = mnemonic + check;
                mnemonic = mnemonic.toLowerCase();
//                System.out.println("Mnemonic: " + mnemonic);
                if (mot(mnemonic)) {
//                    System.out.print("Valid Mnemonic: Yes");
                    size = size(mnemonic);
//                    System.out.printf("\nSize of mnemonic: %d", size);
                    ic_m = m_ic(mnemonic);
                } else {
//                    System.out.print("Invalid Mnemonic");
                    size = 100;
//                    System.out.printf("\nSize of mnemonic: %d", size);
                }

            } else {
                switch (code.charAt(j + 1)) {
                    case ':':
                        end = 1;
                        label = label + check;
                        label_yes = 1;
//                        System.out.println("Label: " + label);

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

//                                System.out.print("Symbol: " + symbol);
//                                System.out.print("\nMnemonic: " + mnemonic);
//                                System.out.print("\nValue of symbol: " + value);
//                                System.out.print("\nValid Mnemonic: Yes");

                                if (check1.equals(check2)) {
                                    size = 1;
                                } else {
                                    size = Integer.parseInt(value);
                                }
//                                System.out.printf("\nSize of mnemonic: %d", size);
                                ic_m = m_ic(mnemonic);

                                break;
                            } else {
                                j = j - 2;
                            }
                        }

//                        System.out.println("Mnemonic: " + mnemonic);
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
//                                    symbol_ic = 1;
                                    symbol = op1;
//                                    System.out.print("Symbol: " + symbol);
                                }
                                break;
                            }
                        }

                        if (symbol_yes != 1) {
//                            System.out.print("op1: " + op1);
                            ic_op1 = op1_ic(op1);
                        }

                        if (j == 0) {
                            if (mot(mnemonic)) {
//                                System.out.print("\nValid Mnemonic: Yes");
                                size = size(mnemonic);
//                                System.out.printf("\nSize of mnemonic: %d", size);
                                ic_m = m_ic(mnemonic);

                                if (mnemonic.equals(start) && lc_change == 1) {
                                    size = Integer.parseInt(op1);
                                }
                            } else {
//                                System.out.print("Invalid Mnemonic");
                                size = 100;
//                                System.out.print("\nSize of mnemonic: NA");
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
//                                    System.out.printf("\nLiteral: %d", literal);
                                    break;
                                }

                                op2 = op2 + code.charAt(j);
                                j++;
                            }

                            if (literal_yes != 1) {
                                if (!op2.equals(s1) && !op2.equals(s2) && !op2.equals(s3) && !op2.equals(s4)) {
                                    symbol = op2;
                                    symbol_yes = 1;
//                                    symbol_ic = 1;
//                                    System.out.println("\nSymbol: " + symbol);
                                }
                            }

                            if(symbol_yes != 1 && literal_yes != 1)
                            {
                                ic_op2 = op1_ic(op2);
//                                System.out.println("op2: " + op2);
                            }

                            if (mot(mnemonic)) {
//                                System.out.print("\nValid Mnemonic: Yes");
                                size = size(mnemonic);
//                                System.out.printf("\nSize of mnemonic: %d", size);
                                ic_m = m_ic(mnemonic);

                                if (lc_change == 1) {
                                    size = Integer.parseInt(op1);
                                }
                            } else {
//                                System.out.print("Invalid Mnemonic");
                                size = 100;
//                                System.out.print("\nSize of mnemonic: NA");
                            }
                            if (error == 1) {
//                                System.out.print("Comma missing after the first operand!");
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

//                                System.out.print("Symbol: " + symbol);
//                                System.out.print("\nMnemonic: " + mnemonic);
//                                System.out.print("\nValue of symbol: " + value);
//                                System.out.print("\nValid Mnemonic: Yes");

                                if (check1.equals(check2)) {
                                    size = 1;
                                } else {
                                    size = Integer.parseInt(value);
                                }
//                                System.out.printf("\nSize of mnemonic: %d", size);
                                ic_m = m_ic(mnemonic);

                                break;
                            } else {
                                j = j - 3;
                            }
                        }

//                        System.out.println("Mnemonic: " + mnemonic);
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
//                                    symbol_ic = 1;
                                    symbol = op1;
//                                    System.out.print("Symbol: " + symbol);
                                }
                                break;
                            }
                        }

                        if (symbol_yes != 1) {
//                            System.out.print("op1: " + op1);
                            ic_op1 = op1_ic(op1);
                        }

                        if (j == 0) {
                            if (mot(mnemonic)) {
//                                System.out.print("\nValid Mnemonic: Yes");
                                size = size(mnemonic);
//                                System.out.printf("\nSize of mnemonic: %d", size);
                                ic_m = m_ic(mnemonic);

                                if (mnemonic.equals(start) && lc_change == 1) {
                                    size = Integer.parseInt(op1);
                                }
                            } else {
//                                System.out.print("Invalid Mnemonic");
                                size = 100;
//                                System.out.print("\nSize of mnemonic: NA");
                            }
                        }

                        if (j != 0) {
                            if (error == 0) {
                                j++;
                            }

//                            int lit = 0;
                            while (j < code.length()) {
                                if (code.charAt(j) == '=') {
                                    j += 2;
                                    do {
                                        op2 = op2 + code.charAt(j);
                                        j++;
                                    } while (j < code.length() - 1);
                                    literal = Integer.parseInt(op2);
//                                    System.out.printf("\nLiteral: %d", literal);
                                    literal_yes = 1;
                                    break;
                                }

                                op2 = op2 + code.charAt(j);
                                j++;
                            }
                            if (literal_yes == 0) {
                                if (!op2.equals(s1) && !op2.equals(s2) && !op2.equals(s3) && !op2.equals(s4)) {
                                    symbol = op2;
                                    symbol_yes = 1;
//                                    symbol_ic = 1;
//                                    System.out.println("\nSymbol: " + symbol);
                                }

                                if(symbol_yes != 1 && literal_yes != 1)
                                {
                                    ic_op2 = op1_ic(op2);
//                                    System.out.println("\nop2: " + op2);
                                }
                            }

                            if (mot(mnemonic)) {
//                                System.out.print("\nValid Mnemonic: Yes");
                                size = size(mnemonic);
//                                System.out.printf("\nSize of mnemonic: %d", size);
                                ic_m = m_ic(mnemonic);

                                if (lc_change == 1) {
                                    size = Integer.parseInt(op1);
                                }
                            } else {
//                                System.out.print("Invalid Mnemonic");
                                size = 100;
//                                System.out.print("\nSize of mnemonic: NA");
                            }

                            if (error == 1) {
//                                System.out.print("\nComma missing after the first operand!");
                            }
                        }
                        break;
                }
                j++;
            }
        } while (end != 1);

//        size of mnemonic, literal_yes, literal, mnemonic, ic
        split1[0] = String.valueOf(size);

        split1[1] = String.valueOf(literal_yes);
        if (literal_yes == 1) {
            split1[2] = String.valueOf(literal);
        } else {
            split1[2] = "no_literal";
        }

        split1[3] = mnemonic;

        ic = ic + ic_m + ic_op1 + ic_op2;
        split1[4] = ic;

        return split1;
    } }

