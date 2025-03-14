package blackjack.model.game;

import blackjack.model.card.Card;
import blackjack.model.card.CardType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReceivedCards {

    public static final int BONUS_ACE_POINT = 10;
    public static final int BUST_POINT = 21;
    private final List<Card> cards = new ArrayList<>();

    public void receive(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int calculateTotalPoint() {
        int basePoint = calculateTotalDefaultPoint();
        int aceCount = countAces();
        return adjustForAces(basePoint, aceCount);
    }

    public boolean isBust() {
        return isBust(calculateTotalPoint());
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }

    public Stream<Card> stream() {
        return cards.stream();
    }

    private int calculateTotalDefaultPoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    private int countAces() {
        return Math.toIntExact(cards.stream()
                .filter(card -> card.equalsCardType(CardType.ACE))
                .count());
    }

    private int adjustForAces(int basePoint, int aceCount) {
        return IntStream.range(0, aceCount).reduce(basePoint, (currentPoint, i) -> plusTenPoint(currentPoint));
    }

    private int plusTenPoint(int currentPoint) {
        if (!isBust(currentPoint + BONUS_ACE_POINT)) {
            currentPoint += BONUS_ACE_POINT;
        }
        return currentPoint;
    }

    private boolean isBust(int point) {
        return point > BUST_POINT;
    }
}
