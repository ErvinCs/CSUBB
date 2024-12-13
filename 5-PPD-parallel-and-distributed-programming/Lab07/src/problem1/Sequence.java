package problem1;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    private List<Integer> numbers;

    public Sequence() {
        this.numbers = new ArrayList<>();
    }

    public Sequence(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Sequence(int size) {
        this.numbers = generateNumbers(size);
    }

    public void addNumber(Integer number) {
        this.numbers.add(number);
    }

    public Integer getNumberAt(int index) {
        return this.numbers.get(index);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getSize() {
        return this.numbers.size();
    }


    private List<Integer> generateNumbers(int size) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            list.add((int) (Math.random() * 9 + 1));
        }
        return list;
    }

    @Override
    public String toString() {
        String output = "Sequence: ";
        for(Integer n : this.numbers)
            output += n + " ";
        return output;
    }
}
