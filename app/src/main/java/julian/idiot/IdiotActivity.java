package julian.idiot;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class IdiotActivity extends AppCompatActivity {

    private Card previouslySelectedCard;
    private Deck deck = new Deck();

    private void displayCards() {
        ArrayList<Card> cards_to_display = deck.getCardsToDisplay();
        if (cards_to_display != null) {
            for (int i = 1; i <= 4; i++) {
                Card card = cards_to_display.get(i - 1);
                if (card != null) {
                    int view_id = getResources().getIdentifier("card_display_" + i, "id", getPackageName());
                    ImageView cardView = (ImageView) findViewById(view_id);
                    if (cardView.getTag(R.id.image_filter) == "grayed") {
                        cardView.setColorFilter(null);
                        cardView.setTag(R.id.image_filter, "");
                    }
                    String suit = card.getSuit();
                    String value = card.getValue();
                    String current_card = value + "_" + suit;
                    int drawable_id = getResources().getIdentifier(current_card, "drawable", getPackageName());
                    cardView.setImageResource(drawable_id);
                    cardView.setTag(R.id.image_card, card);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton nextCard = (ImageButton) findViewById(R.id.next_card);
        final Button lastCardButton = (Button) findViewById(R.id.last_card);
        final Button newGameButton = (Button) findViewById(R.id.new_game);


        nextCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deck.getNextCard();
                displayCards();
            }
        });

        lastCardButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deck.getLastCard();
                displayCards();
            }
        });

        newGameButton.setOnClickListener(new View.OnClickListener(){
          public void onClick(View v) {
              deck = new Deck();
              displayCards();
          }
        });
    }

    public void checkIsMatch(View view) {
        if(view.getTag(R.id.image_filter) != "grayed") {
            ((ImageView) view).setColorFilter(Color.argb(100,100,100,100));
            view.setTag(R.id.image_filter, "grayed");
        } else {
            ((ImageView) view).setColorFilter(null);
            view.setTag(R.id.image_filter, "");
        }

        Card selectedCard = (Card) view.getTag(R.id.image_card);
        ArrayList<Card> visibleCards = deck.getCardsToDisplay();
        Card firstCard = visibleCards.get(0);
        Card secondCard = visibleCards.get(1);
        Card thirdCard = visibleCards.get(2);
        Card lastCard = visibleCards.get(3);

        if (firstCard.getSuit() == lastCard.getSuit()) {
            if ((selectedCard == secondCard || selectedCard == thirdCard)
                    && (previouslySelectedCard == secondCard || previouslySelectedCard == thirdCard)
                    && previouslySelectedCard != selectedCard) {
                deck.deleteInnerCards();
                displayCards();
            }
        } else if (firstCard.getValue() == lastCard.getValue()){
            if ((selectedCard == firstCard || selectedCard == lastCard)
                    && (previouslySelectedCard == firstCard || previouslySelectedCard == lastCard)
                    && previouslySelectedCard != selectedCard) {
                deck.deleteOuterCards();
                displayCards();
            }
        }

        previouslySelectedCard = selectedCard;

        for (int i = 1; i <= 4; i++) {
            int view_id = getResources().getIdentifier("card_display_" + i, "id", getPackageName());
            if (view_id == view.getId()) {
                continue;
            }
            ImageView cardView = (ImageView) findViewById(view_id);
            if (cardView.getTag(R.id.image_filter) == "grayed") {
                cardView.setColorFilter(null);
                cardView.setTag(R.id.image_filter, "");
            }
        }
    }

}
