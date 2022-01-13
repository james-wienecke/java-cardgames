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
    private ArrayList<BlackjackPlayer> allPlayers;


    public Blackjack() {
        this.gameWon = false;
        this.gameOver = false;
        this.deck = new Deck();
        this.deck.shuffle();
        this.players = new ArrayList<>();
        this.allPlayers = new ArrayList<>();
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

        drawCardsForPlayers();

        this.allPlayers.forEach(player -> {
            player.printCardStatus();
            System.out.println(player.getName() + " score: " + player.calcScore(true));
        });

        //printCardStatus();

        drawCardsForPlayers();

        this.dealer.flipLastCard();

        this.allPlayers.forEach(player -> {
            player.printCardStatus();
            System.out.println(player.getName() + " score: " + player.calcScore(true));
        });
        //printCardStatus();
    }

    public void gameLoop() {
        do {
            for (BlackjackPlayer player : players) {
                player.takeTurn();
            }
            dealer.takeTurn();

            // if all players & dealer are standing, end game
            if (allPlayers.stream().allMatch(p -> p.getState() == State.STAND)) {
                this.gameOver = true;
            }
        } while (!this.gameOver);
        this.gameEnd();
    }

    @Deprecated
    private void printCardStatus() {
        allPlayers.forEach(player -> {
            System.out.println(player.getName());
            player.getCards().forEach(System.out::println);
        });
    }

//    public boolean scoreThresholdReached(int scoreThreshold) {
//        boolean reached = false;
//        if (dealer.calcScore(true) >= 21) {
//            reached = true;
//        }
//        for (BlackjackPlayer player : players) {
//            if (player.calcScore(true) >= 21) {
//                reached = true;
//            }
//        }
//
//        return reached;
//    }
public boolean scoreThresholdReached(int scoreThreshold) {
    return allPlayers.stream().anyMatch(player -> player.getScore() > scoreThreshold);
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
//       if (dealer.getState() == State.BLACKJACK) {
//           // dealer wins
//       } else if (players.stream().anyMatch(p -> p.getState() == State.BLACKJACK)) {
//           // player wins
//       } else {
//           if (de)
//       }
        ArrayList<BlackjackPlayer> winners = new ArrayList<>();
        ArrayList<BlackjackPlayer> losers = new ArrayList<>();

        switch (dealer.getState()) {
            case BLACKJACK:
                // dealer wins, all players lose
                losers.addAll(players);
                break;
            case BUST:
                // dealer loses, all players win
                winners.addAll(players);
                break;
            case STAND:
                // check which players are higher or lower than dealer's score
                players.forEach(player -> {
                    if (player.getScore() > dealer.getScore()) {
                        winners.add(player);
                    } else {
                        losers.add(player);
                    }
                });
        }

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

    public void addDealer(BlackjackPlayer dealer) {
        this.dealer = dealer;
        this.allPlayers.add(dealer);
    }

    public void addPlayer(BlackjackPlayer player) {
        this.players.add(player);
        this.allPlayers.add(player);
    }
}
