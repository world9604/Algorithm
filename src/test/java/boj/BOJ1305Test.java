package boj;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BOJ1305Test {
    private SplitConquester solution;

    @Before
    public void setUp() throws Exception {
        solution = new SplitConquester(5, 5);
    }

    @Test
    public void solution() {
        solution.solve(6, 4, 5);
        solution.solve(5, 4, 5);
    }
}