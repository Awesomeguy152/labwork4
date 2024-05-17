package app.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataInputHandler {
    private Scanner consoleScanner = new Scanner(System.in);
    private Scanner fileScanner;
    private String filePath;
    private Scanner currentScanner = consoleScanner;
    private InputMode currentInputMode = InputMode.CONSOLE;
    public void setCurrentInputMode(InputMode currentInputMode) {
        this.currentInputMode = currentInputMode;
        switch (currentInputMode){
            case FILE -> {
                currentScanner = fileScanner;
            }
            case CONSOLE -> {
                currentScanner = consoleScanner;
            }
        }
    }
    public InputMode getCurrentInputMode() {
        return currentInputMode;
    }
    public void setFilePath(String path) throws FileNotFoundException {
        this.filePath = path;
        this.fileScanner = new Scanner(new File(path));
    }
    public String getFilePath(){return filePath;}
    public String nextLine(){
        return currentScanner.nextLine();
    }
    public boolean hasNext(){
        return currentScanner.hasNext();
    }
    public enum InputMode{
        CONSOLE,
        FILE
    }
}
