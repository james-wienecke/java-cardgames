package blackjack;

public class DealerAI extends BlackjackPlayer {
    public DealerAI(Blackjack game) {
        super(game, "dealer");
    }

    @Override
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
}
