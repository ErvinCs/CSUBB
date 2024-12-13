package scanner;

import exceptions.LexicalError;
import exceptions.SyntaxError;
import scanner.auxiliary.*;
import scanner.enums.*;

import java.io.*;
import java.util.*;

public class CodeScanner {

    private File inputFile;
    private File outputFile;
    private BufferedWriter writer;
    private ProgramInternalForm pif;
    private SymbolTable<String, Integer> st;
    private Integer stPosition;

    /**
     * Creates a Scanner that analyses the code at inputFilePath and produces as output its PIF & ST at outputFilePath
     * @param inputFilePath
     * @param outputFilePath
     * @throws IOException if the output file does not exist
     */
    public CodeScanner(String inputFilePath, String outputFilePath) throws IOException {
        this.inputFile = new File(inputFilePath);
        this.outputFile = new File(outputFilePath);
        this.writer = new BufferedWriter(new FileWriter(outputFilePath));
        this.pif = new ProgramInternalForm();
        this.st = new SymbolTable<>();
        this.stPosition = 0;
    }

    /**
     * Produces the PIF & ST
     * @throws LexicalError
     * @throws SyntaxError
     * @throws IOException
     */
    public void codify() throws LexicalError, SyntaxError, IOException {
        this.checkBrackets();

        Scanner lineReader = new Scanner(this.inputFile);
        String line = lineReader.nextLine().trim();
        pif.append(Reserved.isReserved(line), -1);
        if (!line.equals("beginp"))
            throw new LexicalError("At line<1>: No beginp statement found");

        Integer stIndex = 0;
        int lineIndex = 1;
        List<String> atoms = new ArrayList<>();
        String atom;
        do {
            if (atoms.isEmpty()) {
                line = lineReader.nextLine().trim();
                while(line.isEmpty())
                    line = lineReader.nextLine().trim();
                for (String s : line.split("\\s+")) {
                    atoms.add(s.trim());
                }
                lineIndex++;
            }
            atom = atoms.get(0);

            if (Reserved.isReserved(atom) != -1) {
                pif.append(Reserved.isReserved(atom), -1);
                if(atom.equals("endp"))
                    break;
            } else if (Bool.isBool(atom) != -1) {
                pif.append(Bool.isBool(atom), -1);
            } else if (Operator.isOperator(atom) != -1) {
                pif.append(Operator.isOperator(atom), -1);
            } else if (Separator.isSeparator(atom) != -1){
                pif.append(Separator.isSeparator(atom), -1);
            } else if (atom.matches(Pattern.Identifier.getRegex())) {
                stIndex = st.get(atom);
                if (stIndex == null) {
                    stIndex = stPosition;
                    st.add(atom, stIndex);
                    stPosition++;
                }
                pif.append(1, stIndex);
            } else if (atom.matches(Pattern.Constant.getRegex())) {
                stIndex = st.get(atom);
                if (stIndex == null) {
                    stIndex = stPosition;
                    st.add(atom, stIndex);
                    stPosition++;
                }
                pif.append(2, stIndex);
            } else
                throw new LexicalError("At Line: <" + lineIndex + ">.Cannot identify atom: <" + atom + ">");
            atoms.remove(0);
        } while(lineReader.hasNext());
        if (!atom.equals("endp"))
            throw new LexicalError("No endp statement found");
        this.writeFile();
        this.writer.close();
        lineReader.close();
    }

    //@TODO Check for missing ;
    //@TODO Lookahead and/or behind - if then else - while do done
    //@TODO Check for <data_type> <identifier> - missing data type or missing identifier
    //@TODO Lookahead and/or behind - operands


    private void writeFile() throws IOException {
        writer.write(this.pif.toString() + "\n");
        writer.write(this.st.toString());
        writer.flush();
    }

    /**
     * Checks if the scopes of the program are correctly defined
     * @return boolean
     * @throws IOException
     * @throws SyntaxError
     */
    private boolean checkBrackets() throws IOException, SyntaxError{
        Stack<String> bracketStack = new Stack<>();
        Stack<Integer> bracketPosStack = new Stack<>();
        Scanner lineReader = new Scanner(this.inputFile);
        int lineIndex = 0;
        while(lineReader.hasNext()) {
            lineIndex++;
            String line = lineReader.nextLine().trim();
            String[] tokens = line.split(" ");
            for(String token : tokens) {
                if (Separator.isOpenBracket(token) != -1) {
                    bracketStack.push(token);
                    bracketPosStack.push(lineIndex);
                }
                else if (Separator.isClosedBracket(token) != -1) {
                    if (bracketStack.isEmpty())
                        throw new SyntaxError("At line <" + lineIndex + ">.Invalid scope.");
                    if (token.equals(Separator.CLOSED_B.getName()) && bracketStack.peek().equals(Separator.OPEN_B.getName())) {
                        bracketStack.pop();
                        bracketPosStack.pop();
                    }
                    else if (token.equals(Separator.CLOSED_P.getName()) && bracketStack.peek().equals(Separator.OPEN_P.getName())) {
                        bracketStack.pop();
                        bracketPosStack.pop();
                    }
                    else if (token.equals(Separator.CLOSED_C.getName()) && bracketStack.peek().equals(Separator.OPEN_C.getName())) {
                        bracketStack.pop();
                        bracketPosStack.pop();
                    }
                    else {
                        lineReader.close();
                        throw new SyntaxError("At line <" + lineIndex + ">.Invalid scope.");
                    }

                }
            }
        }
        if (!bracketStack.isEmpty()) {
            lineReader.close();
            throw new SyntaxError("At line <" + bracketPosStack.peek() + ">.Invalid scope.");
        }
        lineReader.close();
        return true;
    }

    public ProgramInternalForm getPif() {
        return pif;
    }

    public SymbolTable<String, Integer> getSt() {
        return st;
    }

}
