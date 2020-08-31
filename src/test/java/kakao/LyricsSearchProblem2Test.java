package kakao;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LyricsSearchProblem2Test {

    @Test
    private void solutionTest(){
        String[] words = {"frodo", "?????", "front", "frost", "frozen", "frame", "kakao"};
        String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
        int[] answers = {4, 3, 5, 1, 0};

        LyricsSearchProblem2 problem = new LyricsSearchProblem2();
        int[] expectedAnswers = problem.solution(words, queries);
        for (int i = 0; i < answers.length; i++) {
            assertThat(answers[i], is(expectedAnswers[i]));
        }
    }
}
