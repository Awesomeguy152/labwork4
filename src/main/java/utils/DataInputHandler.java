package utils;

import java.util.Scanner;

public class DataInputHandler {
    private Scanner consoleScanner = new Scanner(System.in);
    private Scanner fileScanner;
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
    public void setFilePath(String path) {
        this.fileScanner = new Scanner(path);
    }
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
