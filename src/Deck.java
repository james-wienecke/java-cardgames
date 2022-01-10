import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        fillDeck();
    }

    public Deck(int shoeSize) {
        this.cards = new ArrayList<>();
        for (int i = 0; i < shoeSize; i++) {
            fillDeck();
        }
    }

    public Deck(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void fillDeck() {
        for (byte suit = 0; suit < 3; suit++) {
            for (byte val = 1; val < 14; val++) {
                this.cards.add(new Card(suit, val));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

    public ArrayList<Card> shuffle() {
        Card[] buffer = this.cards.toArray(Card[]::new);
        Random r = new Random();
        for (int i = 0; i < buffer.length - 1; i++) {
            int index = r.nextInt(i + 1);
            Card c = buffer[index];
            buffer[index] = buffer[i];
            buffer[i] = c;
        }
        ArrayList<Card> shuffled = new ArrayList<>();
        Collections.addAll(shuffled, buffer);

        this.cards = shuffled;
        return shuffled;
    }
}