import java.io.File;
import java.util.Scanner;

public class FileReader {
    String fileName;
    CNF cnf;

    FileReader(String name, CNF cnf) {
        fileName = name;
        this.cnf = cnf;
    }


    void readFile(String fileName) {
        String[] header = {"TERMINAL", "RULES", "START", ""};
        int counter = 0;
        try {
            File machineFile = new File(fileName);
            Scanner fileReader = new Scanner(machineFile);
            fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (!line.equals(header[counter])) {
                    headerChecker(counter, line);
                } else {
                    counter++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void headerChecker(int counter, String line) {
        if (counter == 0) {
            cnf.getNon_term().add(line);
        }
        if (counter == 1) {
            cnf.getTerminal().add(line);
        }
        if (counter == 2) {
            cnf.getRules().add(line);
        }
        if (counter == 3) {
            cnf.setStart(line);
        }

    }

    String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
