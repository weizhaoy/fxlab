package InterfaceLab;

import ClassLoaderLab.Utils;

import java.io.File;

/**
 * Created by weizhaoy on 16/10/25.
 */
public class InterfaceLab {
    public static void main(String[] args){
        Op_Interface_Implementation_1 op_interface_implementation_1 = new Op_Interface_Implementation_1();
        Op_Interface_Implementation_2 op_interface_implementation_2 = new Op_Interface_Implementation_2();
//        System.out.println(op_interface_implementation_1.Op_mut_2(1,2));
//        System.out.println(op_interface_implementation_1.Op_mut_3("hello ","world"));
//        System.out.println(op_interface_implementation_2.Op_mut_2(1,2));
//        System.out.println(op_interface_implementation_2.Op_mut_3("hello ","world"));
        File oldFile = new File("/Users/weizhaoy/Documents/workspace/java/FXLab/oldfile");
        File newFile = op_interface_implementation_1.Op_mut_seed_num_1(oldFile);
        System.out.println(newFile.getAbsolutePath());
    }
}
