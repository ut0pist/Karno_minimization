import java.util.Scanner;

public class TruthTable {
    private int NumberOfInputVars;
    private int NumberOfOutputVars;
    private String[][] TT;

    public TruthTable(int NumberOfInputVars, int NumberOfOutputVars) {
        this.NumberOfInputVars = NumberOfInputVars;
        this.NumberOfOutputVars = NumberOfOutputVars;
        TT = ConvertTTtoChar(CreateTT(this.NumberOfInputVars, this.NumberOfOutputVars));
    }

    public String[][] getTT() {
        return TT;
    }


    public void DisplayTT() {
        System.out.println("Truth table: ");
        for (int i = 0; i < (int) Math.pow(2, NumberOfInputVars); i++) {
            for (int j = 0; j < this.NumberOfOutputVars + 1; j++) {
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
                    FuncRes.close();
                }
            }
        }
        return tt;
    }

    private String[][] ConvertTTtoChar(int[][] TruthTable) {
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
