/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

/**
 *
 * @author Eric
 */
class trick {

    player[] player = new player[4];
    card[] cardOfPlayer = new card[4];

    //methods
    void acceptPlayers(player playerArray[]) {
        for (int i = 0; i<4; i++) {
            player[i] = playerArray[1];
        }
    }

    void acceptCard(card acceptedCard, int player) {
        cardOfPlayer[player] = acceptedCard;
    }

    int playCard(int leader) {
        // call leader, tell to play a card
        return leader;  // return the new leader
    }

    int playTrick(int leader) {
        // playCard five times
        for(int i=0; i<5; i++) {
            playCard(leader);
        }
        // check who wins the trick
        return checkTrickWinner();
    }

    int checkTrickWinner() {
        // returns int of winning player
        int winningPlayer = -1; // negative one to avoid error
        // find the winning card player
        card winningCard = new card("suit", -100);
        for (int i=0; i<4; i++) {
            if(cardOfPlayer[i].value > winningCard.value)  {
                winningPlayer = i;
            }
        }
        return winningPlayer;
    }
}
