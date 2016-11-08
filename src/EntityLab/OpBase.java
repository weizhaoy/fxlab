package EntityLab;

import java.io.File;
import java.lang.reflect.Method;
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




    protected OpBase(File file){
        opFile = file;
        opFilePath = opFile.getAbsolutePath();
        opFileName = opFile.getName();
        //todo class
        //todo methods
    }

    public static OpBase fromFile(File file){
        return new OpBase(file);
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
