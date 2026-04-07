package com.poker;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Force UTF-8 for symbols (The "Magic Line")
        System.setProperty("file.encoding", "UTF-8");

        // 2. Setup the game
        Deck deck = new Deck();
        deck.shuffle();

        // 3. Create a list of players (This is better than player1, player2, etc.)
        List<Player> players = new ArrayList<>();
        players.add(new Player("Grant"));
        players.add(new Player("Owen"));
        players.add(new Player("Kyle"));
        players.add(new Player("Bennett"));
        players.add(new Player("Sunny"));
        players.add(new Player("Landon"));

        // 4. Deal 5 cards to every player in the list
        for (int i = 0; i < 5; i++) {
            for (Player p : players) {
                p.addCard(deck.dealCard());
            }
        }

        // 5. Print the results
        System.out.println("--- Poker Round Results ---");
        for (Player p : players) {
            System.out.println(p);
        }
    }
}