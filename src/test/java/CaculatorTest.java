import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CaculatorTest {

    @Test
    public void test_add() {
        Calculator calculator = new Calculator();
        int sum = calculator.add(1, 3);
        assertThat(sum, is(4));
    }
}
