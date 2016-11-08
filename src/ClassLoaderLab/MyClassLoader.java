package ClassLoaderLab;

import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;

/**
 * Created by weizhaoy on 16/10/25.
 */
public class MyClassLoader extends ClassLoader {




    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File opFile = new File(name);
        if(opFile == null){
            System.out.println("Invalid file.");
            return null;
        }


        byte[] opBytes =  Utils.fileToByteArray(opFile);

        return defineClass(null,opBytes,0,opBytes.length);



    }
}
