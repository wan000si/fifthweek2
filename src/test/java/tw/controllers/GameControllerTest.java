package tw.controllers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.views.GameView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {
    private GameController gameController;
    private Game game;
    private Answer answer;
    private InputCommand guess;
    private AnswerGenerator answerGenerator;
    private GameView gameView;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws OutOfRangeAnswerException {
        Answer actualAnswer=Answer.createAnswer("1 2 3 4");
        answerGenerator = mock(AnswerGenerator.class);
        guess = mock(InputCommand.class);
        when(answerGenerator.generate()).thenReturn(actualAnswer);
        gameController = new GameController(new Game(answerGenerator),new GameView());
        System.setOut(new PrintStream(outContent));
        answer = new Answer();
    }

    @Test
    public void should_return_string_when_begin_the_game() throws IOException {
        gameController.beginGame();
        assertThat(outContent.toString(), CoreMatchers.startsWith("------Guess Number Game, You have 6 chances to guess!  ------"));
    }
}