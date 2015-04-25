/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

/**
 *
 * @author reillyt
 */
public class BingoNumber {
    private int bingoNumber;
    private NumberCallState callState;
    
    public BingoNumber() {
        bingoNumber = 0;
        callState = NumberCallState.NotCalled;
    }

    public BingoNumber(int number) {
        bingoNumber = number;
        callState = NumberCallState.NotCalled;
    }

    public BingoNumber(int number, NumberCallState state) {
        bingoNumber = number;
        callState = state;
    }
    
    public void setBingoNumber(int number) {
        bingoNumber = number;
    }

    public int getBingoNumber() {
        return bingoNumber;
    }

    public void setCallState(NumberCallState state) {
        callState = state;
    }

    public NumberCallState getCallState() {
        return callState;
    }
}
