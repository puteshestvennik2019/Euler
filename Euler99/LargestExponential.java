import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// log(x^y) = y*log(x)

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader("p099_base_exp.txt"));
        String line = bf.readLine();
        double max = 0;
        int i = 0;
        int lineWithMax = 0;

        while (line != null && line.length() > 0) {
            ++i;
            String[] arr = line.split(",");
            double logVal = Integer.parseInt(arr[1]) * Math.log(Integer.parseInt(arr[0]));
            if (logVal > max) {
                lineWithMax = i;
                max = logVal;
            }
            line = bf.readLine();
        }
        System.out.println(lineWithMax);
    }
}