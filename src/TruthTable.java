import java.util.LinkedList;
import java.util.Scanner;

public class TruthTable {
    private int NumberOfInputVars;
    private int NumberOfOutputVars;
    private String[][] TT;

    public TruthTable() {
        int TempValue = 0;

        System.out.println("Enter a number of input variables from 1 to 5: ");
        while (true) {
            Scanner InputVarsScanner = new Scanner(System.in);
            if (InputVarsScanner.hasNextInt()) {
                TempValue = InputVarsScanner.nextInt();
                if (TempValue >= 1 && TempValue <= 5) {
                    this.NumberOfInputVars = TempValue;
                    break;
                }
            }
            System.out.println("Wrong number of input variables was insert \n" + "Try again");
        }

        System.out.println("Enter a number of output variables");
        while (true) {
            Scanner OutputVarsScanner = new Scanner(System.in);
            if (OutputVarsScanner.hasNextInt()) {
                TempValue = OutputVarsScanner.nextInt();
                if (TempValue > 0) {
                    this.NumberOfOutputVars = TempValue;
                    break;
                }
            }
            System.out.println("Wrong number of output variables was insert \n" + "Try again");
        }

        TT = ConvertTTtoString(CreateTT(this.NumberOfInputVars, this.NumberOfOutputVars));
    }

    public String[][] getTT() {
        return TT;
    }

    public int getNumberOfInputVars() {
        return NumberOfInputVars;
    }

    public int getNumberOfOutputVars() {
        return NumberOfOutputVars;
    }


    public void DisplayTT() {
        System.out.println("Truth table: ");
        for (int i = 0; i < TT.length; i++) {
            for (int j = 0; j < TT[0].length; j++) {
                System.out.print(" " + this.TT[i][j] + " ");
            }
            System.out.println();
        }
    }


    private int[][] CreateTT(int NumberOfInputVars, int NumberOfOutputVars) {
        int NumberOfRows = (int) Math.pow(2, NumberOfInputVars);
        int[][] tt = new int[NumberOfRows][NumberOfOutputVars + 1];
        for (int i = 0; i < NumberOfRows; i++) {
            tt[i][0] = i;
        }


        //TODO добавить проверку того, что введенные значения 0 или 1, а не любые двоичные числа
        int CurrentFuncIndex = this.NumberOfOutputVars;
        for (int i = 1; i < this.NumberOfOutputVars + 1; i++) {
            System.out.println("Enter the function with index " + (--CurrentFuncIndex) + ":");
            for (int j = 0; j < (int) Math.pow(2, NumberOfInputVars); j++) {
                while (true) {
                    Scanner FuncRes = new Scanner(System.in);
                    if (FuncRes.hasNextInt(2)) {
                        tt[j][i] = FuncRes.nextInt(2);
                        break;
                    } else {
                        System.out.println("Wrong Y value was insert \n" + "Try again");
                    }
                    //FuncRes.close();
                }
            }
        }
        return tt;
    }

    private String[][] ConvertTTtoString(int[][] TruthTable) {
        String[][] ConvertedTT = new String[TruthTable.length][TruthTable[0].length];

        for (int i = 0; i < (int) Math.pow(2, NumberOfInputVars); i++) {
            ConvertedTT[i][0] = Integer.toBinaryString(TruthTable[i][0]);
            while (ConvertedTT[i][0].length() < this.NumberOfInputVars) {
                ConvertedTT[i][0] = "0" + ConvertedTT[i][0];
            }
        }

        for (int i = 1; i <= this.NumberOfOutputVars; i++) {
            for (int j = 0; j < (int) Math.pow(2, NumberOfInputVars); j++) {
                ConvertedTT[j][i] = Integer.toString(TruthTable[j][i]);
            }
        }

        return ConvertedTT;
    }
}
