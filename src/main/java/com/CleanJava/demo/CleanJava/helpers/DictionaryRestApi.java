package com.CleanJava.demo.CleanJava.helpers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

//https://medium.com/swlh/getting-json-data-from-a-restful-api-using-java-b327aafb3751

public class DictionaryRestApi {
	
	private final String MerriamWebsterURL_first = "https://dictionaryapi.com/api/v3/references/collegiate/json/";
	private final String MerriamWebsterURL_second = "?key=6460e06c-fef2-45d7-8053-5b6e3a3d87a6";
 
	public char identifyWord(String keyword) throws IOException {
		
		  URL url = new URL(MerriamWebsterURL_first + keyword + MerriamWebsterURL_second);
	
		  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
          conn.setRequestMethod("GET");
          conn.connect();

          //Getting the response code
          int responsecode = conn.getResponseCode();

          if (responsecode != 200) {
              throw new RuntimeException("HttpResponseCode: " + responsecode);
          } else {

              String inline = "";
              Scanner scanner = new Scanner(url.openStream());

              // Write all the JSON data into a string using a scanner
              while (scanner.hasNext()) {
                  inline += scanner.nextLine();

              }
              int indexO = inline.indexOf("fl") + 5;
              char wordType = inline.charAt(indexO);
              scanner.close();
              return wordType;
        }
	}

}
