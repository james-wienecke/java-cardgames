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
        System.out.println(this.getName() + " " + this.getState() + "s");
        switch (this.state) {
            case HIT:
                this.drawCard();
                break;
            case STAND:
                break;
        }
        this.printCardStatus();
        System.out.println(this.getName() + " score: " + this.calcScore(true));
        if (this.state == State.BLACKJACK || this.state == State.BUST) {
            System.out.println(this.getName() + " " + this.state);
        }
    }


    protected void aiScoreLogic() {
        if (this.score >= 17) {
            this.state = State.STAND;
            this.retired = true;
        } else {
            this.state = State.HIT;
        }
    }

    public int calcScore(boolean faceUpOnly) {
        for (Card card : this.cards) {
            if (!card.isAlreadyScored()) {
                if (faceUpOnly) {
                    this.score = (card.isFaceUp()) ? card.getValue(this.score) : this.score;
                } else {
                    this.score = card.getValue(this.score);
                }
            }
        }
        if (this.score > 21) {
            this.state = State.BUST;
            this.retired = true;
        } else if (this.score == 21) {
            this.state = State.BLACKJACK;
            this.retired = true;
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

    public String getName() {
        return this.name;
    }

    public void printCardStatus() {
        System.out.println(this.getName());
        this.getCards().forEach(System.out::println);
    }
}
