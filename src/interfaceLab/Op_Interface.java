package interfaceLab;

import java.io.File;

/**
 * Created by weizhaoy on 16/10/25.
 */
public interface Op_Interface {

    void Op_mut_1(File seed1, File seed2);

    int Op_mut_2(int seed1_as_int, int seed2_as_int);

    String Op_mut_3(String seed1_as_string, String seed2_as_string);


}
