package blackjack;

import util.Input;

public class HumanPlayer extends BlackjackPlayer {
    public HumanPlayer(Blackjack game, String name) {
        super(game, name);
        in = new Input();
    }

    private Input in;

    @Override
    public void makeDecision() {
        if (!retired) {
            for (Hand hand: hands) {
                hand.printCardStatus();
                int choiceLimit = game.getRound() == 1 ? 4 : 2;
                int decision = in.getInt(0, choiceLimit, getRoundOptions());
                switch (decision) {
                    case 0:
                        hand.setState(State.SURRENDER);
                        break;
                    case 1:
                        hand.setState(State.HIT);
                        break;
                    case 2:
                        hand.setState(State.STAND);
                        break;
                    case 3:
                        hand.setState(State.DOUBLEDOWN);
                        break;
                    case 4:
                        hand.setState(State.SPLIT);
                        break;
                }
            }
        }
    }

    public String getRoundOptions() {
        StringBuffer sb = new StringBuffer("Enter your action for this round:\n");
        switch (game.getRound()) {
            case 1:
                sb.append("(1) Hit\n")
                        .append("(2) Stand\n")
                        .append("(3) Double down\n")
                        .append("(4) Split\n")
                        .append("(0) Surrender");
                break;
            default:
                sb.append("(1) Hit\n").append("(2) Stand\n").append("(0) Surrender");
        }
        return sb.toString();
    }

//    public void takeTurn() {
//        takeActionInput();
//        switch (this.state) {
//            case HIT:
//                this.drawCard();
//                break;
//            case STAND:
//                this.retired = true;
//                break;
//        }
//        System.out.println(this.getName() + " " + this.getState() + "s");
//        this.printCardStatus();
//        if (!this.retired) {
//            //System.out.println(this.getName() + " score: " + this.calcScore(true));
//        } else {
//            System.out.println(this.getName() + " score: " + this.getScore());
//        }
//        in.waitForAnyLine("Press enter to continue.");
//    }

//    private void takeActionInput() {
//        if (!this.retired) {
//            int input = in.getInt(1 , 2, "Enter your action for this round:\n" +
//                    "(1) Hit\n" +
//                    "(2) Stand");
//            switch (input) {
//                case 1:
//                    this.state = State.HIT;
//                    break;
//                case 2:
//                    this.state = State.STAND;
//                    this.retired = true;
//                    break;
//            }
//        }
//    }
}
