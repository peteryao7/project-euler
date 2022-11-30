import java.util.*;

class p084 {
    /*
     * https://projecteuler.net/problem=84
     * 
     * In the game, Monopoly, the standard board is set up in the following way:
     * 
     * GO  A1  CC1  A2  T1  R1  B1  CH1  B2  B3  JAIL
     * H2                                        C1
     * T2                                        U1
     * H1                                        C2
     * CH3                                       C3
     * R4                                        R2
     * G3                                        D1
     * CC3                                       CC2
     * G2                                        D2
     * G1                                        D3
     * G2J  F3  U2  F2  F1  R3  E3  E2  CH2  E1  FP
     * 
     * A player starts on the GO square and adds the scores on two 6-sided dice to
     * determine the number of squares they advance in a clockwise direction.
     * Without any further rules we would expect to visit each square with equal
     * probability: 2.5%. However, landing on G2J (Go To Jail), CC (community
     * chest), and CH (chance) changes this distribution.
     * 
     * In addition to G2J, and one card from each of CC and CH, that orders the
     * player to go directly to jail, if a player rolls three consecutive doubles,
     * they do not advance the result of their 3rd roll. Instead they proceed
     * directly to jail.
     * 
     * At the beginning of the game, the CC and CH cards are shuffled. When a player
     * lands on CC or CH they take a card from the top of the respective pile and,
     * after following the instructions, it is returned to the bottom of the pile.
     * There are sixteen cards in each pile, but for the purpose of this problem we
     * are only concerned with cards that order a movement; any instruction not
     * concerned with movement will be ignored and the player will remain on the
     * CC/CH square.
     * 
     * - Community Chest (2/16 cards):
     *   1. Advance to GO
     *   2. Go to JAIL
     * - Chance (10/16 cards):
     *   1. Advance to GO
     *   2. Go to JAIL
     *   3. Go to C1
     *   4. Go to E3
     *   5. Go to H2
     *   6. Go to R1
     *   7. Go to next R (railway company)
     *   8. Go to next R
     *   9. Go to next U (utility company)
     *   10. Go back 3 squares.
     * 
     * The heart of this problem concerns the likelihood of visiting a particular
     * square. That is, the probability of finishing at that square after a roll.
     * For this reason it should be clear that, with the exception of G2J for which
     * the probability of finishing on it is zero, the CH squares will have the
     * lowest probabilities, as 5/8 request a movement to another square, and it is
     * the final square that the player finishes at on each roll that we are
     * interested in. We shall make no distinction between "Just Visiting" and being
     * sent to JAIL, and we shall also ignore the rule about requiring a double to
     * "get out of jail", assuming that they pay to get out on their next turn.
     * 
     * By starting at GO and numbering the squares sequentially from 00 to 39 we can
     * concatenate these two-digit numbers to produce strings that correspond with
     * sets of squares.
     * 
     * Statistically it can be shown that the three most popular squares, in order,
     * are JAIL (6.24%) = Square 10, E3 (3.18%) = Square 24, and GO (3.09%) = Square
     * 00. So these three most popular squares can be listed with the six-digit
     * modal string: 102400.
     * 
     * If, instead of using two 6-sided dice, two 4-sided dice are used, find the
     * six-digit modal string.
     * 
     * -----
     * 
     * Markov Chain/Simulation:
     * Simulate a Monopoly game and keep track of the number of times each
     * space is landed on.
     */
    public static String getThreeMostLikelyMonopolySquares(int die, int turns) {
        Random rand = new Random();
        Deck commDeck = new Deck(16);
        Deck chanceDeck = new Deck(16);
        int loc = 0;
        int consecutiveDoubles = 0;
        int[] locs = new int[40];
        
        for (int i = 0; i < turns; i++) {
            int roll1 = rand.nextInt(4) + 1;
            int roll2 = rand.nextInt(4) + 1;
            if (roll1 == roll2)
                consecutiveDoubles++;
            else
                consecutiveDoubles = 0;
            
            // go to jail
            if (consecutiveDoubles == 3) {
                loc = 30;
                consecutiveDoubles = 0;
            }
            else {
                loc = (loc + roll1 + roll2) % 40;
            }

            // do the action on the landed space
            switch (loc) {
                // community chest spaces
                case 2:
                case 17:
                case 33:
                    switch (commDeck.drawCard()) {
                        case 0:
                            loc = 0;
                            break;
                        case 1:
                            loc = 10;
                            break;
                    }
                    break;
                // chance spaces 
                case 7:
                case 22:
                case 36:
                    switch (chanceDeck.drawCard()) {
                        case 0:
                            loc = 0;
                            break;
                        case 1:
                            loc = 10;
                            break;
                        case 2:
                            loc = 11;
                            break;
                        case 3:
                            loc = 24;
                            break;
                        case 4:
                            loc = 39;
                            break;
                        case 5:
                            loc = 5;
                            break;
                        case 6:
                        case 7:
                            if (loc == 7)
                                loc = 15;
                            if (loc == 22)
                                loc = 25;
                            if (loc == 36)
                                loc = 5;
                            break;
                        case 8:
                            if (loc == 22)
                                loc = 28;
                            else
                                loc = 12;
                            break;
                        case 9:
                            loc -= 3;
                            break;
                    }
                    break;
                // G2J
                case 30:
                    loc = 10;
                    break;
                // none of the other spaces affect movement
                default:
                    break;
            }

            locs[loc]++;
        }

        String res = new String();

        // get the top 3 most landed on spaces (index)
        // bit shift left the original values and put their index in the LSB
        // then do AND to remove freq values as we only need the space indices
        for (int i = 0; i < locs.length; i++) {
            locs[i] = ~locs[i] << 6 | i; // 6 digits fit 40 indices
        }
        Arrays.sort(locs);

        for (int i = 0; i < 3; i++) {
            res += String.format("%02d", locs[i] & 0x3F);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(getThreeMostLikelyMonopolySquares(4, 10000000));
    }
}

class Deck {
    static int size;
    static Set<Integer> deck;

    public Deck(int _size) {
        size = _size;
        createNewDeck();
    }

    public Integer drawCard() {
        if (deck.isEmpty()) {
            createNewDeck();
        }
        Integer val = deck.iterator().next();
        deck.remove(val);
        return val;
    }

    private static void createNewDeck() {
        deck = new HashSet<>();
        for (int i = 0; i < size; i++) {
            deck.add(i);
        }
    }
}