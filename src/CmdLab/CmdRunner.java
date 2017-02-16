package CmdLab;

/**
 * Run Shell Cmd
 * Created by weizhaoy on 17/1/17.
 */
public class CmdRunner {

    private  String placeholder; //placeholder pattern in command line, such as "${seed}"
    private  String replacement; //string to replace the placeholder, such as a concrete seed
    private  String binStr;  //target binary (path)
    private  String argStr;  //command line arguments to the target binary

    private  String finalCmd; //final cmd after replacing

    private Process proc = null;


    public static String genFinalCmd(String binStr, String argStr, String placeholder, String replacement){

        return binStr + argStr.replace(placeholder,replacement);
    }
}
