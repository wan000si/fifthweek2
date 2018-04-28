package tw.core.generator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.Answer;
import tw.core.exception.OutOfRangeAnswerException;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    private RandomIntGenerator randomIntGenerator;
    private AnswerGenerator answerGenerator;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp() {
        randomIntGenerator = mock(RandomIntGenerator.class);
        answerGenerator = new AnswerGenerator(randomIntGenerator);
    }

    @Test
    public void should_return_true_when_answer_is_valid() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 4");
        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);
        Answer answer = answerGenerator.generate();
        assertTrue(answer.toString().equals("1 2 3 4"));
    }

    @Test
    public void should_throw_exception_when_answer_contains_repeat_nunbers() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 1 2 3");
        thrown.expect(OutOfRangeAnswerException.class);
        thrown.expectMessage("Answer format is incorrect");
        answerGenerator.generate();
    }

    @Test
    public void should_throw_exception_when_answer_nunbers_is_not_between_zero_and_nine() throws OutOfRangeAnswerException {
        when(randomIntGenerator.generateNums(9, 4)).thenReturn("1 2 3 10");
        thrown.expect(OutOfRangeAnswerException.class);
        thrown.expectMessage("Answer format is incorrect");
        answerGenerator.generate();
    }
}

