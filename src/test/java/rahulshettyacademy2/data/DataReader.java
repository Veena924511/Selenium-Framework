package rahulshettyacademy2.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to String
		// pass json file path as an argument
		//StandardCharsets.UTF_8 -> means we want to convert our Json file in to string in  UTF_8 encoding format
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		// convert String to HashMap - for this we need to use Jackson Databind // dependency - search and add that dependency to pom.xml file
		ObjectMapper mapper = new ObjectMapper();
		// read String from jsonContent, convert it into HashMap and store it in list(we
		// used list because we have array of 2 indexes in json file which means we have
		// 2 HashMaps)
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

}
