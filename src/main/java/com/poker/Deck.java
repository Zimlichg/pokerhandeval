package com.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        initialize();
    }
    private void initialize() {
        // For every Suit in our Enum 
        for (Suit suit : Suit.values()) {
            // For every Rank in our Enum 
            for (Rank rank : Rank.values()) {
                // Create a new card and add it to our list
                cards.add(new Card(rank, suit));
            }
        }
    }
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            return null; // Or throw an error if the deck is empty
        }
        return cards.remove(0); // Take the top card off the deck
    }
}