package ClassLoaderLab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by weizhaoy on 16/10/25.
 */
public class Utils {
    public static byte[] fileToByteArray(File file){

        FileChannel fileChannel = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            fileChannel = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
            while (fileChannel.read(byteBuffer) > 0){

            }

            return byteBuffer.array();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static File renameFile(File file, String name){
        String parent = file.getParent();
        String extension = "";//assuming parameter "name" has already included extension name
        String newName = parent+File.separator+name+extension;
        System.out.println("New Name: "+newName);
        File newFile = new File(newName);
        file.renameTo(newFile);
        return newFile;

    }
}
