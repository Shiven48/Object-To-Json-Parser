package Parser;

import java.lang.reflect.Field;
import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.Arrays;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class Json_Parser {

	private static Map<String,Class<?>> properties = new HashMap<String,Class<?>>();
	
	public static void main(String[] args) {
		
		Author author = new Author("F. Scott Fitzgerald","American",LocalDate.of(1896, 9, 24),true);
		String obj_format = author.toString();
		
		properties = getFields(author);
		
		//This is the object format
		System.out.println("Java Object : "+obj_format); 
		
		Map<String, String> json_format = parseJSON(obj_format);
		String json = json_format.toString();
		
		json = json.replaceAll("=", ":");
		//This is the Json format
		System.out.println("Json Format : "+json);
	}
	
	public static Map<String,Class<?>> getFields(Object input) {
		if(input == null) {
			return null;
		}
		
		Field[] fields = input.getClass().getDeclaredFields();
		
		for(Field field : fields) {
			properties.put(field.getName(),field.getType());
		}
		
		return properties;
	}
	
	public static Map<String, String> parseJSON(String objectString) {
		Map<String, String> jsonMap = new HashMap<>();
		objectString = objectString.trim();
		
		// Check if String starts with '[' and ']' characters
		if (objectString.startsWith("[") && objectString.endsWith("]")) {
			// '[' and  ']' characters are skipped
			objectString = objectString.substring(1, objectString.length() - 1);
			
			// Split the object String when , is not followed by '[',']','{','}' this four characters
			// Regex needs some changes
			String[] keyValuePairs = objectString.split(",\\s*(?=[^{}\\[\\]]*\\s*[^{}\\[\\]]*$)");
			
			for (String pair : keyValuePairs) {
				// Split the string array further when : or = characters are found
				String[] entry = pair.split("[=:]");
				
				 if (entry.length == 2) {
					// Here key and value are found so we will put them into jsonmap
					String key = entry[0].trim();
					String value = null;
					
					if(properties.containsKey(key)) {
						String value_datatype = properties.get(key).toString();
						key = "\""+entry[0].trim()+"\"";
						//System.out.println("DataType of Value : "value_datatype);
						
						if(value_datatype.equals("class java.lang.String")) {
							value =  "\""+entry[1].trim()+"\"";
						}
						else {
							value =entry[1].trim();
						}
						//  OR (Does the same thing)
						// value = value_datatype.equals("class java.lang.String") 
						// ? "\"" + entry[1].trim() + "\"" 
						// : entry[1].trim();

						jsonMap.put(key, value);
					}
				}
			}
		}
		return jsonMap;
	}
}
