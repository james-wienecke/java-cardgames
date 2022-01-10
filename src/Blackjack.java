public class Blackjack {

    public static void main(String[] args) {
        Deck deck1 = new Deck();
        for (Card card : deck1.getCards()) {
            System.out.println(String.format("%c%c", card.getSuitChar(), card.getValueAsChar()));
        }

        System.out.println("Deck 2 -- 4 deck shoe");

        Deck deck2 = new Deck(4);
        for (Card card : deck2.getCards()) {
            System.out.println(String.format("%c%c", card.getSuitChar(), card.getValueAsChar()));
        }
    }
}
