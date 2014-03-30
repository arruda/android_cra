package com.example.cra.util;

//import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

/**
 * @author felipe.pontes
 *
 */
public class JsonConfigLoader {
	
	
	public static String loadJSONFromAsset(Context myContext) {
	    String json = null;
	    try {
	        InputStream is = myContext.getAssets().open("materias.json");

	        int size = is.available();

	        byte[] buffer = new byte[size];

	        is.read(buffer);

	        is.close();

	        json = new String(buffer, "UTF-8");


	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    return json;

	}
	
}