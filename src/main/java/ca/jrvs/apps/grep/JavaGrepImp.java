package ca.jrvs.apps.grep;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaGrepImp implements JavaGrep {

    private String rootDir;
    private String regex;
    private String outFile;

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public String getRegex() {
        return regex;
    }

    @Override
    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getOutFile() {
        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {
        this.outFile = outFile;
    }

    /**
     *
     * @param rootDir input directory
     * @return list of files from root directory, sub directory
     */

    public List<File> listFiles(String rootDir) {

        List<File> inFileslist = new ArrayList();

        File[] filename;

        try {

            File file = new File(rootDir);

            filename = file.listFiles();

            for (int i = 0; i < filename.length; i++) {

                if (filename[i].isDirectory() == true) {

                    String subdirectory = filename[i].getPath();

                    inFileslist.addAll(listFiles(subdirectory));
                }
                else {

                    inFileslist.add(filename[i]);
                }

            }
        }

        catch (Exception e) {
            System.out.println(e);
        }
        return inFileslist;
    }

    public List<String> readLines(File inputFile){

        List<String> lines = new ArrayList();

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while(line !=null)
            {
                lines.add(line);
                line = reader.readLine();
            }

        }

        catch (IOException e)
        {
            System.out.println(e);
        }
        return lines;


    }

    @Override
    public boolean containsPattern(String line) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        return matcher.find();
    }

    public void writeToFile(List<String> lines)
    {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

            for (int i=0; i<lines.size(); i++)
            {
                String outputLine =  lines.get(i);
                writer.write(outputLine);
                // System.out.println(outputLine);
            }
            writer.close();


        }
        catch (IOException e)
        {
            System.out.println(e);
        }

    }

    public void process()
    {
        List <String> matchedLines = new ArrayList<>();

        for (File file : listFiles(rootDir))
        {
            for (String line : readLines(file))
            {
                if (containsPattern(line))
                {
                    matchedLines.add(line);
                }
            }
        }
        writeToFile(matchedLines);
    }

    public static void main(String[] arg)
    {
        if (arg.length !=3)
        {
            throw new IllegalArgumentException("USAGE: regex rootPath outFile");
        }

        JavaGrepImp javaGrepImp = new JavaGrepImp();

        javaGrepImp.setRegex(arg[0]);
        javaGrepImp.setRootDir(arg[1]);
        javaGrepImp.setOutFile(arg [2]);
        try {
            javaGrepImp.process();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
