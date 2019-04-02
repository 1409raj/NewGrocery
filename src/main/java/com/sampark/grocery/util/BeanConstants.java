package com.sampark.grocery.util;

import java.util.HashMap;
import java.util.Map;

public class BeanConstants {
	
	public static final Map<String, String> myMap;
    static
    {
        myMap = new HashMap<String, String>();
        myMap.put("bigint","Long");
        myMap.put("char","String");
        myMap.put("date","Date");
        myMap.put("integer","Integer");
        myMap.put("int","Integer");
        myMap.put("smallint","Integer");
        myMap.put("time","Time");
        myMap.put("timestamp","Timestamp");
        myMap.put("varchar","String");
        myMap.put("double","Double");
    }
}
