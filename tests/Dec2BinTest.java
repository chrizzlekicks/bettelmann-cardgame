import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class Dec2BinTest {

    @Test
    void convert() {
        /* Is stack clear before each run? */
        Dec2Bin clearStack = new Dec2Bin();
        clearStack.convert(4);
        clearStack.convert(128); // length == 8
        int stackSize = clearStack.binStack.size();
        assertEquals(8, stackSize, "Stack was not cleared properly");

        /* Is the input N = 0 */
        Dec2Bin testNull = new Dec2Bin();
        testNull.convert(0);
        assertEquals(0, testNull.binStack.pop(), "0 was not converted properly");

        /* Test stack for a variation of numbers */
        int[] decTestNum = {50, 128, 4};
        int[][] binTestNum = {{1, 1, 0, 0, 1, 0}, {1, 0, 0, 0, 0, 0, 0, 0}, {1, 0, 0}};
        int i = 0;
        while (i < decTestNum.length) {
            Dec2Bin testStack = new Dec2Bin();
            testStack.convert(decTestNum[i]);
            // Test if input N is saved correctly
            assertEquals(decTestNum[i], testStack.getN(), "N is not saved correctly as attribute");
            // Test if stack size equals expected binary array output
            assertEquals(binTestNum[i].length, testStack.binStack.size(), "Stack size does not match binary output length");
            // Test if numbers from modulo op are stored correctly in the stack
            for (int j = 0; j < binTestNum[i].length; j++) {
                assertEquals(binTestNum[i][j], testStack.binStack.pop(), "Conversion does not seem to be correct");
            }
            i++;
        }
    }

    @Test
    void testToString() {
        int[] moreTestNum = {50, 4, 128};
        String[] binMoreTestNum = {"110010", "100", "10000000"};
        int i = 0;
        while (i < moreTestNum.length) {
            Dec2Bin testStack = new Dec2Bin();
            testStack.convert(moreTestNum[i]);
            Stack<Integer> testStackCopy = (Stack<Integer>) testStack.binStack.clone();
            String testString = testStack.toString();
            // Test if the string output matches
            assertEquals(binMoreTestNum[i], testString, "The output of the string is not correct");
            // Test if the stack was changed during the process
            assertEquals(testStackCopy.size(), testStack.binStack.size(), "The stack was modified");
            i++;
        }
    }
}