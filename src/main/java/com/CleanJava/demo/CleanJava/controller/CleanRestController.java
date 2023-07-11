package com.CleanJava.demo.CleanJava.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CleanJava.demo.CleanJava.commands.CheckCodeCommand;
import com.CleanJava.demo.CleanJava.commands.WriteResultsInFileCommand;
import com.CleanJava.demo.CleanJava.entity.Account;
import com.CleanJava.demo.CleanJava.entity.Setting;
import com.CleanJava.demo.CleanJava.service.AccountService;
import com.CleanJava.demo.CleanJava.service.AnalysisService;

@Controller
@RequestMapping(value= "/", method = RequestMethod.GET)
public class CleanRestController {

	private AccountService accountService;
	private AnalysisService analysisService;
	
	List<String> conventionErrors = new ArrayList<String>();
	List<String> metrics = new ArrayList<String>();
	Setting currentSetting;

    
	private final String UPLOAD_DIR = "C:\\Users\\Anna\\Documents\\";

	@Autowired
	public CleanRestController(AccountService theAccountService,
			                   AnalysisService theAnalysisService) {
		accountService = theAccountService;
		analysisService = theAnalysisService;
	}
	
	
	@GetMapping("/home")
	public String goToHome(Model theModel) {
		return "homepage";
	}
	
	
	@PostMapping(value = "GuestAnalysis")
	public String goToGuestAnalysis(Model theModel) {
		
		theModel.addAttribute("defaultConstructorlength", 20);
		theModel.addAttribute("defaultMethodlength", 150);
		theModel.addAttribute("defaultCharactersPerLine", 80);
		theModel.addAttribute("defaultNumberOfParameters", 4);
		theModel.addAttribute("defaultLinesPerFile", 2000);
		theModel.addAttribute("defaultDecrationsPerLine", 1);
		theModel.addAttribute("defaulForStatements", 7);
		theModel.addAttribute("defaultNumberOfMethods", 30);
		theModel.addAttribute("defaultNumberOfFields", 20);
	
		theModel.addAttribute("username", "Hello, Guest");
		theModel.addAttribute("theDefaultSetting", new Setting());

		return "guest_analysis_page"; 

	}
	
	
	@PostMapping(value = "UserAnalysis")
	public String goToUserAnalysis(Model theModel,
								   HttpSession session,
								   HttpServletRequest request) { 

		String username = (String) session.getAttribute("username");

		Account userAccount = accountService.findAccountByUserName(username);
		request.getSession().setAttribute("userID", userAccount.getId());

		List<Setting> userSettings = analysisService.findSettingsByAccountID(userAccount.getId());

		Setting theNewSetting = new Setting();
		theNewSetting.setAccount(userAccount);
		theModel.addAttribute("theNewSetting", theNewSetting);
		theModel.addAttribute("userSettings", userSettings);

		theModel.addAttribute("defaultConstructorlength", 20);
		theModel.addAttribute("defaultMethodlength", 150);
		theModel.addAttribute("defaultCharactersPerLine", 80);
		theModel.addAttribute("defaultNumberOfParameters", 4);
		theModel.addAttribute("defaultLinesPerFile", 2000);
		theModel.addAttribute("defaultDecrationsPerLine", 1);
		theModel.addAttribute("defaulForStatements", 7);
		theModel.addAttribute("defaultNumberOfMethods", 30);
		theModel.addAttribute("defaultNumberOfFields", 20);

		theModel.addAttribute("username", "Hello, " + username);

		return "user_checker_page"; 
	}

	
	@PostMapping(value = "/checkCode")
    public String checkTheCode(    
            @RequestParam(name = "settingId", required = false) Integer chosenSettingID,
            @RequestParam("inputCode") MultipartFile file,	    				
			RedirectAttributes redirect, Model theModel,
			HttpSession session,
			HttpServletRequest request) throws IOException{

		String username = (String) session.getAttribute("username");

        if (file.isEmpty()) {
            theModel.addAttribute("file_error", "Please select a file. ");
            if (username==null) {
        		redirect.addFlashAttribute("username", username);
        		theModel.addAttribute("file_error", "Please select a file. ");
            	return "redirect:/GuestAnalysis";
            }else {
            	redirect.addFlashAttribute("username", username);
            	theModel.addAttribute("file_error", "Please select a file. ");
            	return "redirect:/UserAnalysis";
            }           
        }
        
       conventionErrors.clear(); metrics.clear();

		Setting checkSetting;
		if (chosenSettingID == null) {
			checkSetting = new Setting();
		}else {
			checkSetting = analysisService.findSettingBySettingID(chosenSettingID);
		}
//		currentSetting = checkSetting;
        String sourceFileName = StringUtils.cleanPath(file.getOriginalFilename());    

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + sourceFileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		request.getSession().setAttribute("currentSetting", checkSetting.toString());
		request.getSession().setAttribute("sourceFileName", sourceFileName);
        
		CheckCodeCommand checkCodeCommand = new CheckCodeCommand(UPLOAD_DIR + sourceFileName, checkSetting); 
        checkCodeCommand.execute();
      
        this.conventionErrors = checkCodeCommand.getConventionErrors();
        this.metrics = checkCodeCommand.getMetrics();      
		
		theModel.addAttribute("conventionErrors", conventionErrors);
	    theModel.addAttribute("booleanExpressionComplexity", metrics.get(0));
	    theModel.addAttribute("classComplexity", metrics.get(1));  
	    theModel.addAttribute("cyclomaticComplexity", metrics.get(2));    
	    theModel.addAttribute("lcomMetric", metrics.get(3));
	    	    
	    try {

	            // default StandardCharsets.UTF_8
	            String file_contents = Files.readString(Paths.get(UPLOAD_DIR + sourceFileName));
	    	    theModel.addAttribute("source", file_contents);

	    	    if (session.getAttribute("userID")!=null) {
		    	    theModel.addAttribute("user", session.getAttribute("userID"));
	    	    }

	     } catch (IOException e) {
	            e.printStackTrace();
	     }

		return "result_page";
	}
	
	
	@PostMapping(value = "/deleteSetting")
    public String deleteSetting(@RequestParam(value="settingId") int settingID,
					            Model theModel,
					            RedirectAttributes redirect,
					            HttpSession session) {

		String username = (String) session.getAttribute("username");
		analysisService.deleteSettingByID(settingID);	
		Account thisAccount = accountService.findAccountByUserName(username);
       
		List<Setting> userSettings = analysisService.findSettingsByAccountID(thisAccount.getId());
		theModel.addAttribute("userSettings", userSettings);			
		redirect.addFlashAttribute("username", username);

		return "redirect:/UserAnalysis";

	}	
	 
	
	@PostMapping(value = "/saveNewSetting")
	public String submitNewSetting(@Valid @ModelAttribute("theNewSetting") Setting setting, 
								  final BindingResult result, Model theModel,
								  RedirectAttributes redirect,
								  HttpSession session) {
		
		String username = (String) session.getAttribute("username");
		int userID = (int) session.getAttribute("userID");

		if (result.hasErrors()) {
    		theModel.addAttribute("message", "there is something wrong!");
    		redirect.addFlashAttribute("username", username);
            return "redirect:/UserAnalysis";
		}
		
		Account userAccount = accountService.findAccountByID(userID);
		setting.setAccount(userAccount);
		setting.setSetting_id(0);

		analysisService.saveNewSetting(setting);
        List<Setting> userSettings = analysisService.findSettingsByAccountID(userID);
	
		theModel.addAttribute("userSettings", userSettings);
		
		redirect.addFlashAttribute("username", username);

        return "redirect:/UserAnalysis";

	}

	
	@PostMapping(value = "/download")
	public String download(HttpSession session,
			       HttpServletRequest request) throws FileNotFoundException, IOException {

    	
		String username = (String) session.getAttribute("username");
		String sourceFileName = (String) session.getAttribute("sourceFileName");
		String currentSetting = (String) session.getAttribute("currentSetting");

		WriteResultsInFileCommand resultsFileWriter 
		                      = new WriteResultsInFileCommand(currentSetting,
		                    		  conventionErrors,
		                    		  metrics, 
		                    		  username,
									  sourceFileName);
		
		resultsFileWriter.execute();

		return "file_downloaded.html";
	}

}
