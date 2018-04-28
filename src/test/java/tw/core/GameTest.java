package tw.core;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.model.GuessResult;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static tw.core.GameStatus.CONTINUE;
import static tw.core.GameStatus.FAIL;
import static tw.core.GameStatus.SUCCESS;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    private Game game;

    @Before
    public void Setup() throws OutOfRangeAnswerException {
        Answer actualAnswer = Answer.createAnswer("1 2 3 4");
        AnswerGenerator answerGenerator = mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);
        game = new Game(answerGenerator);
    }

    @Test
    public void should_fail_game_when_guess_six_times_and_still_wrong()
    {
        Answer inputAnswer = Answer.createAnswer("5 6 7 8");
        for (int i = 0; i < 5; i++)
        {
            game.guess(inputAnswer);
        }
        assertEquals(game.checkStatus(),CONTINUE);
        game.guess(inputAnswer);
        assertEquals(game.checkStatus(),FAIL);
    }

    @Test
    public void should_record_guess_history()
    {

        Answer inputAnswer1 = Answer.createAnswer("5 6 7 8");
        game.guess(inputAnswer1);
        Answer inputAnswer2 = Answer.createAnswer("4 5 6 7");
        game.guess(inputAnswer2);
        List<GuessResult> guessResults = game.guessHistory();
        assertThat(guessResults.size() == 2, CoreMatchers.is(true));
        assertEquals(inputAnswer1.toString(), guessResults.get(0).getInputAnswer().toString());
        assertEquals("0A0B", guessResults.get(0).getResult());
        assertEquals(inputAnswer2.toString(), guessResults.get(1).getInputAnswer().toString());
        assertEquals("0A1B", guessResults.get(1).getResult());
    }

    @Test
    public void should_return_0A0B_when_no_number_is_correct()
    {
        Answer inputAnswer = Answer.createAnswer("5 6 7 8");
        assertEquals("0A0B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_return_0A2B_when_two_numbers_are_correct_but_positions_are_not_correct()
    {
        Answer inputAnswer = Answer.createAnswer("3 4 5 6");
        assertEquals("0A2B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_return_1A0B_when_one_number_is_correct_and_position_is_correct_too()
    {
        Answer inputAnswer = Answer.createAnswer("1 5 6 7");
        assertEquals("1A0B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_return_2A2B_when_two_numbers_are_pisition_correct_and_two_are_nunmber_correct()
    {
        Answer inputAnswer = Answer.createAnswer("1 2 4 3");
        assertEquals("2A2B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_return_4A0B_when_all_numbers_are_pisition_correct()
    {
        Answer inputAnswer = Answer.createAnswer("1 2 3 4");
        assertEquals("4A0B",game.guess(inputAnswer).getResult());
    }

    @Test
    public void should_win_when_guess_correct()
    {
        Answer inputAnswer = Answer.createAnswer("1 2 3 4");
        assertTrue(game.checkCoutinue());
    }
}
