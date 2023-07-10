package com.CleanJava.demo.CleanJava.helpers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.springframework.data.util.Pair;
import org.springframework.util.LinkedMultiValueMap;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

public class ErrorCodeCollector {
	
	private static ErrorCodeCollector instance;
	private HashMap<String, Pair<String, String>> errorCodes = new HashMap<String,Pair<String, String>>();
	private ArrayList<String> errorMessages = new ArrayList<String>();
	
	// Singleton pattern
	public static ErrorCodeCollector getInstance() throws FileNotFoundException {
		
        if (instance == null) {
            instance = new ErrorCodeCollector();
        }       
        return instance;
     }
	
	 public ArrayList<String> getErrorMessages(){
		 return errorMessages;
	 }
	 
	 public void addErrorMessage(String message){
		 errorMessages.add(message);
	 }
	
	 public HashMap<String, Pair<String, String>> getErrorCodes(){
		 return errorCodes;
	 }

	 public void addErrorCode(String errorCode, String name, String line) {
	 	errorCodes.put(errorCode, Pair.of(name, line));
	 }
}
