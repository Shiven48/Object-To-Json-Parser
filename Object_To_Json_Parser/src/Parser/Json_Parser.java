package Parser;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json_Parser {

	private static Map<String,Class<?>> properties = new HashMap<String,Class<?>>();
	
	public static void main(String[] args) {
		
		Author author = new Author("F. Scott Fitzgerald","American",LocalDate.of(1896, 9, 24),true);
		Review review1 = new Review("Alice",5,"A timeless classic!");
		List<Review>reviews = new ArrayList<Review>();
		reviews.add(review1);	
		String[] genres = {"Fiction", "Classic"};
		Book books = new Book("The Great Gatsby",author,1925,genres,reviews);
		
		properties = getFields(books);
		//System.out.println(properties);								
		
		String obj_format = books.toString();
		
		//This is the object format
		System.out.println("Java Object : "+obj_format); 				
		System.out.println();
		
		Map<String, String> json_format = parseJSON(obj_format);
		String json = json_format.toString();
		
		json = json.replaceAll("=", ":");
		//This is the Json format
		System.out.println("Json Format : "+json);						
	}

	public static Map<String, String> parseJSON(String objectString) {
		Map<String, String> jsonMap = new LinkedHashMap<>();
		objectString = objectString.trim();									
		
		// Check if String starts with '[' and ']' OR '{' and '}' characters if yes then skip them
		if (objectString.startsWith("[") && objectString.endsWith("]") || objectString.startsWith("{") && objectString.endsWith("}") ) {
			objectString = objectString.substring(1, objectString.length() - 1);
		}
		
		//Split by commas, but not within nested structures
		List<String> parts = splitOutsideNestedStructures(objectString);
		
		for (String part : parts) {
          // Split into key and value
          int equalIndex = findFirstEqualOutsideNested(part);
          // System.out.println("Equal Index : "+equalIndex);				
          
          if (equalIndex != -1) {
              String key = part.substring(0, equalIndex).trim();
              String value = part.substring(equalIndex + 1).trim();
              
              // System.out.println("Before: "key+"    :    "+value);										
              
              // Format the key-value pair
              key = "\"" + key + "\"";
              value = formatValue(key.replace("\"", ""), value);
              
              // System.out.println("After: "+key+"    :    "+value);										
              
              jsonMap.put(key, value);
          }
      }																		
      return jsonMap;
  }
		
	// For debugging
	private static void iterateMap(Map<String, String> jsonMap) {
		for(Map.Entry<String, String> map :jsonMap.entrySet()) {
			System.out.println(map.getKey()+" "+map.getValue());
		}
	}

	// For debugging
	private static void iterate(List<String> parts) {
		for(String part:parts) {
			System.out.println(part);
		}
		System.out.println();
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
	
	private static String formatValue(String key, String value) {
		
		if(value == null || value.isEmpty()) {
			return null;
		}
		
		// For nested object
		else if(value.startsWith("{") && value.endsWith("}")) {
			return value;
		}
		
		//For nested Array 
		else if(value.startsWith("[") && value.endsWith("]")) {
			return formatArray(value);
		}
		
		if(properties.containsKey(key)) {
			Class<?> dataType = properties.get(key);
			if(dataType == String.class) {
				return "\""+value+"\"";
			}
			else if(dataType == Integer.class || dataType == int.class ||
					dataType == Long.class || dataType == long.class ||
					dataType == Double.class || dataType == double.class
					) {
				return value;
			}
			else if(dataType == boolean.class || dataType == Boolean.class) {
				return value.toLowerCase();
			}
			else if(dataType == LocalDate.class || dataType == LocalTime.class || dataType == LocalDateTime.class) {
				return "\""+value+"\"";
			}
		}
		
		// by default handle as String
		return "\""+value+"\"";
	}

	private static String formatArray(String arrayString) {
		
	  arrayString = arrayString.substring(1, arrayString.length() - 1);
      
      List<String> elements = splitOutsideNestedStructures(arrayString);
      
      List<String> formattedElements = new ArrayList<>();
      for(String element : elements) {
    	 element = element.trim();
    	  if(element.startsWith("{")) {
    		 formattedElements.add(element);
    	 } else {
    		 formattedElements.add("\""+element+"\"");
    	 }
      }
		
		return "[ "+ String.join(",", formattedElements) +" ]";
	}
	
	private static int findFirstEqualOutsideNested(String part) {
		int first_index = 0;
		int bracketCount = 0;
		int braceCount = 0;
		char[] part_arr = part.toCharArray();
		
		for(char c : part_arr)
		{
			if(c == '[') bracketCount++;
			else if(c == ']') bracketCount--;
			else if(c == '{') braceCount++;
			else if(c == '}') braceCount--;
			
			if(c == '=' && bracketCount == 0 && braceCount == 0) {
				return first_index;
			} else {
				first_index++;
			}
		}
		
		return -1;
	}

	private static List<String> splitOutsideNestedStructures(String objectString) {
		
		List<String> parts = new ArrayList<String>();
		int bracketCount = 0;
		int braceCount = 0;
		StringBuilder currentpart = new StringBuilder();
		char[] objectString_char_arr = objectString.toCharArray();
		
		for(char c : objectString_char_arr)
		{
			if(c == '[') bracketCount++;
			else if(c == ']') bracketCount--;
			else if(c == '{') braceCount++;
			else if(c == '}') braceCount--;
			
			if(c == ',' && bracketCount == 0 && braceCount == 0) {
				parts.add(currentpart.toString().trim());
				currentpart = new StringBuilder();
			} else {
				currentpart.append(c);
			}	
		}
		
		if (currentpart.length() > 0) {
          parts.add(currentpart.toString().trim());
      }
		
		return parts;
	}
}



    