package interfaceLab;

/**
 * Created by weizhaoy on 16/10/25.
 */
public class InterfaceTest {
    public static void main(String[] args){
        Op_Interface_Implementation op_interface_implementation = new Op_Interface_Implementation();
        System.out.println(op_interface_implementation.Op_mut_2(1,2));
        System.out.println(op_interface_implementation.Op_mut_3("hello ","world"));
    }
}
