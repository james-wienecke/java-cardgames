package blackjack;

import cards.Card;

import java.util.LinkedList;
import java.util.Locale;

public abstract class BlackjackPlayer {
    protected final Blackjack game;
    //protected final LinkedList<Card> cards;
    protected LinkedList<Hand> hands;
//    protected int score;
    protected boolean retired;
//    protected State state;

    private String name;

    BlackjackPlayer(Blackjack game, String name) {
        this.game = game;
        this.name = name.toLowerCase(Locale.ROOT);
//        this.score = 0;
        this.retired = false;
        //this.cards = new LinkedList<>();
        this.hands = new LinkedList<>();
        this.hands.add(new Hand());
        this.hands.forEach(hand ->  hand.setState(State.SETUP));
    }

//    public abstract void takeTurn();

//    public abstract void dealStart();

//    public LinkedList<Card> getCards() {
//        return cards;
//    }

//    public void drawCard() {
//        Card draw = this.game.drawCard();
//        this.cards.add(draw);
//        draw.flipCard();
//    }
//    public void drawCard(boolean faceUp) {
//        Card draw = this.game.drawCard();
//        this.cards.add(draw);
//        if (faceUp) {
//            draw.flipCard();
//        }
//    }

    public Card drawCard(boolean faceUp) {
        return game.drawCard(faceUp);
    }

    public void addCardToHand(Card card) {
        hands.get(0).addCard(card);
    }

    public void updateScore() {
        for (Hand hand: hands) {
            hand.updateScore();
        }
    }

    public LinkedList<Hand> getHands() {
        return hands;
    }


    public Card drawCard() {
        return this.game.drawCard();
    }

    public int[] getHandsScores(boolean hiddenScore) {
        int[] scores = new int[hands.size()];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = hands.get(i).getScore(hiddenScore);
        }
        return scores;
    }

//    public void flipLastCard() {
//        this.cards.getLast().flipCard();
//    }

//    public int calcScore(boolean faceUpOnly) {
//        for (Card card : this.cards) {
//            if (!card.isAlreadyScored()) {
//                if (faceUpOnly) {
//                    this.score = (card.isFaceUp()) ? card.getValue(this.score) : this.score;
//                } else {
//                    this.score = card.getValue(this.score);
//                }
//            }
//        }
//        if (this.score > 21) {
//            this.state = State.BUST;
//            this.retired = true;
//        } else if (this.score == 21) {
//            this.state = State.BLACKJACK;
//            this.retired = true;
//        }
//        return this.score;
//    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public String getName() {
        return name;
    }

    public void printCardStatus() {
        for (Hand hand : this.hands) {
            hand.printCardStatus();
        }
    }

    protected abstract void makeDecision();

    class Hand {
        LinkedList<Card> cards;
        State state;
        boolean inPlay;
        int score;
        int hiddenScore;
        int index;

        Hand() {
            this.cards = new LinkedList<>();
            this.state = State.SETUP;
            this.score = 0;
            hiddenScore = 0;
            index = BlackjackPlayer.this.hands.size();
        }

        public LinkedList<Card> getCards() {
            return cards;
        }

        public void addCard(Card card) {
            this.cards.add(card);
        }

        private void flipLastCard() {
            cards.getLast().flipCard();
        }

        protected void printCardStatus() {
            System.out.println(BlackjackPlayer.this.getName() + " hand #" + index);
            getCards().forEach(System.out::println);
        }

        void updateScore() {
            cards.forEach(card -> {
                if (!card.isAlreadyScored()) {
                    score = (card.isFaceUp()) ? card.getValue(score) : score;
                    hiddenScore = card.getValue(score);
                }
            });

            printCardStatus();
        }

        public void setState(State state) {
            this.state = state;
        }

        public State getState() {
            return state;
        }

        public boolean isInPlay() {
            return inPlay;
        }

        public void setInPlay(boolean inPlay) {
            this.inPlay = inPlay;
        }

        public int getScore(boolean hidden) {
            return hidden ? hiddenScore : score;
        }
    }
}
