package problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BigNumber {
    private List<Integer> digits;

    public void generateDigits(int size) {
        for(int i = 0; i < size; i++)
            digits.add((int) (Math.random() * 9 + 1));
    }

    public BigNumber() {
        this.digits = new ArrayList<>();
    }

    public BigNumber(List<Integer> digits) {
        this.digits = digits;
    }

    public int getNumberOfDigits() {
        return digits.size();
    }

    public List<Integer> getDigits() {
        return digits;
    }

    public int getDigitAt(int position) {
        return digits.get(position);
    }

    public void addDigit(Integer digit) {
        this.digits.add(digit);
    }

    public boolean greaterThan(BigNumber other) {
        return this.digits.size() >= other.digits.size();
    }

    //Kind of expensive but it's kind of late to change it now
    public void fillZeros(BigNumber greater) {
        int difference = greater.digits.size() - this.digits.size();
        List<Integer> newDigits = new ArrayList<>();
        for(int i = 0; i < difference; i++)
            newDigits.add(0);
        for(int i = 0; i < this.getNumberOfDigits(); i++)
            newDigits.add(this.digits.get(i));
        this.digits = newDigits;
    }

    public void reverseDigits() {
        Collections.reverse(this.digits);
    }

    @Override
    public String toString() {
        String output = "Number: ";
        for(int i = 0; i < this.getNumberOfDigits(); i++)
            output += digits.get(i).toString();
        return output;
    }
}
