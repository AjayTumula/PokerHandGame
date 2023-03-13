import org.example.Card;
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

}

