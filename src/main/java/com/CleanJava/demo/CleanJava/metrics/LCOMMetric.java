package com.CleanJava.demo.CleanJava.metrics;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.CleanJava.demo.CleanJava.parser.ParserFacade;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;

public class LCOMMetric implements MetricCalculator {
	
	private ParserFacade parser;
	private int methodReferenceCounter = 0;
	
	public LCOMMetric() throws FileNotFoundException {
		this.parser = ParserFacade.getInstance();  
	}

	@Override
	public String calculateMetric() {

		List<String> fieldNames = parser.getFields();
		List<MethodDeclaration> methodBlocks = parser.getJustBlock();
		List<String> stringLiterals = parser.getStringLiterals();
		List<String> newBlocks = new ArrayList<String>();
		List<Comment> comments;
		
		// Remove All comments from block
		for (MethodDeclaration methodBlock: methodBlocks) {
			String aBlock = methodBlock.getBody().toString();
			comments = methodBlock.getAllContainedComments();
			System.out.println(comments.toString());
			if(!(comments.isEmpty())) {
				for (Comment comment: comments) {
					aBlock = aBlock.replace(comment.toString(), "@");					
				}
			}
			newBlocks.add(aBlock); 
		 }
			
		// remove all string literals from block
		for (int i=0; i < newBlocks.size(); i++) {
			for (String aString: stringLiterals) {
				if (newBlocks.get(i).contains(aString)) {
					 newBlocks.set(i, newBlocks.get(i).replace(aString, "@"));
				}
		    }
			
			for (String fieldName: fieldNames) {
				if (newBlocks.get(i).contains(fieldName)) {
					methodReferenceCounter ++;
				}
			}
		}
		 System.out.println("newBlocks " + newBlocks.toString());

		 double LCOM = 1.0 - (double) methodReferenceCounter/(methodBlocks.size()*fieldNames.size());
		 System.out.println("referenceCounter " + methodReferenceCounter);
//		 System.out.println("LCOM: " + LCOM);	
		 return Double.toString(LCOM);
	}

}
