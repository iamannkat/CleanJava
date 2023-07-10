package com.CleanJava.demo.CleanJava.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Command {
	public void execute()throws IOException, FileNotFoundException;
}
