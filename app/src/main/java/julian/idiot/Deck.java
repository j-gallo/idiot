package julian.idiot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by Julian on 1/1/2017.
 */

public class Deck {
    private LinkedList<Card> cards = new LinkedList<Card>();
    private Card lastCard;
    private Card currentCard;
    private LinkedList<Card> seenCards = new LinkedList<Card>();

    public Deck(){
        String[] suits = new String[]{"clubs", "diamonds", "hearts", "spades"};
        String[] values = new String[]{"ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king"};
        for (String suit: suits) {
            for (String value: values) {
                Card card = new Card(suit, value);
                this.cards.add(card);
            }
        }
        for (int i=0; i<4; i++) {
            seenCards.add(new Card("card", "blank"));
        }
        Collections.shuffle(cards);
    }

    public Card getNextCard() {
        if (this.cards.size() > 0) {
            this.lastCard = this.currentCard;
            this.currentCard = this.cards.remove(0);
            this.seenCards.add(0, this.currentCard);
        }
        return this.currentCard;
    }

    public Card getLastCard() {
        if (this.lastCard != this.currentCard) {
            this.cards.add(0, this.currentCard);
            this.seenCards.remove(0);
        }
        this.currentCard = this.lastCard;
        return this.currentCard;
    }

    public ArrayList getCardsToDisplay() {
        ArrayList cards = new ArrayList(4);
        for (int i=0; i<4; i++) {
            cards.add(this.seenCards.get(i));
        }
        return cards;
    }

    public void deleteInnerCards() {
        for (int i=0; i<2; i++) {
            this.seenCards.remove(1);
        }
    }

    public void deleteOuterCards() {
        for (int i=0; i<4; i++) {
            this.seenCards.remove(0);
        }
    }
}
