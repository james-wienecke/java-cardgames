package blackjack;

import cards.Card;

import java.util.LinkedList;
import java.util.Locale;

public abstract class BlackjackPlayer {
    private final Blackjack game;
    private final LinkedList<Card> cards;
    private int score;
    private boolean retired;
    private state;

    private String name;

    BlackjackPlayer(Blackjack game, String name) {
        this.game = game;
        this.name = name.toLowerCase(Locale.ROOT);
        this.score = 0;
        this.retired = false;
        this.cards = new LinkedList<>();
    }

    public LinkedList<Card> getCards() {
        return cards;
    }

    public void drawCard() {
        Card draw = this.game.drawCard();
        this.cards.add(draw);
        draw.flipCard();
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

//    public int sumOfCards(boolean faceUpOnly) {
//        int sum = 0;
//        for (Card card : this.cards) {
//            if (faceUpOnly) {
//                if (card.isFaceUp()) {
//                    sum = card.getValue(sum);
//                }
//            } else {
//                sum = card.getValue(sum);
//            }
//        }
//        return sum;
//    }

    public int calcScore(boolean faceUpOnly) {
        for (Card card : this.cards) {
            if (faceUpOnly) {
                this.score = (card.isFaceUp()) ? card.getValue(this.score) : this.score;
//                if (card.isFaceUp()) {
//                    this.score = card.getValue(this.score);
//                }
            } else {
                this.score = card.getValue(this.score);
            }
        }
        return this.score;
    }

    public int getScore() {
        return score;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }
}
