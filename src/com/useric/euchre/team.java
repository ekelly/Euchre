/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.useric.euchre;

/**
 *
 * @author Eric
 */
class team {
    // instance variables
    int score;
    boolean calledTrump;
//    player[] Player = new player[2];  //just references

//    public team(player player1, player player2) {
//        Player[0] = player1;
//        Player[1] = player2;
//    }

    // accessor methods
    void increaseScore(int value) {
        score = score+value;
    }
    int getScore() {
        return score;
    }
    boolean calledTrump() {
        return calledTrump;
    }
    void setCalledTrump(boolean b) {
        calledTrump = b;
    }
    void decreaseScore(int value) {
        score = score-value;
    }
}
