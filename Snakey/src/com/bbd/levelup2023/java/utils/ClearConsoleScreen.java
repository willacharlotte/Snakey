package com.bbd.levelup2023.java.utils;

public class ClearConsoleScreen {
    public static void clearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name");

            if (operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
            else{
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
