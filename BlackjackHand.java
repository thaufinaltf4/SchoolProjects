
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackHand {
    private static final Map<Rank, Integer> CARD_VALUES;
    private static final int MAX_VALUE = 21;

    private List<Card> cards = new ArrayList<>();
    private int value = 0;
    private int numAcesAs11 = 0;

    static {
        CARD_VALUES = new HashMap<>();
        for (Rank rank : Rank.values()) {
            switch (rank) {
                case JACK:
                case QUEEN:
                case KING:
                    CARD_VALUES.put(rank, 10);
                    break;
                case ACE:
                    CARD_VALUES.put(rank, 11);
                    break;
                default:
                    CARD_VALUES.put(rank, Integer.parseInt(rank.toString()));
            }
        }
    }

    public BlackjackHand(Card c1, Card c2) {
        addCard(c1);
        addCard(c2);
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException();
        }

        if (value >= MAX_VALUE) {
            return;
        }
        cards.add(card);


        int cardValue = CARD_VALUES.get(card.getRank());
        value += cardValue;

        if (card.getRank() == Rank.ACE) {
            numAcesAs11++;
        }

        while (value > MAX_VALUE && numAcesAs11 > 0) {
            value -= 10;
            numAcesAs11--;
        }
    }

    public int size() {
        return cards.size();
    }

    public static Map<Rank, Integer> getCardValues() {
        return new HashMap<>(CARD_VALUES);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
