package CmdLab;

import java.io.*;

/**
 * Created by weizhaoy on 17/1/17.
 */
public class CmdTest {

    static String binStr = "";
    static String argStr = "";


    public static void main(String[] args) {

//        binStr = "java ";//"/Users/weizhaoy/Documents/STAP/Research/openssl-1.1.0c/apps/openssl "
//        argStr = " -version ";
        binStr = "/Users/weizhaoy/Documents/STAP/Research/openssl-1.1.0c/apps/openssl";
        argStr = " verify  -CAfile " +
                "/Users/weizhaoy/Documents/STAP/Research/new/mucert_replication/utils/rootCA_key_cert.pem " +
                "${seedpath}";
        String placeholder = "${seedpath}";
        String replacement = "";

        String finalcmd = CmdRunner.genFinalCmd(binStr, argStr, placeholder, replacement);


        String dirStr = "/Users/weizhaoy/Documents/STAP/Research/new/mucert_replication/utils/test_certs";
        File dirFile = new File(dirStr);

        for (File file : dirFile.listFiles()) {
            replacement = file.getAbsolutePath();
            CmdRunner.executeCmd(binStr, argStr, placeholder, replacement);

            //todo: collect coverage info ?
            /*
            finalcmd = CmdRunner.genFinalCmd(binStr, argStr, placeholder, replacement);

            try {
                Process proc = Runtime.getRuntime().exec(finalcmd);
                int exitValue = proc.waitFor();
                InputStream in;
                if (exitValue == 0) {
                    in = proc.getInputStream();
                } else {
                    in = proc.getErrorStream();
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String result = "";
                while ((result = bufferedReader.readLine()) != null) {
                    //System.out.println(result);
                }

                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */

        }


//        try {
//            Process proc = Runtime.getRuntime().exec(finalcmd);
//            int exitValue = proc.waitFor();
//            InputStream in;
//            if (exitValue == 0) {
//                in = proc.getInputStream();
//            } else {
//                in = proc.getErrorStream();
//            }
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
//            String result = "";
//            while ((result = bufferedReader.readLine()) != null) {
//                System.out.println(result);
//            }
//
//            bufferedReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
