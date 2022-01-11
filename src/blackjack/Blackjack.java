package blackjack;

import cards.Card;
import cards.Deck;

import java.util.ArrayList;

public class Blackjack {
    private boolean gameWon;
    private boolean gameOver;
    private Deck deck;
    private BlackjackPlayer dealer;
    private ArrayList<BlackjackPlayer> players;
//    private LinkedList<Card> dealerCards;
//    private LinkedList<Card> playerCards;

    public Blackjack() {
        this.gameWon = false;
        this.gameOver = false;
        this.deck = new Deck();
        this.deck.shuffle();
//        this.dealerCards = new LinkedList<>();
//        this.playerCards = new LinkedList<>();
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

//    public LinkedList<Card> getDealerCards() {
//        return dealerCards;
//    }

//    public void setDealerCards(LinkedList<Card> dealerCards) {
//        this.dealerCards = dealerCards;
//    }

//    public LinkedList<Card> getPlayerCards() {
//        return playerCards;
//    }

//    public void setPlayerCards(LinkedList<Card> playerCards) {
//        this.playerCards = playerCards;
//    }

    public Card drawCard() {
        return this.deck.draw();
    }

    public void play() {
        this.gameStart();
        this.gameLoop();
    }

    public void gameStart() {
        // draw 2 cards, one facedown
        this.dealer.drawCard();
        this.dealer.drawCard(false);

        drawCardsForPlayers(2);
    }

    public void gameLoop() {
        do {
            this.dealer.drawCard();
            this.drawCardsForPlayers();
            if (this.scoreThresholdReached()) {
                this.gameOver = true;
            }
            // if all players & dealer are standing, end game
        } while (!gameOver);

    }

    public boolean scoreThresholdReached(int scoreThreshold) {
        boolean reached = false;
        if (dealer.calcScore(true) >= 21) {
            reached = true;
            dealer.setRetired(true);
        }
        for (BlackjackPlayer player : players) {
            if (player.calcScore(true) >= 21) {
                reached = true;
                player.setRetired(true);
            }
        }

        return reached;
    }

    public boolean scoreThresholdReached() {
        return scoreThresholdReached(21);
    }

    public void gameEnd() {
        //        if (dealerSum == 21 || playerSum == 21) {
//            game.win(dealerSum, playerSum);
//        } else if (dealerSum > 21 || playerSum > 21) {
//            game.lose(dealerSum, playerSum);
//        }



    }

    public void drawCardsForPlayers() {
        for (BlackjackPlayer player : players) {
            player.drawCard();
        }
    }

    public void drawCardsForPlayers(int times) {
        for (int i = 0; i < times; i++) {
            drawCardsForPlayers();
        }
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
            System.out.println("HOUSE WINS!!!");
        }
    }

}
