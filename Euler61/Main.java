public class Main {

    public static void main(String[] args) {
        PolygonalNumbers test = new PolygonalNumbers();

        test.populateMap(10000);
        test.makeChains();
        Chain cyclicalFigurativeNumbers = test.findCyclicalChain();
        for (Link num: cyclicalFigurativeNumbers.getChain()) System.out.println(num.toString());;

    }
}