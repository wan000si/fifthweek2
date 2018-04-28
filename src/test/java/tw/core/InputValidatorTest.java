package tw.core;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.validator.InputValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {
    private InputValidator inputValidator;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp() {
        inputValidator = new InputValidator();
    }

    @Test
    public void should_return_true_when_input_is_valid() {
        assertTrue(inputValidator.validate("1 2 3 4"));
    }

    @Test
    public void should_return_false_when_input_has_less_numbers_than_four() {
        assertFalse(inputValidator.validate("1 2"));
    }

    @Test
    public void should_return_false_when_input_has_more_numbers_than_four() {
        assertFalse(inputValidator.validate("1 2 3 4 5"));
    }

    @Test
    public void should_return_false_when_input_contains_non_number() {
        thrown.expect(NumberFormatException.class);
        inputValidator.validate("1 d 3 4)");
//        assertFalse(inputValidator.validate("1 d 3 4"));
    }

    @Test
    public void should_return_false_when_input_nunbers_is_not_between_zero_and_nine() {
        assertFalse(inputValidator.validate("1 10 3 4"));
    }

    @Test
    public void should_return_false_when_input_contains_repeat_nunbers() {
        assertFalse(inputValidator.validate("1 1 3 4"));
    }



}
