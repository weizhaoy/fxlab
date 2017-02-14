package CmdLab;

/**
 * Run Shell Cmd
 * Created by weizhaoy on 17/1/17.
 */
public class CmdRunner {
    static String placeholder; //placeholder pattern in command line, such as "${seed}"
    static String replacement; //string to replace the placeholder, such as a concrete seed
    static String binStr;  //target binary (path)
    static String argStr;  //command line arguments to the target binary

    private Process proc = null;

}
