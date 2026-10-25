import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreTests {

    @Test
    public void testCalculateScore() {
        Main main = new Main();

        int result = main.calculateScore(7);

        assertEquals(1, result);
    }

    @Test
    public void testCalculateFailedScore() {
        Main main = new Main();

        int result = main.calculateScore(4);

        assertEquals(-1, result);
    }

}