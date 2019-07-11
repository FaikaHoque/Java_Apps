package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {
    /**
     * workflow
     */
    void process();

    /**
     * Traverese a given directory and return all files
     * @param rootDir input directory
     * @return files under the rootDir
     */
    List<File> listFiles(String rootDir);
    /**
     * Read a file and return all the lines
     * Explain FIleReader, BufferedReader, and character encoding
     *
     * @param inputFile file to be read
     * @return lines
     * @throws IllegalArgumentException if a given inputFile is not a file
     */
    List <String> readLines(File inputFile) throws IOException;
    /**
     * check if a line contains the reges patteren (passed by user)
     * @param line input string
     * @return true if there is a match
     */
    boolean containsPattern(String line);
    /**
     * write lines to a file
     *
     * Explore: FileOutputStream, OutputStreamWriter and Bufferedwritter
     *
     * @param lines matched line
     * @throws IOException if write failed
     */
    void writeToFile(List<String> lines) throws IOException;



    String getRootDir();
    void setRootDir(String rootDir);

    String getRegex();
    void setRegex(String regex);

    String getOutFile();
    void setOutFile(String outFile);

}
