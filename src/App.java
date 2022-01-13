import blackjack.DealerAI;
import blackjack.Blackjack;
import blackjack.BlackjackPlayer;
import blackjack.HumanPlayer;
import util.Input;

public class App {

    public static void main(String[] args) {
        Input in = new Input();
        Blackjack game = new Blackjack();

        // player setup

        BlackjackPlayer dealer = new DealerAI(game);
        BlackjackPlayer player = new HumanPlayer(game, "player");

        game.addDealer(dealer);
        game.addPlayer(player);

        game.play();


        // initial deal
//        Card initialDealDealer1 = game.getDeck().draw().flipCard();
//        Card initialDealDealer2 = game.getDeck().draw();
//        game.getDealerCards().add(initialDealDealer1);
//        game.getDealerCards().add(initialDealDealer2);

//        int dealerSum = game.sumOfCards(game.getDealerCards());
//        int playerSum = 0;

//        if (dealerSum == 21) {
//            initialDealDealer2.flipCard();
//            game.win(dealerSum, playerSum);
//        }

//        System.out.println("House has:");
//        for (Card card : game.getDealerCards()) {
//            System.out.println(card.toString());
//        }


//        Card initialDealPlayer1 = game.getDeck().draw().flipCard();
//        Card initialDealPlayer2 = game.getDeck().draw().flipCard();

//        game.getPlayerCards().add(initialDealPlayer1);
//        game.getPlayerCards().add(initialDealPlayer2);
//        playerSum = game.sumOfCards(game.getPlayerCards());

//        if (playerSum == 21) {
//            initialDealDealer2.flipCard();
//            game.win(dealerSum, playerSum);
//        }

//        System.out.println("You have:");
//        for (Card card : game.getPlayerCards()) {
//            System.out.println(card.toString());
//        }

//        while (game.isGameWon()) {
//            // play game!
//
//            if (playerSum < 17) {
//                // hit
//            } else if (playerSum >= 17) {
//                // stand
//            }
//            if (dealerSum < 17) {
//                // hit
//            } else if (dealerSum >= 17) {
//                // stand
//            }
//        }
//
//        dealerSum = game.sumOfCards(game.getDealerCards());
//        playerSum = game.sumOfCards(game.getPlayerCards());
//
//        if (dealerSum == 21 || playerSum == 21) {
//            game.win(dealerSum, playerSum);
//        } else if (dealerSum > 21 || playerSum > 21) {
//            game.lose(dealerSum, playerSum);
//        }

    }
}