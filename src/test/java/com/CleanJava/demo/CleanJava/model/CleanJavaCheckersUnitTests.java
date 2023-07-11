package com.CleanJava.demo.CleanJava.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import com.CleanJava.demo.CleanJava.checkers.CharactersPerLineCheckers;
import com.CleanJava.demo.CleanJava.checkers.ConstructorLengthChecker;
import com.CleanJava.demo.CleanJava.checkers.CounterChecks;
import com.CleanJava.demo.CleanJava.checkers.DeclarationsPerLineChecker;
import com.CleanJava.demo.CleanJava.checkers.LinesPerFileChecker;
import com.CleanJava.demo.CleanJava.checkers.MethodLengthChecker;
import com.CleanJava.demo.CleanJava.checkers.NamingCheker;
import com.CleanJava.demo.CleanJava.checkers.ParameterNumberChecker;
import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

@ExtendWith(MockitoExtension.class)
public class CleanJavaCheckersUnitTests {
		
	  private static final String SRC_PATH = "C:\\Users\\Anna\\Desktop\\"
	  		+ "CleanJava\\src\\test\\java\\com\\CleanJava\\demo\\CleanJava"
	  		+ "\\UnitBackEndTests\\create.java";
	  
	  private HashMap<String,Pair<String, String>> errorCodes;
	  private ErrorCodeCollector errorCodeCollector;
	  
      private ParserFacade parser;
	  private ParameterNumberChecker paramaterNumberChecker;
	  private CharactersPerLineCheckers charactersPerLineCheckers;
	  private LinesPerFileChecker linesPerFileChecker;
	  private CounterChecks counterChecks;
	  private ConstructorLengthChecker constructorLengthChecker;
	  private DeclarationsPerLineChecker declarationsPerLineChecker;
	  private MethodLengthChecker methodLengthChecker;
 	 	
	  @BeforeEach
	  void initUseCase() throws IOException {
		  parser = new ParserFacade(SRC_PATH); 
		  errorCodeCollector = ErrorCodeCollector.getInstance();	    
		  errorCodes = errorCodeCollector.getErrorCodes();
	  }
	  
	  
	  @Test
	  @DisplayName("testMethodLengthChecker")
	  void testMethodLengthChecker() throws IOException {
		  
		  methodLengthChecker = new MethodLengthChecker(7);
		  methodLengthChecker.check();

	      String actualMethodLength = errorCodes.get("TooLengthyMethod").getFirst();
	      String expectedMethodLength = "createMethodOne] is too lengthy with 43 lines.";
	      assertEquals(actualMethodLength, expectedMethodLength);
	  }
	  
	  
	  @Test
	  @DisplayName("testParameterNumberChecker")
	  void testParameterNumberChecker() throws IOException {
		  
		  paramaterNumberChecker = new ParameterNumberChecker(7);
	      paramaterNumberChecker.check();

	      String actualParameterValue = errorCodes.get("TooManyParamatersInMethod").getFirst();
	      assertEquals(actualParameterValue, "createMethodWithTooManyParamaters] has 9");
	  }	
	  
	  
	  @Test
	  @DisplayName("testTooManyFieldDeclarationsChecker")
	  void testTooManyFieldDeclarationsInAClassChecker() throws IOException {
		  
		  counterChecks = new CounterChecks();
		  counterChecks.check();

		  String numberOfFields = errorCodes.get("TooManyFieldDeclarationsInAClass").getFirst();
	      assertEquals(numberOfFields, "29");

	  }
	  
	  @Test
	  @DisplayName("testTooManyForLoopsChecker")
	  void testTooManyForLoopsChecker() throws IOException {
		  counterChecks = new CounterChecks();
		  counterChecks.check();

		  String forLoops = errorCodes.get("TooManyForLoopsInAClass").getFirst();
	      assertEquals("10", forLoops);
	      
	  }
	  
	  
	  @Test
	  @DisplayName("testMethodDeclarationCounter")
	  void testMethodDeclarationsCounter() throws IOException {
		  counterChecks = new CounterChecks();
		  counterChecks.check();

		  String methodDeclarations = errorCodes.get("TooManyMethodDeclarationsInAClass").getFirst();
		  assertEquals("0", methodDeclarations);
	  }
	  
	  
	  @Test
	  @DisplayName("testMoreThanOneClassDeclarationInAFileChecker")
	  void testMoreThanOneClassDeclarationInAFileChecker() throws IOException {
		  counterChecks = new CounterChecks();
		  counterChecks.check();

		  String classDeclarations = errorCodes.get("MoreThanOneClassDeclarationInAFile").getFirst();	      
	      assertEquals(classDeclarations, "2");
	  }
	  
	  
	  @Test
	  @DisplayName("testConstructorLengthChecker")
	  void testConstructorLengthChecker() throws IOException {
		  
		  constructorLengthChecker = new ConstructorLengthChecker(30);
		  constructorLengthChecker.check();	 

		  String actualConstructorLength = errorCodes.get("TooLengthyConstructor").getFirst();
		  assertEquals(actualConstructorLength, "37");
		  
	  }
	  
	  
	  @Test
	  @DisplayName("testDeclationsPerLineChecker")
	  void testDeclationsPerLineChecker() throws IOException {
		  
		  declarationsPerLineChecker = new DeclarationsPerLineChecker();
		  declarationsPerLineChecker.check();		  
		
		  String actualDeclarationsPerLine = errorCodes.get("TooManyVariableDeclarationsPerLine").getFirst();
		  String expectedDeclarations = "3";
		  
		  assertEquals(actualDeclarationsPerLine, expectedDeclarations);
	  }
	  
	  
	  @Test
	  @DisplayName("testCharactersPerLineChecker")
	  void testCharactersPerLineChecker() throws IOException {
		  
		  charactersPerLineCheckers = new CharactersPerLineCheckers(80);
		  charactersPerLineCheckers.check();
	
		  String charactersPerLine = errorCodes.get("TooManyCharactersInOneLine").getFirst();
		  assertEquals(charactersPerLine, "142");
	  }
	  
	  
	  @Test
	  @DisplayName("testLinesPerFileChecker")
	  void testLinesPerFileChecker() throws IOException {
		  
		  linesPerFileChecker = new LinesPerFileChecker(50);
		  linesPerFileChecker.check();		  
	
		  String actualLinesPerFile = errorCodes.get("TooLengthyFile").getFirst();
		  assertEquals(actualLinesPerFile, "250");
	  }
	  
}
