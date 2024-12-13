package scanner;

import scanner.auxiliary.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Keeps track of all the atoms in the program, containing their code and position in the symbol table.
 * Wraps a list of Pair<Integer, Integer> where:
 *  The first Integer represents the code of an element in the program (see: Codification table)
 *  The second Integer represents it's position in the symbol table. If the element is not an identifier or constant this takes -1 as its value.
 */
public class ProgramInternalForm {

    private List<Pair<Integer, Integer>> atoms;

    public ProgramInternalForm() {
        this.atoms = new ArrayList<Pair<Integer, Integer>>();
    }

    public void append(Integer code, Integer position) {
        this.atoms.add(new Pair<Integer, Integer>(code, position));
    }

    public List<Pair<Integer, Integer>> getAtoms() {
        return atoms;
    }

    @Override
    public String toString() {
        String output = "PIF:\n";
        for(Pair<Integer, Integer> pair : atoms) {
            output += pair.toString() + "\n";
        }
        return output;
    }
}
