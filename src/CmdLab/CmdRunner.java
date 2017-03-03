package CmdLab;

import java.io.*;
import java.util.List;

/**
 * Run Shell Cmd
 * Created by weizhaoy on 17/1/17.
 */
public class CmdRunner {

    private List<File> seedFiles; //to replace cmd placeholder

    private String placeholder; //placeholder pattern in command line, such as "${seed}" todo: maybe more than one?
    private String replacement; //string to replace the placeholder, such as a concrete seed path
    private String binStr;  //target binary (path)
    private String argStr;  //command line arguments to the target binary

    private String finalCmd; //final cmd after replacing

    private Process proc = null;


    public CmdRunner(String binStr, String argStr, String placeholder, String replacement) {
        this.binStr = binStr;
        this.argStr = argStr;
        this.placeholder = placeholder;
        this.replacement = replacement;
    }

    public void reset(String binStr, String argStr, String placeholder, String replacement) {
        this.binStr = binStr;
        this.argStr = argStr;
        this.placeholder = placeholder;
        this.replacement = replacement;
    }

    /**
     * @param binStr
     * @param argStr
     * @param placeholder
     * @param replacement
     * @return final cmd to execute
     */
    public static String genFinalCmd(String binStr, String argStr, String placeholder, String replacement) {

        return binStr + argStr.replace(placeholder, replacement);
    }


    public static void executeCmd(String cmd){
        executeCmd(cmd,"","","");
    }


    public static void executeCmd(String binStr, String argStr, String placeholder, String replacement) {
        String finalCmd;

        if(!placeholder.equals("") && !replacement.equals("")){
            finalCmd = genFinalCmd(binStr, argStr, placeholder, replacement);
        }else{
            finalCmd = binStr+argStr;
        }

        System.out.println("*****Running \""+finalCmd+"\"*****");
        try {
            Process proc = Runtime.getRuntime().exec(finalCmd);
            int exitValue = proc.waitFor();
            InputStream inputStream;
            if (exitValue == 0) {//exit normally
                inputStream = proc.getInputStream();
            } else {//exit unexpectedly
                inputStream = proc.getErrorStream();
            }

//            System.out.println(exitValue);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String result;

            while ((result = bufferedReader.readLine()) != null){
                System.out.println(result);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("*****Finished \""+finalCmd+"\"*****");
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    public String getBinStr() {
        return binStr;
    }

    public void setBinStr(String binStr) {
        this.binStr = binStr;
    }

    public String getArgStr() {
        return argStr;
    }

    public void setArgStr(String argStr) {
        this.argStr = argStr;
    }

    public String getFinalCmd() {
        return finalCmd;
    }

    public Process getProc() {
        return proc;
    }
}
