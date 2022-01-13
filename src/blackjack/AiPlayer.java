package blackjack;

public class AiPlayer extends BlackjackPlayer {
    public AiPlayer(Blackjack game) {
        super(game, "dealer");
    }

//    @Override
//    public int calcScore(boolean faceUpOnly) {
//         int score = super.calcScore(faceUpOnly);
//         if (this.getScore() >= 17) this.setState(State.STAND);
//         return score;
//    }
}
