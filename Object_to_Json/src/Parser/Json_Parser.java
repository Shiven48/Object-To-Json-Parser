package Parser;

//import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Json_Parser {

	public static void main(String[] args) {
		// A Simple Java Object
		Student student = new Student(1," \"Shiven\" ",48);
		String temp = student.toString();
		String obj_format = "";
		
		obj_format = removeName(temp);
		
		//This is the object format
		System.out.println("Java Object : "+obj_format); 
		
		Map<String, String> json_format = parseJSON(obj_format);
		String json = json_format.toString();
		json = json.replaceAll("=", ":");
		System.out.println("Json Format : "+json);
	}

	// 
	public static Map<String, String> parseJSON(String objectString) {
		Map<String, String> jsonMap = new HashMap<>();
		objectString = objectString.trim();
		if (objectString.startsWith("[") && objectString.endsWith("]")) {
			// [ ] characters are skipped
			objectString = objectString.substring(1, objectString.length() - 1);
			// Split the object String when , is not followed by [ , ],{ , }  this four characters
			String[] keyValuePairs = objectString.split(",\\s*(?=[^{}\\[\\]]*\\s*[^{}\\[\\]]*$)");
			for (String pair : keyValuePairs) {
				// Split the string array further when : or = characters are found
				String[] entry = pair.split("[=:]");
				if (entry.length == 2) {
					// Here key and value are found so we will put them into map
					String key = "\""+entry[0].trim()+"\"";
					String value = entry[1].trim();
					jsonMap.put(key, value);
				}
			}
		}
		return jsonMap;
	}

	// Just for some pre-processing for the Java Object  
	public static String removeName(String text) {
		int startIndex = text.indexOf("[");
		text = text.substring(startIndex);
		return text.trim();
	}
}
