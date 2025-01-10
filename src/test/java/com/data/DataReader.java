package com.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	@SuppressWarnings("deprecation")
	public List<HashMap<String,String>> getJsonData(String path) throws IOException {
		//read json to string
	
	String stjsonContent=	FileUtils.readFileToString(new File(path));
	// string to hashmap jackson databind
	ObjectMapper mapper=new  ObjectMapper();
	List<HashMap<String, String>> data=mapper.readValue(stjsonContent, new TypeReference<List<HashMap<String, String>>>() {
	});
	return data;
	}

}
