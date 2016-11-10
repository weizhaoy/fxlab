package EntityLab;

import ClassLoaderLab.MyClassLoader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by weizhaoy on 16/11/2.
 */
public class OpBase {
    protected Class opClass;
    protected File opFile;
    protected String opFilePath;
    protected String opFileName;
    protected List<Method> methods;
    protected List<Method> opMethods;//opMethods (Methods that are operators/mutators)

    protected static String OpMethodNamePrefix="Op_mut";





    public OpBase(File file){
        opFile = file;
        opFilePath = opFile.getAbsolutePath();
        opFileName = opFile.getName();
        // class
        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            opClass = myClassLoader.loadClass(opFilePath);
            /**methods**/
            methods = new ArrayList<Method>();
            Collections.addAll(methods, opClass.getDeclaredMethods());
            /**opMethods**/
            /**either By Method Name**/
            //opMethods = opMethodsByMethodName();

            /**or By Return Type**/
            opMethods = opMethodsByReturnType();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

//    public static OpBase fromFile(File file){
//        return new OpBase(file);
//    }



    public boolean isOpMethod(Method method){//not used yet
        //todo: standard to be a real OpMethod

         return false;
    }


    public Class getOpClass() {
        return opClass;
    }

    public void setOpClass(Class opClass) {
        this.opClass = opClass;
    }

    public File getOpFile() {
        return opFile;
    }

    public void setOpFile(File opFile) {
        this.opFile = opFile;
    }

    public String getOpFilePath() {
        return opFilePath;
    }

    public void setOpFilePath(String opFilePath) {
        this.opFilePath = opFilePath;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public String getOpFileName() {
        return opFileName;
    }

    public void setOpFileName(String opFileName) {
        this.opFileName = opFileName;
    }

    public List<Method> getOpMethods() {
        return opMethods;
    }

    public void setOpMethods(List<Method> opMethods) {
        this.opMethods = opMethods;
    }

    public static String getOpMethodNamePrefix() {
        return OpMethodNamePrefix;
    }

    public static void setOpMethodNamePrefix(String opMethodNamePrefix) {
        OpMethodNamePrefix = opMethodNamePrefix;
    }


    private List<Method> opMethodsByMethodName(){//not suggested currently
        List<Method> opMethods = new ArrayList<Method>();
        for (Method method : getMethods()) {
            if(method.getName().startsWith(OpMethodNamePrefix)){
                opMethods.add(method);
            }
        }
        return opMethods;
    }

    private List<Method> opMethodsByReturnType(){
        List<Method> opMethods = new ArrayList<Method>();
        for (Method method : getMethods()) {
            if(method.getReturnType() == File.class){
                opMethods.add(method);
            }
        }
        return opMethods;
    }
}
