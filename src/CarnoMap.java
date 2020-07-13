import java.util.LinkedList;

public class CarnoMap {
    private TruthTable TruthTable = new TruthTable();
    private String[][] TT = TruthTable.getTT();
    private int NumberOfInputVars = TruthTable.getNumberOfInputVars();
    private int NumberOfOutputVars = TruthTable.getNumberOfOutputVars();
    private String[][][] AllCarnoMaps;

    public CarnoMap() {
        CreateCarnoMap();
    }

    public void DisplayTruthTable() {
        TruthTable.DisplayTT();
    }

    public void DisplayCarnoMaps() {
        //TODO неравильно выводятся индексы функций
        for (int i = 0; i < AllCarnoMaps.length; i++) {
            System.out.println("Carno Map for func " + i);
            for (int j = 0; j < AllCarnoMaps[0].length; j++) {
                for (int k = 0; k < AllCarnoMaps[0][0].length; k++) {
                    System.out.print(" " + AllCarnoMaps[i][j][k] + " ");
                }
                System.out.println();
            }
        }
    }

    private void CreateCarnoMap() {
        int NumberOfVarsInRows = NumberOfInputVars / 2;
        int NumberOfVarsInColumns = NumberOfInputVars - NumberOfVarsInRows;
        int RowNumber = (int) Math.pow(2, NumberOfVarsInRows) + 2;
        int ColumnNumber = (int) Math.pow(2, NumberOfVarsInColumns) + 2;


        /** Наполняем карты Карно значениями выходных функций **/
        AllCarnoMaps = new String[NumberOfOutputVars][RowNumber][ColumnNumber];
        int CarnoMapIndex = 0;

        for (int i = NumberOfOutputVars - 1; i >= 0; i--) {
            AllCarnoMaps[CarnoMapIndex] = InsertOutputValuesIntoCarnoMap(i);
            CarnoMapIndex++;
        }
    }

    private String[][] CarnoMapTemplate() {
        int counter = 0;
        int VarIndex = NumberOfInputVars - 1;
        int NumberOfVarsInRows = NumberOfInputVars / 2;
        int NumberOfVarsInColumns = NumberOfInputVars - NumberOfVarsInRows;
        String[][] CarnoMap = new String[(int) Math.pow(2, NumberOfVarsInRows) + 2][(int) Math.pow(2, NumberOfVarsInColumns) + 2];
        CarnoMap[0][0] = "";
        CarnoMap[0][1] = "";
        CarnoMap[1][0] = "";

        for (int i = 1; i < CarnoMap.length; i++) {
            for (int j = 1; j < CarnoMap[0].length; j++) {
                CarnoMap[i][j] = "";
            }
        }

        /** Вводим в карту Карно иксы, расположенные в столбце **/
        for (int i = 0; i < NumberOfVarsInColumns; i++) {
            CarnoMap[0][1] += "x" + VarIndex + " ";
            VarIndex--;
        }

        /** Вводим в карту Карно иксы, расположенные в строке **/
        for (int i = 0; i < NumberOfVarsInRows; i++) {
            CarnoMap[1][0] += "x" + VarIndex + " ";
            VarIndex--;
        }

        /** Вводим в карту Карно коды Грея для иксов, расположенных в столбцах **/
        for (int i = 2; i < CarnoMap[0].length; i++) {
            CarnoMap[0][i] = GrayCodes(counter);
            CarnoMap[1][i] = "";
            counter++;

            while (CarnoMap[0][i].length() < NumberOfVarsInColumns) {
                CarnoMap[0][i] = "0" + CarnoMap[0][i];
            }
        }


        /** Вводим в карту Карно коды Грея для иксов, расположенных в строках**/
        counter = 0;
        for (int i = 2; i < CarnoMap.length; i++) {
            CarnoMap[i][0] = GrayCodes(counter);
            CarnoMap[i][1] = "";
            counter++;

            while (CarnoMap[i][0].length() < NumberOfVarsInRows) {
                CarnoMap[i][0] = "0" + CarnoMap[i][0];
            }
        }
        return CarnoMap;
    }

    private String GrayCodes(int CurrentValue) {
        String GrayCode = Integer.toBinaryString(CurrentValue ^ (CurrentValue >> 1));
        return GrayCode;
    }

    private String[][] InsertOutputValuesIntoCarnoMap(int FuncIndex) {
        int VarsInColumns = NumberOfInputVars - (NumberOfInputVars / 2);
        //int VarsInRows = NumberOfInputVars / 2;
        String[][] CurrentCarnoMap = CarnoMapTemplate();
        LinkedList<Integer> ColumnCoordinate = new LinkedList<>();
        LinkedList<Integer> RowCoordinate = new LinkedList<>();

        for (int i = 0; i < TT.length; i++) {
            for (int j = 2; j < CurrentCarnoMap.length; j++) {
                for (int k = 2; k < CurrentCarnoMap[0].length; k++) {
                    if (TT[i][0].substring(0, VarsInColumns).equals(CurrentCarnoMap[0][k]) && TT[i][0].substring(VarsInColumns).equals(CurrentCarnoMap[j][0]))
                        CurrentCarnoMap[j][k] = TT[i][TT[0].length - (FuncIndex + 1)];
                }
            }
        }
        return CurrentCarnoMap;
    }
}
