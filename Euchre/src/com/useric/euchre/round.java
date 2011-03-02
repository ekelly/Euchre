/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

/**
 *
 * @author Eric
 */
class round {

    deck Deck = new deck();
    player[] player = new player[4];
    // This will be difficult - make it last
    trick Trick = new trick();

    void acceptPlayers(player playerArray[]) {
        for (int i = 0; i<4; i++) {
            player[i] = playerArray[1];
        }
    }

    void deal(player playerArray[]) {
        Deck.deal(playerArray);
    }
}
