import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("p054_poker.txt"));
        String line = "";
        int playerOneWins = 0;

        while ((line = reader.readLine()) != null) {
            String[] pokerHands = line.split(" ");

            int i = 0;
            Hand one = new Hand();
            Hand two = new Hand();

            // parse hands
            for (String deal : pokerHands) {
                if (i < 5) one.addCard(new Card(deal));
                else two.addCard(new Card(deal));
                i++;
            }

            // compare hands: first hand's value, then cards one by one, if necessary
            if (one.getValue() > two.getValue())
                playerOneWins++;

            if (one.getValue() == two.getValue() && one.compareCards(two))
                playerOneWins++;
        }
        System.out.println("Player 1 won " + playerOneWins + " times.");
    }
}