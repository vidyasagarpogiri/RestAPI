package KHScripts.KMail;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import TM.Login;
import DataSource.Datatable;
import DataSource.GlobalData;
import components.ApplicationHeader;
import components.Browser;
import components.Config;
import components.TimeDate;
import components.Verify;
import components.Waits;

public class CopyKH_Kmail_KH1331 {
	
	/************************************************************************************
	'Class name                     : 	KH_Kmail_KH1330
	'JIRA ID						:	KH-1330
	'Author							:	Vidya
	'Description                    : 	Test script to validate the Login user is redirected to  Kmail instead of Hotbox if there is any unread mail.
	'Input Parameters           	: 	subject
	'Output Parameters        		: 	
	'Assumptions                    : 	
	'Use                            : 	The Following test creates new user, logins again and make sure after login it is directed to Kmail.
	'Tags                           : 	Regression
	 ************************************************************************************/
	private static WebDriver driver;
    //private WebDriverWait wait = new WebDriverWait(driver,10);
    
	public static void main(String[] args) throws Exception {
		KH_Kmail_KH1330();
	}
		
	  @BeforeClass
	    public static void setup () {
	        //Chrome Driver Decleration
	       // String chromeDriverLocation = "C:\\Selenium\\drivers\\chrome\\chromedriver.exe";
	        //String chromeDriverLocation = "C:\\Users\\Admin\\Documents\\ATF\\ATF\\drivers\\chromedriver.exe";
	        //System.out.println("Chrome Driver: " + chromeDriverLocation );
	        //System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

	        //Chrome Options
	       // ChromeOptions options = new ChromeOptions();
	       // options.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36");
	       // options.addArguments("--start-maximized");
	       // driver = new ChromeDriver(options);
	    }
	  
	@Test(groups = { "KH_Regression", "KH_Clinical", "subclinicalkh" })
	public static void KH_Kmail_KH1330() throws Exception {
	
		//@Open Application and submit credentials
		AM.Login.openAppAndSubmitCredentials();
		//@ Get Current WebDriver
		WebDriver driver = Browser.getDriver();
		//@Import Test data sheet
		String dataFileName = "UserCreation\\KH_Kmail_KH1330.json";
		String dataSheetName = "KH_Kmail_KH1330";
		Datatable.loadDataSheet(dataFileName, dataSheetName );
	
		Datatable.getValue("CreateUser").equals("Yes");
		
		String subject = "Roles have been Added/Removed from your account";
		
		AM.Menu.TopMenu.select(driver, "Go To/HotBox");
		System.out.println("1");
		Thread.sleep(3000);
		Verify.homepage(driver);
		System.out.println("2");
		Thread.sleep(3000);
		WebElement a = driver.findElement(By.xpath(".//*[@class='signature-capture' and @patienttaskkey='133895728']"));
		//WebElement b = driver.findElement(By.xpath(".//*[@class='signature-display' and @patienttaskkey='71378139']"));
		System.out.println("Bool Status : " +a.isDisplayed());
		System.out.println("3");
		a.click();
		Thread.sleep(3000);
		WebElement c = driver.findElement(By.id("signature-get-signature"));
		c.click();
		System.out.println("4");
		Thread.sleep(2000);
		//b.click();
		System.out.println("5");
		Thread.sleep(1500);
		WebElement d = driver.findElement(By.id("unableToSign"));
		d.click();
		System.out.println("6");
		Thread.sleep(2000);
		WebElement e = driver.findElement(By.id("signature-save"));
		System.out.println("7");
		e.click();
		Thread.sleep(4000);
		System.out.println("8");
		WebElement f = driver.findElement(By.id("signature-close")); 
		f.click();
		Verify.homepage(driver);
	}
	   /*****************************************************************
		//@@Step  :To create new user
			if (Datatable.getValue("CreateUser").equals("Yes")) {

				//@Step :To load sheet and add new User
				Datatable.loadDataSheet(dataFileName, "CreateUser");            
				//@Step :To select the required menu
				KH.Menu.TopMenu.select(driver, "File/New/User");
				KH.User.AddNewUser.fillAddNewUser(driver);
				KH.User.AddNewUser.validateAndCreateUser(driver);
				KH.User.UserManager.completeUserSetup(driver);
				KH.Roles.AddRoles.addBasicRolesToNewUser(driver);
				ApplicationHeader.logout(driver);
			}

			else{

				Assert.fail("Create User - Yes is not given in the data sheet");
			}
		
			KH.User.UserLogin.submitCredentials(driver, GlobalData.getUserName(), GlobalData.getTemporaryPwd());
			KH.User.UserLogin.updateProfile(driver);	
			KH.User.UpdateProfile.updateUserProfile(driver);
			Waits.forBrowserLoad(driver);
		
			
			// Navigates to the default page i.e Hotbox 
			Waits.forHomePage(driver);
		    ApplicationHeader.logout(driver);
			
		    //Logging into the same user and verify that it is redirected to Kmail since there are unread emails for the user
		    KH.User.UserLogin.submitCredentials(driver, GlobalData.getUserName(), Datatable.getValue("Password"));
		    Waits.forPageWait(driver);
		    Verify.exactPageTitle(driver, "K-Mail | Kinnser Software");
		    
		    //Selecting the unread mail & opening the email.
		    KH.Kmail.Inbox.selectMailBySubject(driver, subject);
		    Assert.assertTrue(true, "Unread Email is present in the KMail");
		    KH.Kmail.Inbox.openMailBySubject(driver, subject);
		    if(driver.getPageSource().contains(subject))
	        {
		    Verify.exactPageTitle(driver, "K-mail | Kinnser Software");
		    Assert.assertTrue(true, "Email is read in the KMail");
	        }
		    KH.Menu.TopMenu.select(driver, "Go To/Inbox");
		    Waits.forPageWait(driver);
		    Verify.exactPageTitle(driver, "K-Mail | Kinnser Software");
		    ApplicationHeader.logout(driver);
		    
		    //Logging into the same user and verify that it is redirected to default page since there are no unread emails for the user
		    KH.User.UserLogin.submitCredentials(driver, GlobalData.getUserName(), Datatable.getValue("Password"));
		    Verify.exactPageHeader(driver, "Hotbox");
		    
	}*/
	
	@AfterClass(alwaysRun = true)
  	public static void Teardown() {
  		components.Browser.teardownTest();
  	}
   

}