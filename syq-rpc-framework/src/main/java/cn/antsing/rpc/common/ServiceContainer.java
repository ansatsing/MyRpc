package cn.antsing.rpc.common;

import java.util.HashMap;
import java.util.Map;

public final class ServiceContainer {
    private final static Map<String,Object> serviceContainer = new HashMap<String,Object>();
    public static void addService(String interfaceFullName,Object o){

    }
    public static void removeService(String interfaceFullName){

    }

    public static Object getService(String interfaceFullName){
        return null;
    }
}
