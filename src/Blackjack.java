import util.Input;

import java.util.ArrayList;
import java.util.LinkedList;

public class Blackjack {
    private boolean gameWon;
    private Deck deck;
    private LinkedList<Card> dealerCards;
    private LinkedList<Card> playerCards;

    public Blackjack() {
        this.gameWon = false;
        this.deck = new Deck();
        this.deck.shuffle();
        this.dealerCards = new LinkedList<>();
        this.playerCards = new LinkedList<>();
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public LinkedList<Card> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(LinkedList<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }

    public LinkedList<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(LinkedList<Card> playerCards) {
        this.playerCards = playerCards;
    }

    private static void playerAction() {

    }


    public int sumOfCards(LinkedList<Card> cards) {
        int sum = 0;
        for (Card card : cards) {
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

    public void win(int dealerScore, int playerScore) {
        this.gameWon = true;
        if (dealerScore == 21 || playerScore == 21) {
            System.out.println("BLACKJACK");
        }
        if (dealerScore > playerScore) {
            // dealer win
            System.out.println("HOUSE WINS!!!");
        } else {
            System.out.println("PLAYER WINS!!!");
        }
    }

    public void lose(int dealerScore, int playerScore) {
        this.gameWon = false;
        System.out.println("BUST");
        if (dealerScore > playerScore && dealerScore > 21) {
            System.out.println("PLAYER WINS!!!");
        } else if (playerScore > dealerScore && playerScore > 21) {
            System.out.println("HOSE WINS!!!");
        }
    }

}
