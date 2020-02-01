public class Main {

    public static void main(String[] args) {
        int amount = 200;
        System.out.println(getChange(amount, 0, 7) + 1);
    }

    // returns number of ways the amount can be made except for whole amount in 1p only
    public static int getChange(int pennies, int count, int coin) {
        int[] coins = {1,2,5,10,20,50,100,200};

        for (int i = coin; i > 0; i--) {
            if (coins[i] <= pennies) {
                count++;
                count = getChange(pennies - coins[i], count, i);
            }
        }
        return count;
    }
}