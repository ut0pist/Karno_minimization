import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        int NumberOfInputVars = 0;
        int NumberOfOutputVars = 0;
        int TempValue = 0;


        System.out.println("Enter a number of input variables from 1 to 5: ");
        while (true) {
            Scanner InputVarsScanner = new Scanner(System.in);
            if (InputVarsScanner.hasNextInt()) {
                TempValue = InputVarsScanner.nextInt();
                if (TempValue >= 1 && TempValue <= 5) {
                    NumberOfInputVars = TempValue;
                    break;
                }
            }
            System.out.println("Wrong number of input variables was insert \n" + "Try again");
        }

        System.out.println("Enter a number of output variables");
        while (true) {
            Scanner OutputVarsScanner = new Scanner(System.in);
            if (OutputVarsScanner.hasNextInt()) {
                NumberOfOutputVars = OutputVarsScanner.nextInt();
                break;
            } else {
                System.out.println("Wrong number of output variables was insert \n" + "Try again");
            }
        }

        TruthTable TT = new TruthTable(NumberOfInputVars, NumberOfOutputVars);
        //System.out.println(Arrays.deepToString(TT.getTT()));
        TT.DisplayTT();
    }
}