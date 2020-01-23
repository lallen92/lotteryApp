import com.rapttouch.lotteryApp.Main;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class lottoTest {

    Main main = new Main();

    public boolean validateInput(ArrayList<Integer> quickPickLine)
    {
        boolean actualStatus = true;
        final int minValue = 1;
        final int maxValue = 48;
        Set<Integer> set = new HashSet<Integer>(quickPickLine);

        if (set.size() == quickPickLine.size())
        {
            for (int element : quickPickLine)
            {
                if ((element < minValue) && (element > maxValue))
                {
                    actualStatus = false;
                    break;
                }
            }
        }
        else
        {
            actualStatus = false;
        }
        return actualStatus;
    }
    @Test
    public void selectOptionMultiplePicks()
    {
        int numberEntered = 1;
        assertEquals(numberEntered, Main.selectOption(numberEntered));
    }

    @Test
    public void selectOptionManualPick()
    {
        int numberEntered = 2;
        assertEquals(numberEntered, Main.selectOption(numberEntered));
    }

    @Test
    public void selectOptionQuit()
    {
        int numberEntered = 3;
        assertEquals(numberEntered, Main.selectOption(numberEntered));
    }

    @Test
    public void selectOptionNegativeNumber()
    {
        int numberEntered = -1;
        assertEquals(numberEntered, Main.selectOption(numberEntered));
    }

    @Test
    public void selectOptionGreaterThanOptions()
    {
        int numberEntered = 4;
        int expectedReturn = -1;
        assertEquals(expectedReturn, Main.selectOption(numberEntered));
    }
    @Test
    public void validSizeQuickPick()
    {
        int numberEntered = 1;
        int expectedSize = 4;
        assertEquals(expectedSize, (Main.makeYourPick(numberEntered).size()));
    }
    @Test
    public void validLine1SizeQuickPick()
    {
        int numberEntered = 1;
        int expectedSize = 6;
        assertEquals(expectedSize, (Main.makeYourPick(numberEntered).get(0).size()));
    }
    @Test
    public void validLine2SizeQuickPick()
    {
        int numberEntered = 1;
        int expectedSize = 6;
        assertEquals(expectedSize, (Main.makeYourPick(numberEntered).get(1).size()));
    }
    @Test
    public void validLine3SizeQuickPick()
    {
        int numberEntered = 1;
        int expectedSize = 6;
        assertEquals(expectedSize, (Main.makeYourPick(numberEntered).get(2).size()));

    }
    @Test
    public void validLine4SizeQuickPick()
    {
        int numberEntered = 1;
        int expectedSize = 6;
        assertEquals(expectedSize, (Main.makeYourPick(numberEntered).get(3).size()));
    }
    @Test
    public void validLine1ValidQuickPick()
    {
        int numberEntered = 1;
        boolean expectedStatus = true;
        boolean result;
        ArrayList<Integer> quickPickLine = Main.makeYourPick(numberEntered).get(0);
        result = validateInput(quickPickLine);
        assertEquals(expectedStatus, result);
    }
    @Test
    public void validLine2ValidQuickPick()
    {
        int numberEntered = 1;
        boolean expectedStatus = true;
        boolean result;
        ArrayList<Integer> quickPickLine = Main.makeYourPick(numberEntered).get(1);
        result = validateInput(quickPickLine);
        assertEquals(expectedStatus, result);
    }
    @Test
    public void validLine3ValidQuickPick()
    {
        int numberEntered = 1;
        boolean expectedStatus = true;
        boolean result;
        ArrayList<Integer> quickPickLine = Main.makeYourPick(numberEntered).get(2);
        result = validateInput(quickPickLine);
        assertEquals(expectedStatus, result);
    }
    @Test
    public void validLine4ValidQuickPick()
    {
        int numberEntered = 1;
        boolean expectedStatus = true;
        boolean result;
        ArrayList<Integer> quickPickLine = Main.makeYourPick(numberEntered).get(3);
        result = validateInput(quickPickLine);
        assertEquals(expectedStatus, result);
    }

    /*
    @Test
    public void validSizeManualPick() {
        int numberEntered = 2;
        int expectedSize = 1;
        assertEquals(expectedSize, (Main.makeYourPick(numberEntered).size()));
    }


    @Test
    public void validLengthExit() {
        int numberEntered = 3;
        int expectedSize = 0;
        assertEquals(expectedSize, (Main.makeYourPick(numberEntered).size()));
    }
    */
}


