package julian.idiot;

/**
 * Created by Julian on 1/1/2017.
 */

public class Card {
    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.value = value;
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }
}
