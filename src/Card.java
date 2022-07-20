import java.util.Objects;
/**
 * The Class {@code Card} represents playing cards of a 32-cards deck. It implements the interface
 * Comparable, in which the comparison is based on the rank of the cards, while suits are ignored.
 * This class is used in the class {@link Bettelmann}, which simulates the card game 'Bettelmann'.
 */
public class Card implements Comparable<Card> {
    private int id;
    public static int nCards = 32;

    /**
     * Constructor of {@code Card} objects
     *
     * @param id The id of the card from 0 (7 diamond) to 31 (ace of clubs)
     */
    public Card(int id) {
        this.id = id;
    }

    /**
     * Returns the suit of this card as symbol String.
     *
     * @return unicode symbol of the suit of this card
     */
    public String getSuit() {
        //String[] suitList = {"Karo", "Herz", "Pik", "Kreuz"};
        String[] suitList = {"\u2666", "\u2665", "\u2660", "\u2663"};
        return suitList[id%4];
    }

    /**
     * Returns the value of the card (7 -> 0, 8 -> 1, ... King -> 6, Ace -> 7)
     *
     * @return value of this card, i.e., the rank minus 7
     */
    public int getValue() {
        return id / 4;
    }

    /**
     * Returns the value of this card as a short String.
     *
     * @return Value of the card as short String
     */
    public String getValueAsString() {
//        String[] valueList = {"Sieben", "Acht", "Neun", "Zehn", "Bube", "Dame", "König", "As"};
        String[] valueList = {"7", "8", "9", "10", "B", "D", "K", "A"};
        return valueList[getValue()];
    }

    /**
     * Compares Card objects based on their value, regardless of their suit.
     *
     * @param that the other Card, to which this card is compared
     * @return results of the comparison (<0 if this card has a lower rank than that card, =0 if equivalent, >0 else)
     */
    @Override
    public int compareTo(Card that) {
        return Integer.compare(this.getValue(), that.getValue());
    }

    /**
     * Returns a short String representation of this card (suit then value)
     *
     * @return String representation of the {@code Card} object
     */
    @Override
    public String toString() {
        return getSuit() + getValueAsString();
    }

    /**
     * Return whether this card coincides (in rank and suit) with a given object.
     *
     * @param o Object (typically a {@link Card}) with which this card is compared
     * @return whether this card is the same as the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card that = (Card) o;
        return id == that.id;
    }

    public static void main(String[] args) {
        for (int id = 0; id < nCards; id++) {
            Card card = new Card(id);
            System.out.println("ID = " + id + " -> " + card);
        }
    }
}

