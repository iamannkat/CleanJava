package com.CleanJava.demo.CleanJava.metrics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.CleanJava.demo.CleanJava.parser.ParserFacade;


// part of this code is from this website
// https://www.javaguides.net/2018/08/java-program-to-count-number-of-occurrences-of-substring-in-string.html
public class BooleanExpressionComplexityMetric implements MetricCalculator{
	
	private ParserFacade parser;
//	private final List<String> BooleanOperators = Arrays.asList("&", "&&", "|", "||", "^");
	
	public BooleanExpressionComplexityMetric()  throws FileNotFoundException {
		this.parser = ParserFacade.getInstance();
	}

	@Override
	public String calculateMetric() {
		
		List<String> expressions = parser.getBooleanExpCollector();

		int currentOccurance = 0;
		int maxOccurance = 0;
	
		for (int i = 0; i < expressions.size(); i++) {
		
				currentOccurance = countNumberBoolleanOperatorOccurances(expressions.get(i));

				if (currentOccurance > maxOccurance) {
					maxOccurance = currentOccurance;
			    }			
		}

		return Integer.toString(maxOccurance);
		
	}
	
	private int countNumberBoolleanOperatorOccurances(String expression) {
		    int booleanCount = 0;

			if (expression.contains("&&")) { 
				booleanCount = booleanCount + countOccurrencesOf(expression, "&&");;

			}else if (expression.contains("&")) {
				booleanCount = booleanCount + countOccurrencesOf(expression, "&");;
			}
			
			if (expression.contains("||")) {		
				booleanCount = booleanCount + countOccurrencesOf(expression, "||");

			}else if (expression.contains("|")) {				
				booleanCount = booleanCount + countOccurrencesOf(expression, "|");;

			}
			
			if (expression.contains("^")) {
				booleanCount = booleanCount + countOccurrencesOf(expression, "^");;

			}
									
		return booleanCount;
	}
	
//	private boolean containsBooleanOperator(String expression) {
//
//		for (int i = 0; i < BooleanOperators.size(); i++) {
//			if (expression.contains(BooleanOperators.get(i))) {
//				return true;
//			}
//		}
//		return false;
//	}
	  private boolean hasLength(String str) {
	        return (str != null && !str.isEmpty());
	  }

      private int countOccurrencesOf(String str, String sub) {
		    if (!hasLength(str) || !hasLength(sub)) {
		        return 0;
		    }
		
		    int count = 0;
		    int pos = 0;
		    int idx;
		    while ((idx = str.indexOf(sub, pos)) != -1) {
		        ++count;
		        pos = idx + sub.length();
		    }
		    return count;
      }

}
