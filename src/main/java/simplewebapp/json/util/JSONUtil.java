package simplewebapp.json.util;

import com.owlike.genson.Genson;

import org.json.JSONException;
import org.json.JSONObject;

import simplewebapp.beans.*;

public class JSONUtil {
	
	public static Connection deserialize(String json) {
		if(validate(json)){			
			Connection connection = new Genson().deserialize(json, Connection.class);
			return connection;
		}
		return null;
	}
	
	public static Boolean validate(String json) {
		try {
	        new JSONObject(json);
	    } catch (JSONException e) {
	        return false;
	    }
	    return true;
	}

}
