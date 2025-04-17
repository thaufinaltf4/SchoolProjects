
import java.util.Objects;

public class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        if (rank == null || suit == null) {
            throw new NullPointerException();
        }
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card other) {
        int suitComparison = this.suit.compareTo(other.suit);
        if (suitComparison != 0) {
            return suitComparison;
        }
        return this.rank.compareTo(other.rank);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) {
            return false;
        }
        Card other = (Card) obj;
        return this.rank == other.rank && this.suit == other.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }
}
