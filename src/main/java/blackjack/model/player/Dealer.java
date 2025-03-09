package blackjack.model.player;

import blackjack.model.card.Card;

public class Dealer extends Player {

    public static final int RECEIVABLE_POINT = 16;

    public boolean isDrawable() {
        return calculatePoint() <= RECEIVABLE_POINT;
    }

    public Card getInitialCard() {
        return getReceivedCards().getFirstCard();
    }
}
