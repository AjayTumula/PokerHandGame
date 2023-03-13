import org.example.Card;
import org.example.PokerHands;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerHandTest {

    @Test
    public void testCreateCard() throws Exception {
        String input = "KD";
        Card card = new Card(input);
        assertEquals(13, card.getValue());
        assertEquals('D', card.getSuit());
    }

    @Test
    public void testIsPair() throws Exception {
        String content  = "Black: 2H 3D 6S KC KD  White: 2C 3H 4S 8C AH";
        PokerHands pokerHands = new PokerHands(content);
        pokerHands.judgeHands();
        assertEquals(1,pokerHands.player1.getCombination());
        assertEquals(13,pokerHands.player1.getHighCard());
    }

    @Test
    public void testIsTwoPair() throws Exception {
        String content = "Black: 6H 5D 6S KC KD  White: 2C 3H 4S 8C AH";
        PokerHands pokerHands = new PokerHands(content);
        pokerHands.judgeHands();
        assertEquals(2,pokerHands.player1.getCombination());
        assertEquals(13,pokerHands.player1.getHighCard());
    }

    @Test
    public void testIsThreeOfKind() throws Exception {
        String content = "Black: 2H 5D KS KC KD  White: 2C 3H 4S 8C AH";
        PokerHands pokerHands = new PokerHands(content);
        pokerHands.judgeHands();
        assertEquals(3,pokerHands.player1.getCombination());
        assertEquals(13,pokerHands.player1.getHighCard());
    }

    @Test
    public void testIsFourOfAKind() throws Exception {
        String content = "Black: 3S AH AC AS AD  White: 2C 3H 4S 8C KH";
        PokerHands pokerHands = new PokerHands(content);
        pokerHands.judgeHands();
        assertEquals(7,pokerHands.player1.getCombination());
        assertEquals(14,pokerHands.player1.getHighCard());
    }

    @Test
    public void testGetWinner() throws Exception {
        String content = "Black: 2D 3D 4D 5D 6D  White: 2C 3H 4S 8C KH";
        PokerHands pokerHands = new PokerHands(content);
        pokerHands.judgeHands();
        assertEquals("Black wins. - with high card: 5",pokerHands.getWinner());
    }
}

