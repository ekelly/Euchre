/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

//import java.util.ArrayList;

/**
 *
 * @author Eric
 */
class trick {

    player[] Player = new player[4];
//    ArrayList<card> cardOfPlayer;
    card[] cardOfPlayer = new card[4];
    String suitLed;
    int currentWinner;

    //methods
    public trick(player playerArray[]) {
        for (int i = 0; i<4; i++) {
            Player[i] = playerArray[i];
            cardOfPlayer[i] = new card("", -5);
        }
//        cardOfPlayer = new ArrayList<card>(4);
    }

    int incrementPlayer(int leader) {
        leader = leader+1;
        if(leader>3) {
            leader = leader-4;
        }
        return leader;
    }

    void acceptCard(card acceptedCard, int player) {
//        cardOfPlayer.set(player, acceptedCard);
        cardOfPlayer[player] = acceptedCard;
        Player[player].myHand.removeCard(acceptedCard);

    }

    int playTrick(int leader) {
        int ally = incrementPlayer(incrementPlayer(leader));
        // call leader, tell to play a card
        card playedCard;
        playedCard = Player[leader].selectCard();
//        card playedCard = Player[leader].selectCard();
        acceptCard(playedCard, leader);
        currentWinner = leader;
        suitLed = cardOfPlayer[leader].getSuit();
        leader = incrementPlayer(leader);
        ally = incrementPlayer(ally);
        //After the first card is played, set all values based on suitLed
        for(int a=0; a<4; a++) {
            for(int b=0; b<Player[a].myHand.cardsInHand.size(); b++) {
                Player[a].myHand.cardsInHand.get(b).setTrickValue(suitLed);
            }
        }
        //Play the remaining 3 cards in the trick
        for(int i=0; i<3; i++){
            leader = playCard(leader, currentWinner, ally, currentWinnerCard(), suitLed);
            leader = incrementPlayer(leader);
        }
        return checkTrickWinner();  // return the new leader
    }

    int playTrick(int leader, int skipPlayer) {
        int ally = incrementPlayer(incrementPlayer(leader));
        // call leader, tell to play a card
        card playedCard;
        // for going alone
        if(leader != skipPlayer) {
            playedCard = Player[leader].selectCard();
        } else {
            leader = incrementPlayer(leader);
            playedCard = Player[leader].selectCard();
        }
//        card playedCard = Player[leader].selectCard();
        acceptCard(playedCard, leader);
        currentWinner = leader;
        suitLed = cardOfPlayer[leader].getSuit();
        leader = incrementPlayer(leader);
        ally = incrementPlayer(ally);
        //After the first card is played, set all values based on suitLed
        for(int a=0; a<4; a++) {
            for(int b=0; b<Player[a].myHand.cardsInHand.size(); b++) {
                Player[a].myHand.cardsInHand.get(b).setTrickValue(suitLed);
            }
        }
        //Play the remaining 3 cards
        for(int i=0; i<3; i++){
            if(leader != skipPlayer) {
                leader = playCard(leader, currentWinner, ally, currentWinnerCard(), suitLed);
            } else {
            }
            leader = incrementPlayer(leader);
        }
        return checkTrickWinner();  // return the new leader
    }

    int playCard(int leader, int winner, int ally, card highestCard, String suitLed) {
        // call leader, tell to play a card
        card playedCard = Player[leader].selectCard(winner, ally, highestCard, suitLed);
        acceptCard(playedCard, leader);
        currentWinner = currentWinner(leader);
        return leader;  // return the new leader
    }

    void resetTrick() {
//        cardOfPlayer.clear();
        for(int i=0; i<4; i++) {
            cardOfPlayer[i] = new card("", -5);
            Player[i].Team.setCalledTrump(false);
        }
        suitLed = null;
        currentWinner = 10;
    }

    int currentWinner(int leader) {
        if(currentWinner < 5) {
//            if(cardOfPlayer.get(currentWinner).getValue() > cardOfPlayer.get(leader).getValue()) {
//                currentWinner = leader;
//            }
            if(cardOfPlayer[currentWinner].getValue() > cardOfPlayer[leader].getValue()) {
                currentWinner = leader;
            }
        } else if(currentWinner == 10) {
            currentWinner = leader;
        }
        return currentWinner;
    }

    card currentWinnerCard() {
//        return cardOfPlayer.get(currentWinner);
        return cardOfPlayer[currentWinner];
    }

    int checkTrickWinner() {
        // returns int of winning player
        int cardValue = -1;
        int winningPlayer = 5; //TODO: UNINTIALIZE
        for(int i=0; i<4; i++) {
//            if(cardOfPlayer.get(i).getValue() > cardValue) {
//               cardValue = cardOfPlayer.get(i).getValue();
//               winningPlayer = i;
//            }
            if(cardOfPlayer[i].getValue() > cardValue) {
               cardValue = cardOfPlayer[i].getValue();
               winningPlayer = i;
            }
        }
        return winningPlayer;
    }
    int checkTrickWinner(int playerGoingAlone) {
        // returns int of winning player
        int cardValue = -1;
        int winningPlayer = 5; //TODO: UNINTIALIZE
        for(int i=0; i<4; i++) {
//            if(cardOfPlayer.get(i).getValue() > cardValue) {
//               cardValue = cardOfPlayer.get(i).getValue();
//               winningPlayer = i;
//            }
            if(playerGoingAlone != i) {
                if(cardOfPlayer[i].getValue() > cardValue) {
                    cardValue = cardOfPlayer[i].getValue();
                    winningPlayer = i;
                }
            }
        }
        return winningPlayer;
    }
}
