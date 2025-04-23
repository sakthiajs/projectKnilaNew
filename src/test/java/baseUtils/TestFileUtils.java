package baseUtils;

import java.io.File;
import java.net.URL;

public class TestFileUtils {

    
    public static String getTestDataFilePath(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("test-data/" + fileName);

        if (resource == null) {
            throw new RuntimeException("File not found in test resources: test-data/" + fileName);
        }

        return new File(resource.getFile()).getAbsolutePath();
    }
}
