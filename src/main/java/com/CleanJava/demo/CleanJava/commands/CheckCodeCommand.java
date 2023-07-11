package com.CleanJava.demo.CleanJava.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.CleanJava.demo.CleanJava.checkers.CharactersPerLineCheckers;
import com.CleanJava.demo.CleanJava.checkers.ConstructorLengthChecker;
import com.CleanJava.demo.CleanJava.checkers.CounterChecks;
import com.CleanJava.demo.CleanJava.checkers.DeclarationsPerLineChecker;
import com.CleanJava.demo.CleanJava.checkers.IConventionChecker;
import com.CleanJava.demo.CleanJava.checkers.LinesPerFileChecker;
import com.CleanJava.demo.CleanJava.checkers.MethodLengthChecker;
import com.CleanJava.demo.CleanJava.checkers.NamingCheker;
import com.CleanJava.demo.CleanJava.checkers.ParameterNumberChecker;
import com.CleanJava.demo.CleanJava.checkers.PrimitiveObsessionChecker;
import com.CleanJava.demo.CleanJava.entity.Setting;
import com.CleanJava.demo.CleanJava.helpers.ErrorCodeCollector;
import com.CleanJava.demo.CleanJava.helpers.VerboseMessageCreator;
import com.CleanJava.demo.CleanJava.metrics.BooleanExpressionComplexityMetric;
import com.CleanJava.demo.CleanJava.metrics.ClassComplexityMetric;
import com.CleanJava.demo.CleanJava.metrics.CyclomaticComplexityMetric;
import com.CleanJava.demo.CleanJava.metrics.LCOMMetric;
import com.CleanJava.demo.CleanJava.metrics.MetricCalculator;
import com.CleanJava.demo.CleanJava.parser.ParserFacade;

public class CheckCodeCommand  implements Command{

//	private VerboseMessageCreator verboseMessageCreator;
	private List<String> metrics;
	private List<String> conventionErrors;
	private ErrorCodeCollector errorCodeCollector;

	private final ParserFacade parser;
	private Setting setting;
	
	public CheckCodeCommand(String sourceCodePath, Setting setting) throws FileNotFoundException {
		this.parser = new ParserFacade(sourceCodePath);	
		this.metrics = new ArrayList<String>();
		this.conventionErrors = new ArrayList<String>();
		this.errorCodeCollector = ErrorCodeCollector.getInstance();
		this.setting = setting;
	}

	public List<String> getMetrics() {
		return metrics;
	}

	public List<String> getConventionErrors() {
		return conventionErrors;
	}

	@Override
	public void execute() throws FileNotFoundException, IOException{

		IConventionChecker[] conventionCheckers = { new CharactersPerLineCheckers(setting.getCharactersPerLine()),
										new ConstructorLengthChecker(setting.getConstructorlength()), 
										new LinesPerFileChecker(setting.getLinesPerFile()),
										new MethodLengthChecker(setting.getMethodlength()),
										new CounterChecks(),
										new ParameterNumberChecker(setting.getNumberOfParameters()), 
		                                new DeclarationsPerLineChecker(),
		                                new PrimitiveObsessionChecker(),
		                                new NamingCheker()};

		
		MetricCalculator[] metricChecker = {new BooleanExpressionComplexityMetric(),
											new ClassComplexityMetric(),
											new CyclomaticComplexityMetric(),
											new LCOMMetric()};
		
		
		for (MetricCalculator metricCalculator: metricChecker) {			
			metrics.add(metricCalculator.calculateMetric());			
	}
		for (IConventionChecker checker: conventionCheckers) {
			checker.check();
		}

		conventionErrors = errorCodeCollector.getErrorMessages();
	}

}
