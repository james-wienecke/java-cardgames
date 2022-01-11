package cards;

public enum CardSuit {
    HEARTS('♥'),
    DIAMONDS('♦'),
    SPADES('♠'),
    CLUBS('♣');

    private char suit;

    CardSuit(char suit) {
        this.suit = suit;
    }

    public char getSuitChar() {
        return this.suit;
    }
}

