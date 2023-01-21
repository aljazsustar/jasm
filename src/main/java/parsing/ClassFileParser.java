package parsing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ClassFileParser {
    private BufferedReader bufferedReader;

    public ClassFileParser(String path) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("Razredne datoteke '%s' ni bilo mogoče najti.", path));
        }
        this.bufferedReader = new BufferedReader(fileReader);
    }
}
