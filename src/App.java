import util.Input;

public class App {

    public static void main(String[] args) {
        Input in = new Input();
        Blackjack game = new Blackjack();
        // initial deal
        Card initialDealDealer1 = game.getDeck().draw().flipCard();
        Card initialDealDealer2 = game.getDeck().draw();

        game.getDealerCards().add(initialDealDealer1);
        game.getDealerCards().add(initialDealDealer2);

        int dealerSum = game.sumOfCards(game.getDealerCards());
        int playerSum = 0;

        if (dealerSum == 21) {
            initialDealDealer2.flipCard();
            game.win(dealerSum, playerSum);
        }

        for (Card card : game.getDealerCards()) {
            System.out.println(card.toString());
        }




    }
}
