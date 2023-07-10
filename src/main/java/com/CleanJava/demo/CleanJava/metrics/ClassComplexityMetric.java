package com.CleanJava.demo.CleanJava.metrics;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.CleanJava.demo.CleanJava.parser.ParserFacade;

// Check the number of class types used by a class.
public class ClassComplexityMetric implements MetricCalculator {
	
	private ParserFacade parser;
	private List<String> excludedClasses;
	private List<String> finalList = new ArrayList<String>();
	private String filename = "C:\\Users\\Anna\\Desktop\\CleanJava\\src\\main\\java\\com\\CleanJava\\demo\\CleanJava\\helpers\\excludedClasses"; 
	
	
	public ClassComplexityMetric() throws IOException {
		this.parser = ParserFacade.getInstance();
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			excludedClasses = lines.collect(Collectors.toList());
		}
	}
	
	@Override
	public String calculateMetric() throws IOException {
		  int complexity_counter = 0;
		  List<String> imports = parser.getImports();	
		  
		  try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			excludedClasses = lines.collect(Collectors.toList());
		  }
		  
		  // Do the same for imports.
		  for (int i=0; i<imports.size(); i++) {
			  
			  // Get the last one after the period.
			  String[] currentImportList = imports.get(i).split("\\.");
			  String currentImport = currentImportList[currentImportList.length - 1];
			  isExcluded(currentImport);

			  // check if the import is "*"
			  if (currentImport.equals("*")) {
				  currentImport = currentImportList[currentImportList.length - 2];
				  isExcluded(currentImport);
			  } 
		  }
		  
	      complexity_counter = finalList.size();

	      excludedClasses.clear();		
		  return Integer.toString(complexity_counter);
	}
	
	
	private void isExcluded(String currentImport) {
		
		  // If the import is not included in the excluded classes
     	  if (!(excludedClasses.contains(currentImport))) {
    		  finalList.add(currentImport);
    	  }
     	  
	}
}
