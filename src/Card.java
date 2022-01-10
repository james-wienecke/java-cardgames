public class Card {
    private CardSuit suit;
    private byte value;
    private boolean faceUp;

    public Card(byte suit, byte value) {
        this.value = value;
        this.faceUp = false;
        switch (suit) {
            case 0:
                this.suit = CardSuit.CLUBS;
                break;
            case 1:
                this.suit = CardSuit.SPADES;
                break;
            case 2:
                this.suit = CardSuit.DIAMONDS;
                break;
            case 3:
                this.suit = CardSuit.HEARTS;
                break;
        }
    }

    public byte getValue() {
        return value;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public char getSuitChar() {
        return suit.getSuitChar();
    }

    public char getValueAsChar() {
        switch(this.value) {
            case 10:
                return '0'; // 10 jeeze this is a mess
            case 11:
                return 'J'; // Jack
            case 12:
                return 'Q'; // Queen
            case 13:
                return 'K'; // King
            case 1:
                return 'A'; // Ace
            case 0:
                return 'X'; // joker
            default:
                // 48 is added to bump an offset on ASCII table to hit the decimal numbers lol
                return (char) (value + (byte) 48);
        }
    }

    public String getValueAsString() {
        String val = null;
        switch(this.value) {
            case 11:
                return String.valueOf('J'); // Jack
            case 12:
                return String.valueOf('Q'); // Queen
            case 13:
                return String.valueOf('K'); // King
            case 1:
                return String.valueOf('A'); // Ace
            case 0:
                return "JOKER"; // joker
            default:
                return String.valueOf(value);
        }
    }

    public Card flipCard() {
        this.faceUp = !this.faceUp;
        return this;
    }

    @Override
    public String toString() {
        String value, suit, suitName;
        if (this.faceUp) {
            value = getValueAsString();
            suit = String.format("%c", this.suit.getSuitChar());
            suitName = this.suit.name();
        } else {
            value = "?";
            suit = "?";
            suitName = "FACEDOWN";
        }


        return String.format("%2s %s %s", value, suit, suitName);
    }
}
