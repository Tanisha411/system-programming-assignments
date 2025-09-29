

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class SP4 {
    public static void main(String[] args)
    {
        print_mnt();
        print_mdt();
        print_evn1();
        print_evn2();

        try
        {
            String code = "";
            BufferedReader br = new BufferedReader(new FileReader("/Users/tanishakodam/Documents/Tanisha/DSA/Input_4"));
            File fo = new File("/Users/tanishakodam/Documents/Tanisha/DSA/Output_4_1");

            while(br.ready())
            {
                code = br.readLine();
                output(code);
            }
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }

    }

    static void print_mnt()
    {
        String[] macros = {"CLEARMEM", "INCR"};
        String[] pp = {"1", "2"};
        String[] kp = {"1", "1"};
        String[] mdtp = {"1", "6"};
        String[] kpdtp = {"1", "2"};
        String[] sstp = {"1", "1"};

        try
        {
            FileWriter fw3 = new FileWriter("/Users/tanishakodam/Documents/Tanisha/DSA/Output_4_2", true);
            fw3.write("\nMNT:\n");

            for(int i = 0; i < macros.length; i++)
            {
                fw3.write(macros[i] + " ");
                fw3.write(pp[i] + " ");
                fw3.write(kp[i] + " ");
                fw3.write(mdtp[i] + " ");
                fw3.write(kpdtp[i] + " ");
                fw3.write(sstp[i] + " ");
                fw3.write("\n");
            }

            fw3.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    static void print_mdt()
    {
        ArrayList<String> code = new ArrayList<>();
        code.add("LCL (E,1)");
        code.add("(E,1) SET 10");
        code.add("MOVER (P,2), =0");
        code.add("MOVEM (P,2), (P,1)+(E,1)");
        code.add("MEND");
        code.add("MOVER (P,3), (P,1)");
        code.add("ADD (P,3), (P,2)");
        code.add("LCL (E,1)");
        code.add("(E,1) SET 5");
        code.add("ADD (P,3), (E,1)");
        code.add("MOVEM (P,3), (P,1)");
        code.add("MEND");

        try
        {
            FileWriter fw3 = new FileWriter("/Users/tanishakodam/Documents/Tanisha/DSA/Output_4_2", true);
            fw3.write("\nMDT:\n");

            for(int i = 0; i < code.size(); i++)
            {
                fw3.write(code.get(i) + "\n");
            }

            fw3.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    static void print_evn1()
    {
        ArrayList<String> ev_name = new ArrayList<>();
        ArrayList<String> ev_value = new ArrayList<>();
        ev_name.add("M");
        ev_value.add("10");

        try
        {
            FileWriter fw3 = new FileWriter("/Users/tanishakodam/Documents/Tanisha/DSA/Output_4_2", true);
            fw3.write("\nMacro 1:\n");
            fw3.write("EVNTAB | EVTAB\n");

            for(int i = 0; i < ev_name.size(); i++)
            {
                fw3.write(ev_name.get(i) + " ");
                fw3.write(ev_value.get(i) + "\n");
            }

            fw3.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    static void print_evn2()
    {
        ArrayList<String> ev_name = new ArrayList<>();
        ArrayList<String> ev_value = new ArrayList<>();
        ev_name.add("OPR");
        ev_value.add("5");

        try
        {
            FileWriter fw3 = new FileWriter("/Users/tanishakodam/Documents/Tanisha/DSA/Output_4_2", true);
            fw3.write("\nMacro 2:\n");
            fw3.write("EVNTAB | EVTAB\n");

            for(int i = 0; i < ev_name.size(); i++)
            {
                fw3.write(ev_name.get(i) + " ");
                fw3.write(ev_value.get(i) + "\n");
            }

            fw3.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    static String evn1(int i)
    {
//        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<String> ev_name = new ArrayList<>();
        ArrayList<String> ev_value = new ArrayList<>();
//        index.add(1);
        ev_name.add("M");
        ev_value.add("10");

        String ev = "";
        ev = ev_value.get(i-1);

        return ev;
    }

    static String evn2(int i)
    {
//        ArrayList<Integer> index = new ArrayList<>();
        ArrayList<String> ev_name = new ArrayList<>();
        ArrayList<String> ev_value = new ArrayList<>();
//        index.add(1);
        ev_name.add("OPR");
        ev_value.add("5");

        String ev = "";
        ev = ev_value.get(i-1);

        return ev;
    }

    static String kpdtab(int i)
    {
        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> value = new ArrayList<>();

        name.add("REG");
        value.add("AREG");

        name.add("REG1");
        value.add("AREG");

        String kp = "";
        kp = value.get(i-1);

        return kp;
    }

    static boolean valid_mnemonic(String mnemonic)
    {
        int check = 0, i = 0;
        boolean valid = false;
        mnemonic = mnemonic.toLowerCase();

        ArrayList<String> mnemonics = new ArrayList<>();
        mnemonics.addAll(Arrays.asList("mover", "movem", "mov", "add", "sub", "mul", "jump", "print", "start", "ltorg", "end", "dc", "ds"));

        do {
            if(mnemonics.get(i).equals(mnemonic))
            {
                check = 1;
                valid = true;
            }
            i++;
        }while(check != 1 && i < mnemonics.size());

        return valid;
    }

    static int[] mnt(String name)
    {
        String[] macros = {"CLEARMEM", "INCR"};
//        String[] pp = {"1", "2"};
//        String[] kp = {"1", "1"};
        String[] mdtp = {"1", "6"};
//        String[] kpdtp = {"1", "2"};
//        String[] sstp = {"1", "1"};
        int i = 0, check = 0, valid = 0, mdt = 0;

        do {
            if(macros[i].equals(name))
            {
                check = 1;
                valid = 1;
                mdt = Integer.parseInt(mdtp[i]);
            }
            i++;
        }while(check != 1 && i < macros.length);

        int[] result = new int[2];
        result[0] = valid;
        result[1] = mdt;

        return result;
    }

    static int[] AL(String name)
    {
        String[] macros = {"CLEARMEM", "INCR"};
        String[] pp = {"1", "2"};
        String[] kp = {"1", "1"};
        int i = 0, check = 0;

        do {
            if(macros[i].equals(name))
            {
                check = 1;
            }
            i++;
        }while(check != 1 && i < macros.length);
        i--;

        int[] result = new int[2];
        result[0] = Integer.parseInt(pp[i]); // #pp
        result[1] = result[0] + Integer.parseInt(kp[i]); // #pp + #kp

        return result;
    }

    static ArrayList<String> mdt(int mdtp, String line1, int pp, int al)
    {
        int i = mdtp - 1;

        String[] macros = {"CLEARMEM", "INCR"};
        String[] kpdtp = {"1", "2"}; // from MNT

        ArrayList<String> code = new ArrayList<>();
        code.add("LCL (E,1)");
        code.add("(E,1) SET 10");
        code.add("MOVER (P,2), =0");
        code.add("MOVEM (P,2), (P,1)+(E,1)");
        code.add("MEND");
        code.add("MOVER (P,3), (P,1)");
        code.add("ADD (P,3), (P,2)");
        code.add("LCL (E,1)");
        code.add("(E,1) SET 5");
        code.add("ADD (P,3), (E,1)");
        code.add("MOVEM (P,3), (P,1)");
        code.add("MEND");

//        CLEARMEM 30,&REG=BREG
        String[] aptab = new String[al];
        String name = "";
        int k = 0;
        do {
            name = name + line1.charAt(k);
            k++;
        }while(line1.charAt(k) != ' ');

        int i1 = 0;
        int check = 0;
        do {
            if(macros[i1].equals(name))
            {
                check = 1;
            }
            i1++;
        }while(check != 1);
        i1--;

        // adding keyword parameters to the aptab
        int kpdtp1 = Integer.parseInt(kpdtp[i1]);
//        kpdtp1--;

        int count = 0, i3 = pp;
        do {
            aptab[i3] = kpdtab(kpdtp1);
            i3++;
            count++;
            kpdtp1++;
        }while(count < al-pp);
//        pp--;

        //adding positional parameters
//        CLEARMEM 30,&REG=BREG
//        CLEARMEM 25
//        INCR X,Y,&REG=BREG

        //storing the actual parameters
        k++;
        String p1 = "", p2 = "", kp = "";
        do {
            p1 = p1 + line1.charAt(k);
            k++;

            if(k == line1.length())
                break;

        }while(line1.charAt(k) != ',');
        aptab[0] = p1;

        if(k != line1.length())
        {
            k++;
            if(line1.charAt(k) == '&')
            {
                do {
                    k++;
                }while(line1.charAt(k) != '=');
                k++;

                do {
                    kp = kp + line1.charAt(k);
                    k++;
                }while(k != line1.length());
                aptab[pp] = kp;
            }
            else
            {
                int end = 0;
                do {
                    p2 = p2 + line1.charAt(k);
                    k++;
                    if(k == line1.length())
                    {
                        end = 1;
                        break;
                    }
                }while(line1.charAt(k) != ',');
                aptab[1] = p2;

                if(end != 1)
                {
                    do {
                        k++;
                    }while(line1.charAt(k) != '=');
                    k++;
                    do {
                        kp = kp + line1.charAt(k);
                        k++;
                    }while(k != line1.length());
                    aptab[pp] = kp;
                }
            }
        }

        try
        {
            FileWriter fw1 = new FileWriter("/Users/tanishakodam/Documents/Tanisha/DSA/Output_4_2", true);
            fw1.write("APTAB:\n");
            for(int l1 = 0; l1 < aptab.length; l1++)
            {
                fw1.write(aptab[l1] + "\n");
//                fw1.close();
            }
            fw1.write("\n");
            fw1.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }

        ArrayList<String> output = new ArrayList<>();
        String mnem = "", line = "", o1 = "";
        int j = 0;

        do {
            line = code.get(i);
            do {
                mnem = mnem + line.charAt(j);
                j++;
            }while(line.charAt(j) != ' ');
            j = 0;

            if(valid_mnemonic(mnem))
            {
                //substituting the occurrences of pn and evns
//                output.add(code.get(i));

                String r1 = "";
                o1 = line;
                int index = o1.indexOf("(P,");
                if(index != -1)
                {
                    int m = 1;
                    for(m = 1; m <=5; m++)
                    {
                        r1 = r1 + o1.charAt(index);
                        index++;
                    }
                    String p = "";
                    p = p + r1.charAt(3);
                    int i2 = Integer.parseInt(p);
                    i2 = i2 - 1;
                    o1 = o1.replace(r1,aptab[i2]);
//                    output.add(o1);

                    m = 1;
                    r1 = "";
                    p = "";
                    i2 = 0;
                    index = o1.indexOf("(E,");
                    if(index != -1)
                    {
                        for(m = 1; m <= 5; m++)
                        {
                            r1 = r1 + o1.charAt(index);
                            index++;
                        }
                        p = p + r1.charAt(3);
                        i2 = Integer.parseInt(p);
//                        i2 = i2 - 1;

                        if(i1 == 0)
                        {
                            o1 = o1.replace(r1,evn1(i2));
                        }
                        else
                        {
                            o1 = o1.replace(r1,evn2(i2));
                        }
                    }

                    m = 1;
                    r1 = "";
                    p = "";
                    i2 = 0;

                    index = o1.indexOf("(P,");
                    if(index != -1)
                    {
                        m = 1;
                        for (m = 1; m <= 5; m++) {
                            r1 = r1 + o1.charAt(index);
                            index++;
                        }
                        p = "";
                        p = p + r1.charAt(3);
                        i2 = Integer.parseInt(p);
                        i2 = i2 - 1;
                        o1 = o1.replace(r1, aptab[i2]);
                    }

                    output.add(o1);

                }
                else
                {
                    output.add(code.get(i));
                }
            }

            mnem = "";
            i++;
        }while(!code.get(i).equals("MEND"));

        return output;
    }

    static void output(String code)
    {
        String mnemonic = "";
        int i = 0;

        do {
            mnemonic = mnemonic + code.charAt(i);
            i++;

            if(mnemonic.equals("START") || mnemonic.equals("END"))
                break;

        }while(code.charAt(i) != ' ' && i != code.length());

        boolean valid_mnem = valid_mnemonic(mnemonic);

        try
        {
            FileWriter fw = new FileWriter("/Users/tanishakodam/Documents/Tanisha/DSA/Output_4_1", true);
            if(valid_mnem)
            {
                fw.write(code + "\n");
            }
            else
            {
//                fw.write("macro expansion\n");
                int[] valid = mnt(mnemonic);
                if(valid[0] == 1)
                {
                    int[] al1;
                    al1 = AL(mnemonic);

                    ArrayList<String> output = new ArrayList<>();
                    output = mdt(valid[1], code, al1[0], al1[1]);
                    int j = 0;
                    do {
                        fw.write(output.get(j) + "\n");
                        j++;
                    }while(j < output.size());
                }
            }
            fw.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }
}
