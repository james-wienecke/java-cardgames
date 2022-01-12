package blackjack;

import cards.Card;

import java.util.LinkedList;
import java.util.Locale;

public abstract class BlackjackPlayer {
    protected final Blackjack game;
    protected final LinkedList<Card> cards;
    protected int score;
    protected boolean retired;
    protected State state;

    private String name;

    BlackjackPlayer(Blackjack game, String name) {
        this.game = game;
        this.name = name.toLowerCase(Locale.ROOT);
        this.score = 0;
        this.retired = false;
        this.cards = new LinkedList<>();
        this.state = State.SETUP;
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

    public void takeTurn() {
        aiScoreLogic();
        switch (this.state) {
            case HIT:
                this.drawCard();
                break;
            case STAND:
                break;
        }
        this.calcScore(true);
    }

    protected void aiScoreLogic() {
        if (this.calcScore(true) >= 17) {
            this.state = State.STAND;
            this.retired = true;
        } else {
            this.state = State.HIT;
        }
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
        System.out.println(this.name + " score: " + this.score);
        if (this.score > 21) {
            this.state = State.BUST;
            this.retired = true;
            System.out.println(this.name + " BUSTS!");
        } else if (this.score == 21) {
            this.state = State.BLACKJACK;
            this.retired = true;
            System.out.println(this.name + " BLACKJACK!");
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
