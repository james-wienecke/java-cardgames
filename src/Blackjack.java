public class Blackjack {

    public static void main(String[] args) {
        Deck deck = new Deck(1);
        for (Card card : deck.getCards()) {
            System.out.println(String.format("%c%c", card.getSuitChar(), card.getValueAsChar()));
        }
    }
}
