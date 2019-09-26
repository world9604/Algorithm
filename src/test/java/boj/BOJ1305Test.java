package boj;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BOJ1305Test {
    private Solution solution;

    @Before
    public void setUp() throws Exception {
        solution = new Solution();
    }

    @Test
    public void solution() {
        int length = solution.solve(6, "aabaaa");
        assertThat(length, is(4));

        int length2 = solution.solve(5, "aaaaa");
        assertThat(length2, is(1));
    }
}