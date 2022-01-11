import java.util.LinkedList;
import java.util.Locale;

public abstract class BlackjackPlayer {
    private final Blackjack game;
    private final LinkedList<Card> cards;
    private int score;

    private String name;

    BlackjackPlayer(Blackjack game, String name) {
        this.game = game;
        this.name = name.toLowerCase(Locale.ROOT);
        this.score = 0;
        this.cards = new LinkedList<>();
    }

    public void drawCard(boolean faceUp) {
        Card draw = this.game.drawCard();
        this.cards.add(draw);
        if (faceUp) {
            draw.flipCard();
        }
    }

    public void flipLastCard() {
        this.cards.getLast().flipCard();
    }

    public int sumOfCards() {
        int sum = 0;
        for (Card card : this.cards) {
            switch (card.getValue()) {
                case 11:
                case 12:
                case 13:
                    sum += 10;
                    break;
                case 1:
                    if (sum + 11 > 21) {
                        sum += 1;
                    } else {
                        sum += 11;
                    }
                    break;
                default:
                    sum += card.getValue();
            }
        }
        return sum;
    }
}
