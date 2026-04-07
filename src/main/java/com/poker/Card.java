package com.poker;

public class Card {
    
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
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
    public String toString() {
        // The switch expression directly assigns the result to rankLabel
        String rankLabel = switch (rank) {
            case ACE -> "A";
            case KING -> "K";
            case QUEEN -> "Q";
            case JACK -> "J";
            case TEN -> "10";
            default -> String.valueOf(rank.getValue()); // Uses 2-9 for others
        };

        return "[" + rankLabel + suit.getSymbol() + "]";
    }
}