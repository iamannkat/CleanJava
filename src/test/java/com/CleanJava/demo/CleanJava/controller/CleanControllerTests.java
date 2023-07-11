package com.CleanJava.demo.CleanJava.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.entity.Setting;
import com.CleanJava.demo.CleanJava.service.AccountService;
import com.CleanJava.demo.CleanJava.service.AnalysisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) // to ignore 403 errors
class CleanControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private AnalysisService analysisService;
    
    Account account;

    @BeforeEach
    void setUp() {

    }
//
//    @AfterAll
//    void tearDown() {
//    	accountRepository.deleteAccountByID(account.getId());
//    }
//    
    
    @Test
    void canShowMyHomePage() throws Exception {
    	mvc.perform(get("/home")
      	      .accept(MediaType.APPLICATION_JSON_VALUE))
    	      .andExpect(status().isOk());
    }
    
    
    @Test
    void canLoadGuestAnalysisPage() throws Exception {

        // when
        mvc.perform(post("/GuestAnalysis")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());    
    }
    
    @Test
    void canLoadUserAnalysisPage() throws Exception {

	     // when
	     mvc.perform(post("/UserAnalysis")
	                     .contentType(MediaType.APPLICATION_JSON)
			      	     .sessionAttr("username", "annakat"))
	             .andExpect(status().isOk());
	
	     // then
	     account = accountService.findAccountByUserName("annakat");
         assertThat(account.getUsername()).isNotNull();
         assertEquals(account.getUsername(), "annakat");
         assertEquals(account.getId(), 1);	
    }
    
    @Test
    void canCreateNewSetting() throws Exception {

	     // when
	     mvc.perform(post("/saveNewSetting")
	                     .contentType(MediaType.APPLICATION_JSON)
			      	     .sessionAttr("username", "annakat")
			      	     .sessionAttr("userID", 1)
			      	     .flashAttr("theNewSetting", new Setting()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/UserAnalysis"));
	     
    }
    
    
  
    @Test
    void canDeleteSetting() throws Exception {

	     // when
	     mvc.perform(post("/deleteSetting")
	                     .contentType(MediaType.APPLICATION_JSON)
			      	     .sessionAttr("username", "annakat")
			      	     .requestAttr("settingId", 5)
			      	     .param("settingId", "5"))
	        .andExpect(status().isFound())
			.andExpect(redirectedUrl("/UserAnalysis"));
	
	     // then
	     
	     assertThat(analysisService.findSettingBySettingID(5)).isNull();
//         assertThat(account.getUsername()).isNotNull();
//         assertEquals(account.getUsername(), "annakat");
    }
    
    
    @Test
    void canPerfomAnalysis() throws Exception {
    	
        MockMultipartFile file 
        = new MockMultipartFile(
          "file", 
          "hello.txt", 
          MediaType.TEXT_PLAIN_VALUE, 
          "Hello, World!".getBytes()
        );
 

//        final byte[] file_bytes = Files.readAllBytes(Paths.get("C:\\Users\\Anna\\Desktop\\CleanJava\\src\\test\\java\\com\\CleanJava\\demo\\CleanJava\\RestControllerTests\\SomeExample.java"));
        Setting setting = new Setting();
        setting.setSetting_id(33);

        mvc.perform(multipart("/CheckCode").file(file)
	                     .contentType(MediaType.APPLICATION_JSON)
			      	     .sessionAttr("username", "annakat")
			      	     .requestAttr("settingId", setting.getSetting_id())
			      	     .requestAttr("inputCode", file))
                   .andExpect(status().isNotFound())
		      	   .andExpect(content().string(""));		      			   
		      	 
    }
    
    @Test
    void canPerfomDownload() throws Exception {
    	
    	List<String> conventionErrors = new ArrayList<String>();
    	List<String> metrics = new ArrayList<String>();
    	conventionErrors.add("hello");
    	conventionErrors.add("oohh");
    	metrics.add("hgf");
    	metrics.add("hghghghghghghg");
    	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    	String currentMetrics = ow.writeValueAsString(metrics);
    	String currentErrors = ow.writeValueAsString(conventionErrors);

    	Setting thisSetting = new Setting();
	     // when
	     mvc.perform(get("/download")
	                     .contentType(MediaType.APPLICATION_JSON)
	                     .content(currentMetrics)
	                     .content(currentErrors)
			      	     .sessionAttr("username", "annakat")
			      	     .sessionAttr("sourceFileName", "someExample.java")
			      	     .sessionAttr("currentSetting", thisSetting.toString()))
	        .andExpect(status().isOk());
    }
    
}