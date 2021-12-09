import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ValidArrangementOfPairsTest {
    @Test
    void returnsValidArrangementOfPairs() {
        int[][] input = {
                {5,1},
                {4, 5},
                {11, 9},
                {9, 4}
        };
        int[][] expected = {
                {11, 9},
                {9, 4},
                {4, 5},
                {5,1}
        };

        var v = new ValidArrangementOfPairs();
        int[][] output = v.validArrangement(input);

        assertThat(output)
                .isEqualTo(expected);
    }
}