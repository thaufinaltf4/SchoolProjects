
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public List<Card> draw(int count) {
        List<Card> drawnCards = new ArrayList<>();
        if (count < 0) {
            return drawnCards;
        }
        int actualCount = Math.min(count, cards.size());
        for (int i = 0; i < actualCount; i++) {
            drawnCards.add(cards.remove(0));
        }
        return drawnCards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public List<Card> getCardsByRank(Rank rank) {
        List<Card> matching = new ArrayList<>();
        for (Card card : cards) {
            if (card.getRank() == rank) {
                matching.add(card);
            }
        }
        return matching;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}