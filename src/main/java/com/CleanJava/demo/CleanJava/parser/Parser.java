package com.CleanJava.demo.CleanJava.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Parser {
	private static String SRC_PATH;
	private CompilationUnit cu;
	private File inputCode;
	private static ParserFacade instance;
	public String value;
		
	public Parser(String sourcePath) throws FileNotFoundException {	
		SRC_PATH = sourcePath;
		inputCode = new File(sourcePath);
		cu = StaticJavaParser.parse(inputCode);

	}
	
	// Singleton pattern
	public static ParserFacade getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new ParserFacade(SRC_PATH);

        }
        return instance;
     }
	
	public List<String> getBooleanExpCollector(){
		List<String> bin = new ArrayList<>();

	    cu.findAll(BinaryExpr.class).forEach(be -> {
	    	
		 if (be.toString().contains("&") || 
				 be.toString().contains("&&") || 
				 be.toString().contains("|") || 
				 be.toString().contains("||") ||
				 be.toString().contains("^")) {
			 
			 bin.add(be.toString());
		 }	     
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	public List<Node> getFieldsTypes(){
		List<Node> bin = new ArrayList<>();

	    cu.findAll(FieldDeclaration.class).forEach(be -> {
	        bin.add(be.getElementType());
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public List<Object> getMethodParametersNumber(){// getParameter
		List<Object> bin = new ArrayList<>();

	    cu.findAll(MethodDeclaration.class).forEach(be -> {
	        bin.add(be.getNameAsString());
	        bin.add(be.getParameters().size());
	        bin.add(be.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
	        
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public List<String> getConstants(){
		List<String> bin = new ArrayList<>();

	    cu.findAll(VariableDeclarator.class).forEach(be -> {
	         be.getInitializer().ifPresent(i ->
			   i.ifLiteralExpr(il-> bin.add(be.getNameAsString())));
	        
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public List<String> getFields(){
		List<String> bin = new ArrayList<>();

	    cu.findAll(FieldDeclaration.class).forEach(be -> {
	        
			 bin.add(be.getVariables().get(0).getNameAsString());

	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public List<String> getMethodBlocks(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(MethodDeclaration.class).forEach(be -> {
	        
			 bin.add(be.getBody().toString());
			 bin.add(be.getNameAsString());
			 bin.add(be.getBegin().toString().replace("Optional[" ,"").
			  replace("]", "").split(",")[0].replace("(line ", ""));

	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public List<String> getParameters(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(Parameter.class).forEach(be -> {
	        
			 bin.add(be.getNameAsString());
			 bin.add(be.getBegin().toString().replace("Optional[" ,"").
			  replace("]", "").split(",")[0].replace("(line ", ""));

	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public List<MethodDeclaration> getJustBlock(){
		List<MethodDeclaration> bin = new ArrayList<>();
      
	    cu.findAll(MethodDeclaration.class).forEach(be -> {
	        
			 bin.add(be);

	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	

	public List<String> getInterfaceName(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(ClassOrInterfaceDeclaration.class).forEach(be -> {
	        
			 bin.add(be.getImplementedTypes().toString().replace("[" ,"").replace("]", ""));
			 bin.add(be.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public List<String> getCyclomaticStatements(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(IfStmt.class).forEach(be -> {
	        
			 bin.add(be.toString());
	    });
	    
	    cu.findAll(WhileStmt.class).forEach(be -> {
	        
			 bin.add(be.toString());
	    });
	    
	    cu.findAll(DoStmt.class).forEach(be -> {
	        
			 bin.add(be.toString());
	    });
	    
	    cu.findAll(CatchClause.class).forEach(be -> {
	        
			 bin.add(be.toString());
	    });
	    
	    cu.findAll(SwitchStmt.class).forEach(be -> {
	        
			 bin.add(be.toString());
	    });
	    
	    cu.findAll(SwitchEntry.class).forEach(be -> {
	        
			 bin.add(be.toString());
	    });
	    
	    cu.findAll(ForEachStmt.class).forEach(be -> {
	        
			 bin.add(be.toString());
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public List<String> getPackageNames(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(PackageDeclaration.class).forEach(be -> {
	        
			 bin.add(be.getNameAsString());
			 bin.add(be.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}

	
	public List<String> getMethodNames(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(MethodDeclaration.class).forEach(be -> {
	        
			 bin.add(be.getNameAsString());
			 bin.add(be.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public List<String> getConstructorLength(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(ConstructorDeclaration.class).forEach(be -> {
	        
			 bin.add(be.getBody().toString());
			 bin.add(be.getDeclarationAsString());
			 bin.add(be.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public List<String> getForStatements(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(ForStmt.class).forEach(be -> {
	        
			 bin.add(be.toString());
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	//getVariables
	public List<String> getVariables(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(VariableDeclarator.class).forEach(be -> {
	        
			 bin.add(be.getNameAsString());
			 bin.add(be.getTypeAsString());
			 bin.add(be.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	

	//getStringLiterals
	public List<String> getStringLiterals(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(VariableDeclarator.class).forEach(be -> {
	        
			 bin.add(be.toString());
		});
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	//getClassExp
	public List<String> getClassExp(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(VariableDeclarator.class).forEach(be -> {
	        
			 bin.add(be.getNameAsString());
			 bin.add(be.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}

	
	public List<String> getImports(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(ImportDeclaration.class).forEach(be -> {
	        
			 bin.add(be.getNameAsString());
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	//getVariablesNames
	public List<String> getVariablesNames(){
		List<String> bin = new ArrayList<>();
      
	    cu.findAll(VariableDeclarator.class).forEach(be -> {
	        
			 bin.add(be.getNameAsString());
			 bin.add(be.getTypeAsString());
			 bin.add(be.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
	    });
	    
//	    System.out.println(bin.toString());
	    return bin;
	}
	
	
	public int getLinesPerFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(SRC_PATH));
		int lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close(); 	
		return lines;
	}
	

	public int getLineLength(int line_number) throws IOException {
		String line = Files.readAllLines(Paths.get(SRC_PATH)).get(line_number);
		return line.length();
			  
	}
}
