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
    //todo opMethods




    public OpBase(File file){
        opFile = file;
        opFilePath = opFile.getAbsolutePath();
        opFileName = opFile.getName();
        // class
        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            opClass = myClassLoader.loadClass(opFilePath);
            // methods
            methods = new ArrayList<Method>();
            Collections.addAll(methods, opClass.getDeclaredMethods());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

//    public static OpBase fromFile(File file){
//        return new OpBase(file);
//    }



    public boolean whetherMutatorMethod(Method method){
        //todo: standard to be a real mutator method
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
}
