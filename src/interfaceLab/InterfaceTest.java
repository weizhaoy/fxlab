package InterfaceLab;

/**
 * Created by weizhaoy on 16/10/25.
 */
public class InterfaceTest {
    public static void main(String[] args){
        Op_Interface_Implementation_1 op_interface_implementation_1 = new Op_Interface_Implementation_1();
        Op_Interface_Implementation_2 op_interface_implementation_2 = new Op_Interface_Implementation_2();
        System.out.println(op_interface_implementation_1.Op_mut_2(1,2));
        System.out.println(op_interface_implementation_1.Op_mut_3("hello ","world"));
        System.out.println(op_interface_implementation_2.Op_mut_2(1,2));
        System.out.println(op_interface_implementation_2.Op_mut_3("hello ","world"));
    }
}
