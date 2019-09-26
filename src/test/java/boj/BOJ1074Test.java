package boj;

import org.junit.Test;
import static org.junit.Assert.*;

public class BOJ1074Test {
    private SplitConquester splitConquester;

    @Test
    public void setUp() {
        splitConquester = new SplitConquester(3, 1);
        splitConquester.solve((int)Math.pow(2, 2), 0, 0);

        splitConquester = new SplitConquester(7, 7);
        splitConquester.solve((int)Math.pow(2, 3), 0, 0);
    }
}