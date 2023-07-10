package com.CleanJava.demo.CleanJava.metrics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.CleanJava.demo.CleanJava.parser.ParserFacade;

//LITERAL_WHILE , LITERAL_DO , LITERAL_FOR , LITERAL_IF , LITERAL_SWITCH , 
//LITERAL_CASE , LITERAL_CATCH , QUESTION ??

public class CyclomaticComplexityMetric implements MetricCalculator {
	
	private ParserFacade parser;
	private int complexityCounter = 0;
	
	public CyclomaticComplexityMetric() throws FileNotFoundException{
		this.parser = ParserFacade.getInstance();
	}
	
	@Override
	public String calculateMetric() {
		
//		List<String> wildcardExpressions = parser.getWildcardExpressions();
//		System.out.print("before: " + wildcardExpressions.toString());
//
//		for (String exp : wildcardExpressions) {
//			if (!(exp.contains("?"))) {
//				wildcardExpressions.remove(exp);
//			}
//		}
//		System.out.print("after: " + wildcardExpressions.toString());


		complexityCounter   += parser.getIfs().size()
							 + parser.getDos().size()
							 + parser.getForStatements().size()
						     + parser.getForEachStatements().size()
							 + parser.getwhiles().size()
							 + parser.getCase().size()
							 + parser.getCatch().size()
							 + parser.getSwitch().size();
//							 + wildcardExpressions.size();
		// add the ? thingy
		
//		List<String> booleanExpressions = parser.getBooleanExpCollector();
//		System.out.print(booleanExpressions.toString());
//	
//		for(int i=0; i<booleanExpressions.size(); i++) {
//
//			if (booleanExpressions.get(i).contains("&&")){
//				complexityCounter += 1;
//			}
//			
//			if (booleanExpressions.get(i).contains("||")) {
//				complexityCounter += 1;
//			}
//		}
		
		return Integer.toString(complexityCounter);
	}

}
