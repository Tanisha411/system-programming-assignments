import java.util.Scanner;

//hardcoded mot and ST, lc processing, machine code
public class SP3 {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int code_area_address = 0;
        System.out.print("Enter the code area address: ");
        code_area_address = input.nextInt();
        input.nextLine();

        //printing the symbol table
        System.out.println("\nSymbol Table: ");
        System.out.println("Index | Name | Address");
        int[] index = {1, 2, 3, 4};
        String[] name = {"w", "x", "y", "z"};
        int[] address = {520, 523, 528, 540};
        for(int i = 0; i < name.length; i++)
        {
            System.out.println(index[i] + " | " + name[i] + " | " + address[i]);
        }

        String code = "";
        String[] check;
        int lc = 0, lc_change = 0;
        System.out.println("\nEnter the intermediate code: ");

        do {
            code = input.nextLine();
            check = mcode(code); //mcode, new_size
            lc_change = Integer.parseInt(check[2]);

            System.out.print(code_area_address + " ");
            System.out.print(lc + " ");

            if(lc_change != 1)
            {
                code_area_address = code_area_address + Integer.parseInt(check[1]);
            }

            lc = lc + Integer.parseInt(check[1]);

            if(!check[0].equals(""))
            {
                System.out.println(check[0] + "\n");
            }
            else
            {
                System.out.print("\n\n");
            }

        }while(!code.equals("AD 02") && !code.equals("ad 02"));
    }

    static String[] mcode(String code)
    {
        String mcode = "", s_operand = "";
        String mnemonic = "", register = "", symbol = "", new_size = "", constant = "";
        String dc = "DC";
        String[] s1 = new String[3];
        int i = 0, s_address = 0, lc_change = 0;

        for(i = 0; i < 2; i++)
        {
            mnemonic = mnemonic + code.charAt(i);
        }
        mnemonic = mnemonic.toUpperCase();

        switch(mnemonic)
        {
            case "AD":
                mcode = "";
                i = i + 3;
                new_size = "0";
                if(i != code.length())
                {
                    lc_change = 1;
                    i = 8;
                    do {
                        new_size = new_size + code.charAt(i);
                        i++;
                    }while(i < code.length());
                }
                break;

            case "DL":
                String index = "";
                i = i + 2;
                index = index + code.charAt(i);

                i = 8;
                do {
                    constant = constant + code.charAt(i);
                    i++;
                }while(i < code.length());

                if(index.equals("2"))
                {
                    new_size = new_size + constant;
                }
                else
                {
                    new_size = "1";
                    mcode = mcode + constant;
                }
                break;

            case "IS":
                new_size = "2";
                i++;
                mcode = mcode + code.charAt(i);
                i++;
                mcode = mcode + code.charAt(i);
                i = i + 2;
                do {
                    if(code.charAt(i) == 'R' || code.charAt(i) == 'r')
                    {
                        i = i + 2;
                        mcode = mcode + " " + code.charAt(i);
                    }
                    if(code.charAt(i) == 'S' || code.charAt(i) == 's')
                    {
                        i = i + 2;
                        s_operand = String.valueOf(code.charAt(i));
                        s_address = Symbol_Table(Integer.parseInt(s_operand));
                        mcode = mcode + " " + s_address;
                    }
                    i++;
                }while(i < code.length());
                break;
        }

        s1[0] = mcode;
        s1[1] = new_size;
        s1[2] = String.valueOf(lc_change);

        return s1;
    }

    static int Symbol_Table(int index1)
    {
        String[] name = {"w", "x", "y", "z"};
        int[] index = {1, 2, 3, 4};
        int[] address = {520, 523, 528, 540};
        int end = 0, i = 0, address1 = 0;

        address1 = address[index1-1];

        return address1;
    }

}


