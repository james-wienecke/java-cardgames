package blackjack;

public class DealerAI extends BlackjackPlayer {
    public DealerAI(Blackjack game) {
        super(game, "dealer");
    }

//    @Override
//    public void dealStart() {
//        this.drawCard();
//        this.game.getPlayers
//    }

    @Deprecated
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
        //System.out.println(this.getName() + " score: " + this.calcScore(true));
        if (this.state == State.BLACKJACK || this.state == State.BUST) {
            System.out.println(this.getName() + " " + this.state);
        }
    }

    @Override
    protected void makeDecision() {
        if (score >= 17) {
            state = State.STAND;
        } else {
            state = State.HIT;
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

    public void initialDeal() {
        // deals one facedown card to themselves
        addCardToHand(drawCard(false));
        // deals one faceup card to each players
        game.getPlayers().forEach(player -> {
            player.addCardToHand(drawCard(true));
        });

        // deals one faceup card to themselves
        addCardToHand(drawCard(true));
        updateScore();
        // deals one faceup card to each players
        game.getPlayers().forEach(player -> {
            player.addCardToHand(drawCard(true));
            player.updateScore();
        });
        // round 0 is over, now it is round 1
        game.incrementRound();
    }

    public void dealRound() {

    }

    public void servePlayer(BlackjackPlayer player) {
        while (player.getHands().stream().anyMatch(Hand::isInPlay)) {
            for (Hand hand : player.getHands()) {
                player.makeDecision();
                switch(hand.getState()) {
                    case DOUBLEDOWN:
                        hand.addCard(drawCard(false));
                        hand.setInPlay(false);
                        break;
                    case HIT:
                        hand.addCard(drawCard(true));
                        hand.updateScore();
                        break;
                    case STAND:
                    case BUST:
                    case BLACKJACK:
                    case SURRENDER:
                        hand.setInPlay(false);
                        break;
                }
            }
        }
    }
}
