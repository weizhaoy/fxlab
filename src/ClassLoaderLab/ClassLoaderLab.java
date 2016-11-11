package ClassLoaderLab;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by weizhaoy on 16/11/10.
 */
public class ClassLoaderLab {

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader();
        File testClassFile = new File("/Users/weizhaoy/Documents/workspace/java/FXLab/out/production/FXLab/interfaceLab/Op_Interface_Implementation_1.class");
        Class clazz = classLoader.findClass("/Users/weizhaoy/Documents/workspace/java/FXLab/out/production/FXLab/interfaceLab/Op_Interface_Implementation_1.class");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName()+": ");
            if(method.getReturnType() == File.class){
                System.out.println("By Return Type");
            }
            if (method.getName().startsWith("Op_mut")){
                System.out.println("By Method Name");
            }

        }

//        File oldFile = new File("/Users/weizhaoy/Documents/workspace/java/FXLab/oldFile");
//        File newfile = Utils.renameFile(oldFile,"newname");
//        System.out.println(newfile.getAbsolutePath());


    }

}
