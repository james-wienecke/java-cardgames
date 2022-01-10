import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Deck {
    private LinkedList<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>();
        fillDeck();
    }

    public Deck(int shoeSize) {
        this.cards = new LinkedList<>();
        for (int i = 0; i < shoeSize; i++) {
            fillDeck();
        }
    }

    public Deck(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public void fillDeck() {
        for (byte suit = 0; suit < 3; suit++) {
            for (byte val = 1; val < 14; val++) {
                this.cards.add(new Card(suit, val));
            }
        }
    }

    public LinkedList<Card> getCards() {
        return this.cards;
    }

    public LinkedList<Card> shuffle() {
        Card[] buffer = this.cards.toArray(Card[]::new);
        Random r = new Random();
        for (int i = 0; i < buffer.length - 1; i++) {
            int index = r.nextInt(i + 1);
            Card c = buffer[index];
            buffer[index] = buffer[i];
            buffer[i] = c;
        }
        LinkedList<Card> shuffled = new LinkedList<>();
        Collections.addAll(shuffled, buffer);

        this.cards = shuffled;
        return shuffled;
    }
}