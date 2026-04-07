import java.util.*;

public class HandEvaluator {

    public static HandRank evaluate(List<Card> hand) {
        // 1. Sort hand by rank value to make straight checking possible
        hand.sort(Comparator.comparingInt(c -> c.getRank().getValue()));

        // 2. Count frequencies (e.g., how many of each Rank)
        Map<Rank, Integer> counts = new HashMap<>();
        for (Card c : hand) {
            counts.put(c.getRank(), counts.getOrDefault(c.getRank(), 0) + 1);
        }

        List<Integer> frequencies = new ArrayList<>(counts.values());
        frequencies.sort(Collections.reverseOrder());

        // 3. Pattern Checks
        boolean isFlush = isFlush(hand);
        boolean isStraight = isStraight(hand);

        // 4. Decision Tree
        if (isFlush && isStraight && hand.get(0).getRank() == Rank.TEN) return HandRank.ROYAL_FLUSH;
        if (isFlush && isStraight) return HandRank.STRAIGHT_FLUSH;
        if (frequencies.get(0) == 4) return HandRank.FOUR_OF_A_KIND;
        if (frequencies.get(0) == 3 && frequencies.get(1) == 2) return HandRank.FULL_HOUSE;
        if (isFlush) return HandRank.FLUSH;
        if (isStraight) return HandRank.STRAIGHT;
        if (frequencies.get(0) == 3) return HandRank.THREE_OF_A_KIND;
        if (frequencies.get(0) == 2 && frequencies.get(1) == 2) return HandRank.TWO_PAIR;
        if (frequencies.get(0) == 2) return HandRank.ONE_PAIR;

        return HandRank.HIGH_CARD;
    }

    private static boolean isFlush(List<Card> hand) {
        Suit firstSuit = hand.get(0).getSuit();
        for (Card c : hand) {
            if (c.getSuit() != firstSuit) return false;
        }
        return true;
    }

    private static boolean isStraight(List<Card> hand) {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i + 1).getRank().getValue() != hand.get(i).getRank().getValue() + 1) {
                return false;
            }
        }
        return true;
    }
}
2. Main.java (The Simulator)
This file handles the actual game loop: shuffling, dealing, and announcing the winner based on the HandEvaluator's score.

Java
package com.poker;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        List<Player> players = Arrays.asList(
            new Player("Grant"), 
            new Player("Owen"), 
            new Player("Kyle"),
            new Player("Bennett"), 
            new Player("Sunny"), 
            new Player("Landon")
        );

        // Deal 5 cards to each player
        for (int i = 0; i < 5; i++) {
            for (Player p : players) {
                p.addCard(deck.dealCard());
            }
        }

        Player winner = null;
        HandRank bestRank = null;

        System.out.println("--- POKER ROUND RESULTS ---");
        for (Player p : players) {
            // Sort hand before printing so it's easier to read
            p.getHand().sort(Comparator.comparingInt(c -> c.getRank().getValue()));
            
            HandRank result = HandEvaluator.evaluate(p.getHand());
            System.out.println(p.getName() + "'s hand: " + p.getHand() + " -> " + result);

            // Winner Logic
            if (bestRank == null || result.getValue() > bestRank.getValue()) {
                bestRank = result;
                winner = p;
            }
        }

        System.out.println("\n🏆 THE WINNER IS: " + winner.getName() + " with a " + bestRank + "!");
    }
}