import java.util.Scanner;

public class TruthTable {
    private int NumberOfInputVars;
    private int NumberOfOutputVars;
    private String[][] TT;
    private String[][] CarnoCard;

    public TruthTable(int NumberOfInputVars, int NumberOfOutputVars) {
        this.NumberOfInputVars = NumberOfInputVars;
        this.NumberOfOutputVars = NumberOfOutputVars;
        TT = ConvertTTtoChar(CreateTT(this.NumberOfInputVars, this.NumberOfOutputVars));
        CreateCarnoCard();
    }

    public String[][] getTT() {
        return TT;
    }

    public String[][] getCarnoCard() {
        return CarnoCard;
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

    public void DisplayCarnoCard() {
        System.out.println("Carno Card: ");
        for (int i = 0; i < CarnoCard.length; i++) {
            for (int j = 0; j < CarnoCard[0].length; j++) {
                System.out.print(" " + this.CarnoCard[i][j] + " ");
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

    private void CreateCarnoCard() {
        int counter = 0;
        int VarIndex = NumberOfInputVars - 1;
        int NumberOfVarsInRows = NumberOfInputVars / 2;
        int NumberOfVarsInColumns = NumberOfInputVars - NumberOfVarsInRows;
        CarnoCard = new String[(int) Math.pow(2, NumberOfVarsInRows) + 2][(int) Math.pow(2, NumberOfVarsInColumns) + 2];
        CarnoCard[0][0] = "";
        CarnoCard[0][1] = "";
        CarnoCard[1][0] = "";

        for(int i = 1; i < CarnoCard.length; i++){
            for(int j = 1; j < CarnoCard[0].length; j++){
                CarnoCard[i][j] = "";
            }
        }

        /** Вводим в карту Карно иксы, расположенные в столбце **/
        for (int i = 0; i < NumberOfVarsInColumns; i++) {
            CarnoCard[0][1] += "x" + VarIndex + " ";
            VarIndex--;
        }

        /** Вводим в карту Карно иксы, расположенные в строке **/
        for (int i = 0; i < NumberOfVarsInRows; i++) {
            CarnoCard[1][0] += "x" + VarIndex + " ";
            VarIndex--;
        }

        /** Вводим в карту Карно коды Грея для иксов, расположенных в столбцах **/
        for (int i = 2; i < CarnoCard[0].length; i++) {
            CarnoCard[0][i] = GrayCodes(counter);
            CarnoCard[1][i] = "";
            counter++;

            while(CarnoCard[0][i].length() < NumberOfVarsInColumns){
                CarnoCard[0][i] = "0" + CarnoCard[0][i];
            }
        }


        /** Вводим в карту Карно коды Грея для иксов, расположенных в строках**/
        counter = 0;
        for (int i = 2; i < CarnoCard.length; i++) {
            CarnoCard[i][0] = GrayCodes(counter);
            CarnoCard[i][1] = "";
            counter++;

            while(CarnoCard[i][0].length() < NumberOfVarsInRows){
                CarnoCard[i][0] = "0" + CarnoCard[i][0];
            }
        }
    }

    private String GrayCodes(int CurrentValue) {
        String GrayCode = Integer.toBinaryString(CurrentValue ^ (CurrentValue >> 1));
        return GrayCode;
    }
}
