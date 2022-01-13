package blackjack;

import util.Input;

public class HumanPlayer extends BlackjackPlayer {
    public HumanPlayer(Blackjack game, String name) {
        super(game, name);
        in = new Input();
    }

    private Input in;

    @Override
    public void takeTurn() {
        takeActionInput();
        switch (this.state) {
            case HIT:
                this.drawCard();
                break;
            case STAND:
                this.retired = true;
                break;
        }
        this.printCardStatus();
        if (!this.retired) {
            System.out.println(this.getName() + " score: " + this.calcScore(true));
        } else {
            System.out.println(this.getName() + " score: " + this.getScore());
        }
    }

    private void takeActionInput() {
        int input = in.getInt(1 , 2, "Enter your action for this round:\n" +
                "(1) Hit\n" +
                "(2) Stand");
        switch (input) {
            case 1:
                this.state = State.HIT;
                break;
            case 2:
                this.state = State.STAND;
                break;
        }
    }
}
