package CmdLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by weizhaoy on 17/1/17.
 */
public class CmdTest {

    static String binStr = "";
    static String argStr = "";


    public static void main(String[] args) {

//        binStr = "java ";//"/Users/weizhaoy/Documents/STAP/Research/openssl-1.1.0c/apps/openssl "
//        argStr = " -version ";
        binStr = "javac";
        argStr = " -a -b ${classfile} -c";
        String placeholder = "${classfile}";
        String replacement = "HelloWorld";

        String finalcmd = CmdRunner.genFinalCmd(binStr, argStr, placeholder, replacement);




        try {
            Process proc = Runtime.getRuntime().exec(finalcmd);
            int exitValue = proc.waitFor();
            InputStream in;
            if (exitValue == 0){
                in = proc.getInputStream();
            }else{
                in = proc.getErrorStream();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String result = "";
            while ((result = bufferedReader.readLine()) != null) {
                System.out.println(result);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
