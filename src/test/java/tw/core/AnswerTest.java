package tw.core;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import java.util.Arrays;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {
    private Answer answer;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp() {
        answer = new Answer();
    }

    @Test
    public void should_return_true_when_answer_is_valid() throws OutOfRangeAnswerException {
        answer = Answer.createAnswer("1 2 3 4");
        assertEquals("1 2 3 4",answer.toString());
    }

    @Test
    public void should_throw_exception_when_answer_contains_repeat_nunbers() throws OutOfRangeAnswerException {
        answer = Answer.createAnswer("1 1 3 4");
        thrown.expect(OutOfRangeAnswerException.class);
        thrown.expectMessage("Answer format is incorrect");
        answer.validate();
    }

    @Test
    public void should_throw_exception_when_answer_is_not_between_zero_and_nine() throws OutOfRangeAnswerException {
        answer = Answer.createAnswer("1 10 3 4");
        thrown.expect(OutOfRangeAnswerException.class);
        thrown.expectMessage("Answer format is incorrect");
        answer.validate();
    }

    @Test
    public void should_check_method_return_the_correct_record_has_three_right_but_position_different() throws Exception {
        Answer actualAnswer=Answer.createAnswer("1 2 3 4");
        answer=Answer.createAnswer("2 4 7 8");
        Record record=actualAnswer.check(answer);
        assertEquals(record.getValue()[0],0);
        assertEquals(record.getValue()[1],2);
    }
    @Test
    public void should_check_method_return_the_correct_record_is_all_right() throws Exception {
        Answer actualAnswer=Answer.createAnswer("1 2 3 4");
        answer=Answer.createAnswer("1 2 3 4");
        Record record=actualAnswer.check(answer);
        assertEquals(record.getValue()[0],4);
        assertEquals(record.getValue()[1],0);
    }
    @Test
    public void should_check_method_return_the_correct_record_is_all_wrong() throws Exception {
        Answer actualAnswer=Answer.createAnswer("1 2 3 4");
        answer=Answer.createAnswer("5 6 7 8");
        Record record=actualAnswer.check(answer);
        assertEquals(record.getValue()[0],0);
        assertEquals(record.getValue()[1],0);
    }



}
