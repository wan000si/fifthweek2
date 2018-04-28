package tw.core;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tw.core.generator.RandomIntGenerator;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    private RandomIntGenerator randomIntGenerator;
    @Rule
    public ExpectedException thrown= ExpectedException.none();


    @Before
    public void setUp() {
        randomIntGenerator = new RandomIntGenerator();
    }

    @Test
    public void shoule_throw_exception_when_digitmax_less_than_numbersOfNeed() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Can't ask for more numbers than are available");
        randomIntGenerator.generateNums(3, 4);
    }

    @Test
    public void shoule_return_the_needed_number_of_request() {
        String randNum = randomIntGenerator.generateNums(9, 4);
        assertEquals(4,randNum.split(" ").length);
    }

    @Test
    public void should_generate_non_repeatable_numbers() {
        String randNum = randomIntGenerator.generateNums(9, 4);
        assertEquals(4, Arrays.stream(randNum.split(" ")).distinct().count());
    }

    @Test
    public void should_generate_numbers_between_zero_and_nine() {
        String randNum = randomIntGenerator.generateNums(9, 4);
        assertTrue(Arrays.stream(randNum.split(" ")).allMatch(i->Integer.parseInt(i)<10));
    }

}