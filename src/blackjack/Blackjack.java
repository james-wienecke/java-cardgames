package blackjack;

import cards.Card;
import cards.Deck;

import java.util.ArrayList;

public class Blackjack {
    private boolean gameWon;
    private boolean gameOver;
    private int round;
    private Deck deck;
    private DealerAI dealer;
    private ArrayList<BlackjackPlayer> players;
    private ArrayList<BlackjackPlayer> allPlayers;


    public Blackjack() {
        this.gameWon = false;
        this.gameOver = false;
        this.round = 0;
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

    public Card drawCard(boolean faceUp) {
        return deck.draw(faceUp);
    }

    public void play() {
        this.gameStart();
        this.gameLoop();
    }

    public void incrementRound() {
        round++;
    }

    public int getRound() {
        return round;
    }

    public void gameStart() {
        dealer.initialDeal();

        for (BlackjackPlayer player : players) {
            // serve players
            dealer.servePlayer(player);
        }

//        // draw 2 cards, one facedown
//        this.dealer.drawCard();
//        this.dealer.drawCard();
//
//        drawCardsForPlayers();
//
//        this.allPlayers.forEach(player -> {
//            player.printCardStatus();
//            System.out.println(player.getName() + " score: " + player.calcScore(true));
//        });
//
//        drawCardsForPlayers();
//
//        this.dealer.flipLastCard();
//
//        this.allPlayers.forEach(player -> {
//            player.printCardStatus();
//            System.out.println(player.getName() + " score: " + player.calcScore(true));
//        });
    }

    public void gameLoop() {
        do {
            if (allPlayers.stream().anyMatch(p -> p.getState() == State.BLACKJACK || p.getState() == State.BUST)) {
                break;
            }
            for (BlackjackPlayer player : players) {
                player.takeTurn();
            }
            dealer.takeTurn();

            // if all players & dealer are standing, end game
            if (allPlayers.stream().allMatch(p -> p.getState() == State.STAND ||
                    p.getState() == State.BLACKJACK ||
                    p.getState() == State.BUST ||
                    p.getState() == State.SURRENDER)) {
                this.gameOver = true;
            }
        } while (!this.gameOver);
        this.gameEnd();
    }

    @Deprecated
    private void printCardStatus() {
        allPlayers.forEach(player -> {
            System.out.println(player.getName());
            //player.getCards().forEach(System.out::println);
        });
    }


    public boolean scoreThresholdReached(int scoreThreshold) {
        return allPlayers.stream().anyMatch(player -> player.getScore() > scoreThreshold);
    }

    public boolean scoreThresholdReached() {
        return scoreThresholdReached(21);
    }

    public void gameEnd() {
        ArrayList<BlackjackPlayer> winners = new ArrayList<>();
        ArrayList<BlackjackPlayer> losers = new ArrayList<>();

        switch (dealer.getState()) {
            case BLACKJACK:
                // dealer wins, all players lose
                winners.add(dealer);
                losers.addAll(players);
                break;
            case BUST:
                // dealer loses, all players win
                winners.addAll(players);
                losers.add(dealer);
                break;
            default:
                // check which players are higher or lower than dealer's score
                players.forEach(player -> {
                    if (player.getState() == State.BUST) {
                        losers.add(player);
                    } else {
                        if (player.getScore() > dealer.getScore()) {
                            winners.add(player);
                        } else {
                            losers.add(player);
                        }
                    }
                });
        }
        gameEndPrintWinsAndLoss(winners, losers);
    }

    private void gameEndPrintWinsAndLoss(ArrayList<BlackjackPlayer> winners, ArrayList<BlackjackPlayer> losers) {
        StringBuffer winSb = new StringBuffer("Winners: ");
        for (BlackjackPlayer player : winners) {
            winSb.append(player.getName()).append(' ');
        }
        StringBuffer loseSb = new StringBuffer("Losers: ");
        for (BlackjackPlayer player : losers) {
            loseSb.append(player.getName()).append(' ');
        }
        System.out.println(winSb);
        System.out.println(loseSb);
    }

    public void drawCardsForPlayers() {
        for (BlackjackPlayer player : players) {
            player.drawCard();
        }
    }

    @Deprecated
    public void drawCardsForPlayers(int times) {
        for (int i = 0; i < times; i++) {
            drawCardsForPlayers();
        }
    }

    public void addDealer(DealerAI dealer) {
        this.dealer = dealer;
        this.allPlayers.add(dealer);
    }

    public void addPlayer(BlackjackPlayer player) {
        this.players.add(player);
        this.allPlayers.add(player);
    }

    public ArrayList<BlackjackPlayer> getPlayers() {
        return this.players;
    }
}
