public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader scanner = new BufferedReader(new FileReader("p067_triangle.txt"));
        String line = "";
        String[] prevLine = new String[0];

        while ((line = scanner.readLine()) != null) {
            String[] lineArray = line.split(" ");

            int l = prevLine.length;
            String prev = "0";

            for (int i = 0; i < l; i++) {
                lineArray[i] = String.valueOf(Integer.parseInt(lineArray[i]) + Math.max(Integer.parseInt(prev), Integer.parseInt(prevLine[i])));
                prev = prevLine[i];
            }
            prevLine = lineArray;
        }
        int max = 0;
        for (String num: prevLine) {
            int n = Integer.parseInt(num);
            max = Math.max(max, n);
        }
        System.out.println(max);
    }
}