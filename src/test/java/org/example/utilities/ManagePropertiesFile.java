package org.example.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ManagePropertiesFile {

    public String getPropertyValue(String fileName,String propertyName) throws IOException {
        FileReader fileReader =new FileReader(fileName);
        Properties properties=new Properties();
        properties.load(fileReader);
        return properties.getProperty(propertyName);
    }
}
