package idea;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RabinKarpTest {

    private RabinKarp soloution;

    @Before
    public void setUp() {
        soloution = new RabinKarp();
    }

    @Test
    public void solve() {
        String parent = "ababacabacaabacaaba";
        String pattern = "abacaaba";
        List<Integer> finedList = soloution.solve(parent, pattern);
        for (Integer finedIndex : finedList) {
            assertThat(finedIndex, is(6));
        }
    }

}