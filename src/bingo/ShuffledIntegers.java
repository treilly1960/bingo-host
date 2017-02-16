/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

/**
 *
 * @author treilly
 */
public class ShuffledIntegers {
    
    private final int m_min;
    private final int m_max;
    private int m_lastIndex;
    private final int[] m_integers;
    private int bNumbersLeft, iNumbersLeft, nNumbersLeft, gNumbersLeft, oNumbersLeft;
    private boolean bRangeSelected, iRangeSelected, nRangeSelected, gRangeSelected, oRangeSelected;

    private void updateNumbersLeft(int lastNumberCalled) {
        
        if ((lastNumberCalled > 0) & (lastNumberCalled <= 15)) {
            if (bNumbersLeft > 0) {
                bNumbersLeft--;
            }
        }
        else if ((lastNumberCalled > 15) & (lastNumberCalled <= 30)) {
            if (iNumbersLeft > 0) {
                iNumbersLeft--;
            }
        }
        else if ((lastNumberCalled > 30) & (lastNumberCalled <= 45)) {
            if (nNumbersLeft > 0) {
                nNumbersLeft--;
            }
        }
        else if ((lastNumberCalled > 45) & (lastNumberCalled <= 60)) {
            if (gNumbersLeft > 0) {
                gNumbersLeft--;
            }
        }
        else if ((lastNumberCalled > 60) & (lastNumberCalled <= 75)) {
            if (oNumbersLeft > 0) {
                oNumbersLeft--;
            }
        }
    }
    
    private boolean numberInColumn(int num) {
        return ((bRangeSelected && ((num > 0) & (num <= 15))) |
                (iRangeSelected && ((num > 15) & (num <= 30))) |
                (nRangeSelected && ((num > 30) & (num <= 45))) |
                (gRangeSelected && ((num > 45) & (num <= 60))) |
                (oRangeSelected && ((num > 60) & (num <= 75))));
    }
    
    public ShuffledIntegers(int min, int max) {
        
        m_min = min;
        m_max = max;
        m_integers = new int[75];
        
        for (int i=min; i<=max; i++) {
            m_integers[i-1] = i;
        }
        
        m_lastIndex = max - min;
        
        bNumbersLeft = 15;
        iNumbersLeft = 15;
        nNumbersLeft = 15;
        gNumbersLeft = 15;
        oNumbersLeft = 15;
        
    }
    
    public int nextInteger(boolean bRange, boolean iRange, boolean nRange, boolean gRange, boolean oRange) {
        
        bRangeSelected = bRange;
        iRangeSelected = iRange;
        nRangeSelected = nRange;
        gRangeSelected = gRange;
        oRangeSelected = oRange;
        
        int rtn = 0;
        
        if (!bRange) {
            bNumbersLeft = 0;
        }

        if (!iRange) {
            iNumbersLeft = 0;
        }

        if (!nRange) {
            nNumbersLeft = 0;
        }

        if (!gRange) {
            gNumbersLeft = 0;
        }

        if (!oRange) {
            oNumbersLeft = 0;
        }

        do {
            if (m_lastIndex >= 0) {
            int b = (int)Math.floor((Math.random() * m_lastIndex));
            int temp = m_integers[m_lastIndex];
            rtn = m_integers[b];
            m_integers[m_lastIndex] = m_integers[b];
            m_integers[b] = temp;
            m_lastIndex--;
            updateNumbersLeft(rtn);
            }
            else
            {
                rtn = m_integers[0];
            }
        } while (!numberInColumn(rtn));
        return rtn;
    }
    
    public void reShuffle() {
        
        m_lastIndex = m_max - m_min;
        
        bNumbersLeft = 15;
        iNumbersLeft = 15;
        nNumbersLeft = 15;
        gNumbersLeft = 15;
        oNumbersLeft = 15;
}
    
    public boolean done() {
        
        return ((bNumbersLeft + iNumbersLeft + nNumbersLeft + gNumbersLeft + oNumbersLeft) < 1);
    }
}
