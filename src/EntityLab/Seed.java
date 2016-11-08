package EntityLab;

import java.io.File;

/**
 * Created by weizhaoy on 16/11/2.
 */
public class Seed {
    protected File seedFile;
    protected String seedFilePath;
    protected String seedFileName;

    public Seed(File seedFile){
        setSeedFile(seedFile);
        setSeedFileName(seedFile.getName());
        setSeedFilePath(seedFile.getAbsolutePath());
    }



    public File getSeedFile() {
        return seedFile;
    }

    public void setSeedFile(File seedFile) {
        this.seedFile = seedFile;
    }

    public String getSeedFilePath() {
        return seedFilePath;
    }

    public void setSeedFilePath(String seedFilePath) {
        this.seedFilePath = seedFilePath;
    }

    public String getSeedFileName() {
        return seedFileName;
    }

    public void setSeedFileName(String seedFileName) {
        this.seedFileName = seedFileName;
    }
}
