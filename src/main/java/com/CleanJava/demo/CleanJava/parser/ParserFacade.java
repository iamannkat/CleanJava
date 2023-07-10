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
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchEntry;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.type.WildcardType;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.types.ResolvedType;

public class ParserFacade extends VoidVisitorAdapter<Void>{ 
	
	private static String SRC_PATH;
	private CompilationUnit cu;
	private File inputCode;
	private static ParserFacade instance;
	public String value;
		
	public ParserFacade(String sourcePath) throws FileNotFoundException {	
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
	

	public List<Node> getFieldTypes(){
		 VoidVisitorAdapter<List<Node>> visitor = new FieldTypeCollector();
		 List<Node> fields = new ArrayList<>();
		 visitor.visit(cu, fields);	
		 return fields;	
	}
	
//	 public List<String> getIntegerLiterals() {
//		 VoidVisitorAdapter<List<String>> visitor = new IntegerLiteralCollector();
//		 List<String> fields = new ArrayList<>();
//		 visitor.visit(cu, fields);	
//		 return fields;		
//	 }
	
	 public List<String> getConstants() {
		 VoidVisitorAdapter<List<String>> visitor = new ConstantVariableCollector();
		 List<String> fields = new ArrayList<>();
		 visitor.visit(cu, fields);	
		 return fields;		
	 }
	
	 public List<String> getFields() {
		 VoidVisitorAdapter<List<String>> visitor = new FieldCollector();
		 List<String> fields = new ArrayList<>();
		 visitor.visit(cu, fields);	
		 return fields;		
	 }
	
	public List<String> getMethodBlocks() {	
		List<String> methodBlocks = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new BlockCollector();
		methodVisitor.visit(cu, methodBlocks);
	    return methodBlocks;    
	}
	
	
	public List<String> getParameters() {	
		List<String> methodParameters = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new ParameterCollector();
		methodVisitor.visit(cu, methodParameters);
	    return methodParameters;    
	}
//	
//	public List<String> getFieldAccessExp() {	
//		List<String> methodParameters = new ArrayList<>();
//		VoidVisitor<List<String>> methodVisitor = new FieldAccessExprPrinter();
//		methodVisitor.visit(cu, methodParameters);
//	    return methodParameters;    
//	}
	
	public List<MethodDeclaration> getJustBlock() {	
		List<MethodDeclaration> methodBlocks = new ArrayList<>();
		VoidVisitor<List<MethodDeclaration>> methodVisitor = new MethodBlockCollector();
		methodVisitor.visit(cu, methodBlocks);
	    return methodBlocks;    
	} 
	
	public List<Object> getMethodParametersNumber() {		
		List<Object> methodParametersNames = new ArrayList<>();
		VoidVisitor<List<Object>> methodParametersVisitor = new MethodParametersCollector();
		methodParametersVisitor.visit(cu, methodParametersNames);
	    return methodParametersNames;    
	}
	
	
	public List<String> getInterfaceName() {
		List<String> interfaceNames = new ArrayList<>();
		VoidVisitor<List<String>> numericLiteralVisitor = new InterfaceCollector();
		numericLiteralVisitor.visit(cu, interfaceNames);
	    return interfaceNames;
	}
	
	
	public List<String> getPackageNames() {
		List<String> packageNames = new ArrayList<>();
		VoidVisitor<List<String>> PackageVisitor = new PackageNameCollector();
		PackageVisitor.visit(cu, packageNames);
	    return packageNames;
	}
	
	public List<String> getMethodNames() {
		List<String> methodNames = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new MethodNameCollector();
		methodVisitor.visit(cu, methodNames);
	    return methodNames;
	}
	
	public List<String> getBooleanExpCollector() {		
		List<String> expCollector = new ArrayList<>();
		VoidVisitor<List<String>> expVisitor = new BooleanExpCollector();
		expVisitor.visit(cu, expCollector);
	    return expCollector;
	}
	
	public List<String> getConstructorLength() {		
		List<String> constructorCollector = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new ConstructorCollector();
		methodVisitor.visit(cu, constructorCollector);
	    return constructorCollector;
	}
	
	public List<String> getForStatements() {		
		List<String> constructorCollector = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new ForStmtPrinter();
		methodVisitor.visit(cu, constructorCollector);
	    return constructorCollector;
	}
	
	
	public List<String> getForEachStatements() {		
		List<String> forEachCollector = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new ForeachStmtPrinter();
		methodVisitor.visit(cu, forEachCollector);
	    return forEachCollector;
	}
	
	public List<String> getVariables() {		
		List<String> constructorCollector = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new VariableCollector();
		methodVisitor.visit(cu, constructorCollector);
	    return constructorCollector;
	}
	
	public List<String> getStringLiterals() {		
		List<String> constructorCollector = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new StringLiteralCollector();
		methodVisitor.visit(cu, constructorCollector);
	    return constructorCollector;
	}
	
	public List<String> getClassExp() {		
		List<String> constructorCollector = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new ClassExpCollector();
		methodVisitor.visit(cu, constructorCollector);
	    return constructorCollector;
	}
	
	public List<String> getImports() {		
		List<String> constructorCollector = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new ImportCollector();
		methodVisitor.visit(cu, constructorCollector);
	    return constructorCollector;
	}
	
	public List<String> getVariablesNames() {		
		List<String> constructorCollector = new ArrayList<>();
		VoidVisitor<List<String>> methodVisitor = new VariableNameCollector();
		methodVisitor.visit(cu, constructorCollector);
	    return constructorCollector;
	}
	
	 public List<String> getIfs() {
		 VoidVisitorAdapter<List<String>> visitor = new IfStmtCollector();
		 List<String> fields = new ArrayList<>();
		 visitor.visit(cu, fields);	
		 return fields;
	 }
	 
	 
	 public List<String> getwhiles() {
		 VoidVisitorAdapter<List<String>> visitor = new WhileCollector();
		 List<String> fields = new ArrayList<>();
		 visitor.visit(cu, fields);	
		 return fields;
	 }
	 
	 
	 public List<String> getCase() {
		 VoidVisitorAdapter<List<String>> visitor = new SwitchEntryCollector();
		 List<String> fields = new ArrayList<>();
		 visitor.visit(cu, fields);	
		 return fields;
	 }
	 
	 
	 public List<String> getDos() {
		 VoidVisitorAdapter<List<String>> visitor = new DoCollector();
		 List<String> fields = new ArrayList<>();
		 visitor.visit(cu, fields);	
		 return fields;
	 }
	 
	 
	 public List<String> getCatch() {
		 VoidVisitorAdapter<List<String>> visitor = new CatchCollector();
		 List<String> fields = new ArrayList<>();
		 visitor.visit(cu, fields);	
		 return fields;
	 }
	 
	 public List<String> getSwitch() {
		 VoidVisitorAdapter<List<String>> visitor = new SwitchCollector();
		 List<String> fields = new ArrayList<>();
		 visitor.visit(cu, fields);	
		 return fields;
	 }
	 
	 public List<String> getWildcardExpressions() {
		 VoidVisitorAdapter<List<String>> visitor = new AssignExprCollector();
		 List<String> fields = new ArrayList<>();
		 visitor.visit(cu, fields);	
		 return fields;
	 }
	 
//	 public List<String> getThis(){
//		 List<Node> myNodes = cu.getChildNodes();
//		 for (Node node:myNodes) {
//			 
//		 }
//	 }
	 
// ========================================================================================	 
// ========================================================================================= 
	
	 private class StringLiteralCollector extends VoidVisitorAdapter<List<String>> {
		 
		  @Override
		  public void visit(StringLiteralExpr md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(md.toString());
		  }   
	 }	
	 

	 private class AssignExprCollector extends VoidVisitorAdapter<List<String>> {
		 
		  @Override
		  public void visit(AssignExpr md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(md.toString());
		  }   
	 }	
		
	 private class IntegerLiteralCollector extends VoidVisitorAdapter<List<String>> {
		 
		  @Override
		  public void visit(IntegerLiteralExpr md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(md.toString());
//			  collector.add(md.getBegin().toString().replace("Optional[" ,"").replace("]", ""));
			  collector.add(md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
		  }
	 }	 
	
	 private class VariableCollector extends VoidVisitorAdapter<List<String>> {
		 
		  @Override
		  public void visit(VariableDeclarator md, List<String> collector) {
			  super.visit(md, collector);		
			  collector.add(md.getNameAsString());
			  collector.add(md.getTypeAsString());
//			  collector.add(md.getBegin().toString().replace("Optional[" ,"").replace("]", ""));
			  collector.add(md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));

		  }
	 }
	 
	 private class ConstantVariableCollector extends VoidVisitorAdapter<List<String>> {
		 
		  @Override
		  public void visit(VariableDeclarator md, List<String> collector) {
			  super.visit(md, collector);

			  md.getInitializer().ifPresent(i ->
			   i.ifLiteralExpr(il-> collector.add(md.getNameAsString())));
			  
//			  md.getInitializer().ifPresent(i ->
//			   i.ifIntegerLiteralExpr(il-> collector.add(md.getBegin().toString().replace("Optional[(" ,"").replace(")]", ""))));
		  
		  }
	 }
	 	 
	 private class VariableNameCollector extends VoidVisitorAdapter<List<String>> {
		 
		  @Override
		  public void visit(VariableDeclarator md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(md.toString());
			  collector.add(md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));

		  }
	 }
	 
//	 
//	 private class FieldAccessExprPrinter extends VoidVisitorAdapter<List<String>> {
//		  @Override
//		  public void visit(FieldAccessExpr md, List<String> collector) {
//			  super.visit(md, collector);
//			  collector.add(md.toString());
//		  }
//	 }
	 	
	 private class ForStmtPrinter extends VoidVisitorAdapter<List<String>> {
		 
		  @Override
		  public void visit(ForStmt md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(md.toString());
		  }
	 }
		 
	 private class ForeachStmtPrinter extends VoidVisitorAdapter<List<String>> {
		  @Override
		  public void visit(ForEachStmt md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(md.toString());
		  }
	 }
	 
	 private class InterfaceCollector extends VoidVisitorAdapter<List<String>> {
		 
		  @Override
		  public void visit(ClassOrInterfaceDeclaration md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(0, md.getImplementedTypes().toString().replace("[" ,"").replace("]", ""));
			  collector.add(1, md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
		  }
	 }
	 

	 private class IfStmtCollector extends VoidVisitorAdapter<List<String>> {
		  @Override
		  public void visit(IfStmt md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(0, md.toString());
		  }	  
	 }

	
	 private class WhileCollector extends VoidVisitorAdapter<List<String>> {
		  @Override
		  public void visit(WhileStmt md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(0, md.toString());
		  }	  
	 }

	 
	 private class DoCollector extends VoidVisitorAdapter<List<String>> {
		  @Override
		  public void visit(DoStmt md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(0, md.toString());
		  }		  
	 }

	 
	 private class CatchCollector extends VoidVisitorAdapter<List<String>> {
		  @Override
		  public void visit(CatchClause md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(0, md.toString());
		  }		  
	 }

	 
	 private class SwitchCollector extends VoidVisitorAdapter<List<String>> {
		  @Override
		  public void visit(SwitchStmt md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(0, md.toString());
		  } 
	 }

	 
	 private class SwitchEntryCollector extends VoidVisitorAdapter<List<String>> {
		  @Override
		  public void visit(SwitchEntry md, List<String> collector) {
			  super.visit(md, collector);
			  collector.add(0, md.toString());
		  }
		  
	 }
	 
	private class PackageNameCollector extends VoidVisitorAdapter<List<String>> {
 			
		@Override
		public void visit(PackageDeclaration md, List<String> collector) {
			super.visit(md, collector);
			collector.add(0, md.getNameAsString());
//			collector.add(1, md.getBegin().toString().replace("Optional[" ,"").replace("]", ""));
			collector.add(1, md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
		}
	}	
		
	// collect the names and the names' of the parameters from each method 
	private class MethodNameCollector  extends VoidVisitorAdapter<List<String>> {
		 			
		 @Override
		 public void visit(MethodDeclaration md, List<String> collector) {
			 super.visit(md, collector);
			  collector.add(0, md.getNameAsString());
//			  collector.add(1, md.getBegin().toString().replace("Optional[" ,"").replace("]", ""));
			  collector.add(1, md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
		 }
	}
	
	// collect the names and the names' of the parameters from each method 
	private class ParameterCollector  extends VoidVisitorAdapter<List<String>> {
		 			
		 @Override
		 public void visit(Parameter md, List<String> collector) {
			 super.visit(md, collector);			 
			 collector.add(0, md.getNameAsString());
//			 collector.add(1, md.getBegin().toString().replace("Optional[" ,"").replace("]", ""));
			 collector.add(1, md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
			
		 }
	}
	
	private class MethodParametersCollector extends VoidVisitorAdapter<List<Object>> {
			
		 @Override
		 public void visit(MethodDeclaration md, List<Object> collector) {
			 super.visit(md, collector);
			 collector.add(0, md.getNameAsString());		
			 collector.add(1, md.getParameters().size()); 
//			 collector.add(2, md.getBegin().toString().replace("Optional[" ,"").replace("]", "")); 
			 collector.add(2, md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
		 }
	}
	
	private class FieldTypeCollector extends VoidVisitorAdapter<List<Node>> {
		
		 @Override
		 public void visit(FieldDeclaration md, List<Node> collector) {
			 super.visit(md, collector);
			 collector.add(md.getElementType());
		 }
	}
	
	private class FieldCollector extends VoidVisitorAdapter<List<String>> {
		
		 @Override
		 public void visit(FieldDeclaration md, List<String> collector) {
			 super.visit(md, collector);
			 collector.add(0, md.getVariables().get(0).getNameAsString());
//			 collector.add(md);

		 }
	}

	
	private class BlockCollector extends VoidVisitorAdapter<List<String>> {
		
		 @Override
		 public void visit(MethodDeclaration md, List<String> collector) {
			 super.visit(md, collector);
			 collector.add(0, md.getBody().toString());
			 collector.add(1, md.getNameAsString());
			 collector.add(2, md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
		 }	
	}
	
	private class MethodBlockCollector extends VoidVisitorAdapter<List<MethodDeclaration>> {
		
		 @Override
		 public void visit(MethodDeclaration md, List<MethodDeclaration> collector) {
			 super.visit(md, collector);
			 collector.add(md);

		 }	
	}
	
	private class ConstructorCollector extends VoidVisitorAdapter<List<String>> {
		 
		 @Override
		 public void visit(ConstructorDeclaration md, List<String> collector) {			 
			 super.visit(md, collector);
			 collector.add(0, md.getBody().toString());
			 collector.add(1, md.getDeclarationAsString());
//			 collector.add(2, md.getBegin().toString().replace("Optional[" ,"").replace("]", ""));
			 collector.add(2, md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
		 }
	}
	
	private class BooleanExpCollector extends VoidVisitorAdapter<List<String>> {      
		 
		 @Override
		 public void visit(BinaryExpr md, List<String> collector) {			 
			 super.visit(md, collector);
   			 if (md.toString().contains("&") || 
   					 md.toString().contains("&&") || 
   					 md.toString().contains("|") || 
   					 md.toString().contains("||") ||
   					 md.toString().contains("^")) {
   				 
   	   			 collector.add(md.toString());
   			 }

	     }
	}		
	
	private class ClassExpCollector extends VoidVisitorAdapter<List<String>> {
		 
		 @Override
		 public void visit(ClassOrInterfaceDeclaration md, List<String> collector) {			 
			 super.visit(md, collector);
			 collector.add(0, md.getNameAsString());
//			 collector.add(1, md.getBegin().toString().replace("Optional[" ,"").replace("]", ""));
			 collector.add(1, md.getBegin().toString().replace("Optional[" ,"").
					  replace("]", "").split(",")[0].replace("(line ", ""));
		 } 
	}
	
	private class ImportCollector extends VoidVisitorAdapter<List<String>> {
		 
		 @Override
		 public void visit(ImportDeclaration md, List<String> collector) {			 
			 super.visit(md, collector);
			 collector.add(md.getNameAsString());
	     } 
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
