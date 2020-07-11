import java.util.LinkedList;
import java.util.Scanner;

public class TruthTable {
    private int NumberOfInputVars;
    private int NumberOfOutputVars;
    private String[][] TT;
    //private String[][] CarnoMap;
    private String[][][] AllCarnoMaps;

    public TruthTable(int NumberOfInputVars, int NumberOfOutputVars) {
        this.NumberOfInputVars = NumberOfInputVars;
        this.NumberOfOutputVars = NumberOfOutputVars;
        TT = ConvertTTtoChar(CreateTT(this.NumberOfInputVars, this.NumberOfOutputVars));
        CreateCarnoMap();
    }

    public String[][] getTT() {
        return TT;
    }

    public String[][][] getCarnoMap() {
        return AllCarnoMaps;
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

    /*public void DisplayCarnoMap() {
        System.out.println("Carno Map: ");
        for (int i = 0; i < CarnoMap.length; i++) {
            for (int j = 0; j < CarnoMap[0].length; j++) {
                System.out.print(" " + this.CarnoMap[i][j] + " ");
            }
            System.out.println();
        }
    }*/

    public void DisplayCarnoMaps(){
        for(int i = 0; i < AllCarnoMaps.length; i++){
            System.out.println("Carno Map for func " + i);
            for(int j = 0; j < AllCarnoMaps[0].length; j++){
                for(int k = 0; k < AllCarnoMaps[0][0].length; k++){
                    System.out.print(" " + AllCarnoMaps[i][j][k] + " ");
                }
                System.out.println();
            }
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

    private void CreateCarnoMap() {
        int NumberOfVarsInRows = NumberOfInputVars / 2;
        int NumberOfVarsInColumns = NumberOfInputVars - NumberOfVarsInRows;
        int RowNumber = (int) Math.pow(2, NumberOfVarsInRows) + 2;
        int ColumnNumber = (int) Math.pow(2, NumberOfVarsInColumns) + 2;
        //TODO Карт Карно может быть много. Нужно добавить структуру, которая будет хранить множество карт Карно.
        /** Наполняем карты Карно значениями выходных функций **/
        AllCarnoMaps = new String[NumberOfOutputVars][RowNumber][ColumnNumber];
        for(int j = 0; j < AllCarnoMaps.length; j++){
            for(int i = NumberOfOutputVars - 1; i >= 0; i--){
                AllCarnoMaps[j] = InsertOutputValuesIntoCarnoMap(i);;
            }
        }
    }

    private String[][] CarnoMapTemplate(){
        int counter = 0;
        int VarIndex = NumberOfInputVars - 1;
        int NumberOfVarsInRows = NumberOfInputVars / 2;
        int NumberOfVarsInColumns = NumberOfInputVars - NumberOfVarsInRows;
        String[][] CarnoMap = new String[(int) Math.pow(2, NumberOfVarsInRows) + 2][(int) Math.pow(2, NumberOfVarsInColumns) + 2];
        CarnoMap[0][0] = "";
        CarnoMap[0][1] = "";
        CarnoMap[1][0] = "";

        for(int i = 1; i < CarnoMap.length; i++){
            for(int j = 1; j < CarnoMap[0].length; j++){
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

            while(CarnoMap[0][i].length() < NumberOfVarsInColumns){
                CarnoMap[0][i] = "0" + CarnoMap[0][i];
            }
        }


        /** Вводим в карту Карно коды Грея для иксов, расположенных в строках**/
        counter = 0;
        for (int i = 2; i < CarnoMap.length; i++) {
            CarnoMap[i][0] = GrayCodes(counter);
            CarnoMap[i][1] = "";
            counter++;

            while(CarnoMap[i][0].length() < NumberOfVarsInRows){
                CarnoMap[i][0] = "0" + CarnoMap[i][0];
            }
        }
        return CarnoMap;
    }

    private String GrayCodes(int CurrentValue) {
        String GrayCode = Integer.toBinaryString(CurrentValue ^ (CurrentValue >> 1));
        return GrayCode;
    }

    /*private String[] FindCoordinatesOfNeededOutputValues(int NeededValue, int FuncIndex){
        int counter = 0; //количество строк в таблице координат нужных значений
        LinkedList<Integer> RowIndex = new LinkedList<>(); //номера строк, в которых были найдены нужные значения для последующей минимизации (0 или 1)

        //TODO неправильно указывается номер столбца, с которого должна быть считана выходящая функция
        for(int i = 0; i < TT.length; i++){
            String temp = Integer.toString(NeededValue);
            if(TT[i][TT[0].length - (FuncIndex + 1)].equals(Integer.toString(NeededValue))){
                RowIndex.add(counter);
            }
            counter++;
        }

        //TODO Подумать, можно ли не переносить найденные координаты в другой массив.
        String[] FoundCoordinates = new String[RowIndex.size()];
        for(int i = 0; i < FoundCoordinates.length; i++){
                FoundCoordinates[i] = TT[RowIndex.getFirst()][0];
                RowIndex.removeFirst();
        }

        return FoundCoordinates;
    }*/

    private String[][] InsertOutputValuesIntoCarnoMap(int FuncIndex){
        int VarsInColumns = NumberOfInputVars - (NumberOfInputVars / 2);
        int VarsInRows = NumberOfInputVars / 2;
        String[][] CurrentCarnoMap = CarnoMapTemplate();
        LinkedList<Integer> ColumnCoordinate = new LinkedList<>();
        LinkedList<Integer> RowCoordinate = new LinkedList<>();

        for(int i = 0; i < TT.length; i++){
            for(int j = 2; j < CurrentCarnoMap.length; j++){
                for(int k = 2; k < CurrentCarnoMap[0].length; k++){
                    if(TT[i][0].substring(0, VarsInColumns).equals(CurrentCarnoMap[0][k]) && TT[i][0].substring(VarsInColumns).equals(CurrentCarnoMap[j][0]))
                        CurrentCarnoMap[j][k] = TT[i][TT[0].length - (FuncIndex + 1)];
                }
            }
        }
        return CurrentCarnoMap;
    }
}
