/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//NOTE: when replacing an element in an ArrayList, use set, not get

package com.useric.euchre;

import java.util.Random;

/**
 *
 * @author Eric
 */
public class game {
    // variables
    player[] Player = new player[4];
    team TeamNS;
    team TeamEW;
    round Round;

    boolean checkGameOver() {
        if (TeamNS.getScore() > 9) {
            System.out.print("TeamNS's score was " + TeamNS.getScore() + " ");
            System.out.println("TeamEW's score was " + TeamEW.getScore());
            return true;
        }
        if (TeamEW.getScore() > 9) {
            System.out.print("TeamNS's score was " + TeamNS.getScore() + " ");
            System.out.println("TeamEW's score was " + TeamEW.getScore());
            return true;
        } else {
            return false;
        }
    }

    int randomInt() {
        // generate a random integer between 0 and 3 for determining dealer
        Random r = new Random();
        int i = r.nextInt(4);
        return i;
    }

    public game() {
        //        Player[0] = new player(false, TeamNS);
        TeamNS = new team();
        TeamEW = new team();
        Player[0] = new player(true, TeamNS); // FOR DEBUG ONLY!!!
        Player[1] = new player(true, TeamEW);
        Player[2] = new player(true, TeamNS);
        Player[3] = new player(true, TeamEW);
        Round = new round(Player, TeamNS, TeamEW);
    }

    // methods
    public static void main (String args[]) {
        game euchre = new game();

        // create players
//        for(int playerNumber=0; playerNumber<4; playerNumber++) {
//            team Team;
//            if(playerNumber%2 == 0) {
//                Team = euchre.TeamNS;
//            } else {
//                Team = euchre.TeamEW;
//            }
//            euchre.Player[playerNumber] = new player(bool, Team);
//        }

//        euchre.Player[0] = new player(true, euchre.TeamNS);
//        euchre.Player[0] = new player(false, euchre.TeamNS); // FOR DEBUG ONLY!!!
//        euchre.Player[1] = new player(false, euchre.TeamEW);
//        euchre.Player[2] = new player(false, euchre.TeamNS);
//        euchre.Player[3] = new player(false, euchre.TeamEW);

        // play a round with a random leader
        //(remember to subtract one to get the dealer)
        int i = euchre.Round.playRound(euchre.randomInt());
        // keep playing rounds until game over
        while(euchre.checkGameOver() == false) {
            euchre.Round = new round(euchre.Player, euchre.TeamNS, euchre.TeamEW);
            i = euchre.Round.playRound(i);
        }
    }
}
