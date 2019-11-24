package cn.antsing.spi.client;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "META-INF/services/cn.antsing.spi.api.Robot";
        Enumeration<URL> systemResources = ClassLoader.getSystemResources(fileName);
        while (systemResources.hasMoreElements()){
            System.out.println(systemResources.nextElement().toString());
        }
    }
}
