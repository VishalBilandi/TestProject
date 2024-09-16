//1.Individual
//2.With CPA
//3.Without Add ons
//4.Without finance
//5.Claim yes




package Comprehensive;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.Launch_browser01;
import utils.WebDriverUtils;
public class TC_07 extends Launch_browser01
{
	    private WebDriverUtils util;
	    JavascriptExecutor js;
	

	    @BeforeClass
	    public void setup()
	    {
	        super.setup(); // Ensure superclass setup is also called
	        util = new WebDriverUtils(driver); // Initialize WebDriverUtils
	    }

	    @Test(priority = 0)
	    public void clickCarModule() 
	    {
	        By carModule = By.xpath("//li[@ng-click=\"pageNavigation('car')\"]");
	        util.clickElement(carModule);
	    }

	    @Test(priority = 1, dependsOnMethods = "clickCarModule")
	    public void dontknowcar()
	    {
	        By old_car = By.xpath("//div[@class='col-lg-5 col-sm-6 buy-link']//a[@class='CarBttn']");
	        util.waitForElement(old_car);
	        driver.findElement(old_car).click();
	    }

	    @Test(priority = 2, dependsOnMethods = "dontknowcar")
	    public void comprehensive() throws InterruptedException
	    {
	        By comp = By.xpath("//div[@id='ComprehensiveP']");
	        util.waitForElement(comp);
	        driver.findElement(comp).click();
	    }
	    
	    
//	    @Test(priority = 3, dependsOnMethods = "comprehensive")
//	    public void exisitngTP_popup()
//	    
//	    {
//	    	By tp_popup = By.xpath("//div[@class='checkbox tremcondition']//label[@id='trnCndtion']");
//	    	util.waitForElement(tp_popup);
//	    	driver.findElement(tp_popup).click();
//	    	
//	       	By popup_okbtn = By.xpath("//button[@ng-disabled='standlonform.confirm.$error.required']");
//	    	util.waitForElement(popup_okbtn);
//	    	driver.findElement(popup_okbtn).click();
//
//	    }
//	    
	    
	    
	    @DataProvider(name = "rtoData")
	    public Object[][] rtoData() {
	        return new Object[][] {
	            { "MH01" },
	  
	        };
	    }
	    
	    @Test(priority = 4, dependsOnMethods = "comprehensive", dataProvider = "rtoData")
	    public void rto_num(String rt) throws InterruptedException 
	    
	    {
	    	
	     		 	By RTO=By.xpath("//input[@id='ReRegno']");
	     		 	driver.findElement(RTO).click();
	     		 	util.sendKeysToElement(RTO, rt);
	     		 	

	     		   Thread.sleep(300);
	     	 	   List<WebElement> elements = driver.findElements((By.xpath("//ul[@id='ui-id-3']//li")));
	    	 	    System.out.println("Number of elements:" +elements.size());
	     	        for (int i=0; i<elements.size();i++) {
	     	     	   
	     	     	   System.out.println(elements.get(i).getText());
	     	     	  
	     	     	   if(elements.get(i).getText().equals("MH01 (Mumbai)"))
	     	     	   {
	     	     		   elements.get(i).click();
	     	     		   break;             
	     	            }
	     	     	    else
	             	   {
	             		   System.out.println("Not working");
	             	   }
	             	   
	             	   }
	          	    }
	     	        
	    
	    
	    
	    @Test(priority = 5, dependsOnMethods = "rto_num")
	    public void reg_date()
	    {
	    	By reg_calender = By.xpath("//input[@id='Reregdate']");
	    	util.waitForElement(reg_calender);
	    	driver.findElement(reg_calender).click();
	    	
	    	By year = By.xpath("//select[@aria-label='Select year']");
	    	Select expiry_year = new Select(driver.findElement(year));
	    	expiry_year.selectByVisibleText("2018");
	    	
	       	By month = By.xpath("//select[@aria-label='Select month']");
	    	Select expiry_month = new Select(driver.findElement(month));
	    	expiry_month.selectByVisibleText("Nov");
	    	
	       	By date = By.xpath("//a[normalize-space()='1']");
	       	util.waitForElement(date);
	        driver.findElement(date).click();
	    }

	    @DataProvider(name = "vehicleno")
	    public Object[][] veh_num() {
	        return new Object[][] {
	            { "MH01-AB-2558" },
	        };
	    }
	  
	    @Test(priority = 6, dependsOnMethods = "reg_date", dataProvider = "vehicleno")
	    public void vehicle_num(String vn)
	    {
	    	By veh_num  = By.xpath("//input[@id='ReRegnoidd']");
	    	util.waitForElement(veh_num);
	    	util.sendKeysToElement(veh_num, vn);
	    }
	    
	    
	    @DataProvider(name = "makeData")
	    public Object[][] makeData() {
	        return new Object[][] {
	            { "Hond" },
	        };
	    }

	    
	    @Test(priority = 7, dependsOnMethods = "vehicle_num", dataProvider = "makeData")
	    public void make(String mke) throws InterruptedException {
	        By makeLocator = By.xpath("//input[@id='Remake']");
	        util.sendKeysToElement(makeLocator, mke);

	        By makeListLocator = By.xpath("//ul[@id='ui-id-2']//li");
	        

	        Thread.sleep(300);
	        List<WebElement> makeList = driver.findElements(makeListLocator);
	        
	        for (int i=0; i<makeList.size();i++) {
	        System.out.println(makeList.get(i).getText());
	        
	        
	   	   if(makeList.get(i).getText().equals("Honda"))
	   	   {
	   		makeList.get(i).click();
	   		   break;             
	          }
	   	    else
	  	   {
	  		   System.out.println("Not working");
	  	   }
	  	   
	  	   }
		    }


	    @Test(priority = 8, dependsOnMethods = "make")
	    public void model() throws InterruptedException 
	    
	    {
	    	By mdl = By.xpath("//select[@id='Remodels']");
	    	util.waitForElement(mdl);
	    	driver.findElement(mdl).click();
	    	driver.findElement(By.xpath("//option[@value='string:City (Petrol)']")).click();
	    	
	    }

	    @Test(priority = 9, dependsOnMethods = "model")
	    public void variant() 
	    {
	    	By vari = By.xpath("//select[@id='revariant']");
	    	util.waitForElement(vari);
	    	driver.findElement(vari).click();
	    	driver.findElement(By.xpath("//option[@value='string:1.3 LXI (1343 CC)']")).click();
	        
	    }
	    
	    @Test(priority = 10, dependsOnMethods = "variant")
	    public void previous_pol()
	    {
	    	By pre_poldrpdown = By.xpath("//select[@id='PolicyDetailsType']");
	    	util.waitForElement(pre_poldrpdown);
	    	Select drpdwn = new Select(driver.findElement(pre_poldrpdown));
	    	drpdwn.selectByVisibleText("Not Expired");
	    }
	    
	    @Test(priority = 11, dependsOnMethods = "previous_pol")
	    public void existing_insurer()
	    {
	    	By exis_insure = By.xpath("//select[@id='PInsurer']");
	    	util.waitForElement(exis_insure);
	    	Select insurer = new Select(driver.findElement(exis_insure));
	    	insurer.selectByVisibleText("Cholamandalam General Insurance Co. Ltd.");
	    }
	    

	    
	    @Test(priority = 12, dependsOnMethods = "existing_insurer")
	    public void OD_Expirydate()
	    {
	    	By expiry_calender = By.xpath("//input[@id='policyExdate']");
	    	util.waitForElement(expiry_calender);
	    	driver.findElement(expiry_calender).click();
	    	
	    	By year = By.xpath("//select[@aria-label='Select year']");
	    	Select expiry_year = new Select(driver.findElement(year));
	    	expiry_year.selectByVisibleText("2024");
	    	
	       	By month = By.xpath("//select[@aria-label='Select month']");
	    	Select expiry_month = new Select(driver.findElement(month));
	    	expiry_month.selectByVisibleText("Nov");
	    	
	     	By date = By.xpath("//a[normalize-space()='1']");
	        driver.findElement(date).click();
	    	 	
	    }
	    

	    
	    @Test(priority = 13, dependsOnMethods = "OD_Expirydate")
	    public void owner_type () 
	    {
	    	
	    	
	        By indi = By.xpath("//div[@ng-class=\"{'boxmainhighlight' : Re_Item.VehicleOwnedBy == 'I'}\"]");
	        
	        util.waitForElement(indi);
	        driver.findElement(indi).click();
	        
	    }
	    
	    
	    @Test(priority = 14, dependsOnMethods = "owner_type")
	    public void CPA_No () {
	        By cpa = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/section[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[12]/div[1]/div[1]");
	        util.waitForElement(cpa);
	        driver.findElement(cpa).click();
	    }
	    
	   
	    @Test(priority = 15, dependsOnMethods = "CPA_No")
	    public void ownership_no ()
	    {
	        By owner = By.xpath("//div[@ng-class=\"{'boxmainhighlight' : Re_Item.OwnershipChange == 'No'}\"]");
	        util.waitForElement(owner);
	        driver.findElement(owner).click();
	    }
	    
	    @Test(priority = 16, dependsOnMethods = "ownership_no")
	    public void claim_yes ()
	    {
	        By claim = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/section[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[14]/div[1]/div[1]");
	        util.waitForElement(claim);
	        driver.findElement(claim).click();
	        
	    }
	    
	    
//	    @Test(priority = 16, dependsOnMethods = "claim_No")
//	    public void ncb_drpdwn ()
//	    {
//	    	
//	        By ncb = By.xpath("//select[@id='NCBper']");
//	        util.waitForElement(ncb);
//	        
//	        Select ncb_percent = new Select(driver.findElement(ncb));
//	        ncb_percent.selectByVisibleText("20%");
//	    }
	    
	    
	  @DataProvider(name = "fullname")
	  public Object[][] fullname() {
	      return new Object[][] {
	          { "Vishal Bilandi" },
	      };
	  }

	  @Test(priority = 17, dependsOnMethods = "claim_yes", dataProvider = "fullname")
	  public void fullname(String fn)
	  {
	      By fullNameLocator = By.xpath("//div[@class='col-sm-6 col-lg-4 col-xs-12']//input[@id='Name']");

	          js = (JavascriptExecutor) driver;
	         js.executeScript("window.scrollBy(0, 300);");
	          util.waitForElement(fullNameLocator);
	          driver.findElement(fullNameLocator).sendKeys(fn);

	  }
	  

	  @DataProvider(name = "Mobilenum")
	  public Object[][] mobilenum() {
	      return new Object[][] 
	    		  {
	          { "9311506762" },
	      };
	  }

	  
	  @Test(priority = 18, dependsOnMethods = "fullname", dataProvider = "Mobilenum")
	  public void mobnum(String mn)
	  {
	      By mobile_number = By.xpath("//div[@class='col-sm-6 col-lg-4 col-xs-12']//input[@placeholder='Mobile']");
	      util.waitForElement(mobile_number);
	      driver.findElement(mobile_number).sendKeys(mn);
	  }

	  
	  @Test(priority = 19, dependsOnMethods = "mobnum")
	  public void term_con()
	  {
	      By tc = By.xpath("//form[@name='donotknowregistrationForm']//label[@id='trnCndtion']");
	      util.waitForElement(tc);
	      driver.findElement(tc).click();
	  } 
	    
	  
	  @Test(priority = 20, dependsOnMethods = "term_con")
	  public void get_quote()
	  {
	      By qut_btn = By.xpath("//a[@id='recarfinalstep']");
	      util.waitForElement(qut_btn);
	      driver.findElement(qut_btn).click();
	  } 

	  
	  
	  
	  @Test(priority = 21, dependsOnMethods = "get_quote")
	  public void premium()
	  {
	      By pre_btn = By.xpath("//button[@id='buy_Reliance']");
	      util.waitForElement(pre_btn);
	      driver.findElement(pre_btn).click();
	  }
	  
	    
	    @Test(priority = 22, dependsOnMethods = "premium")
	    public void kyc_button() {
	    	
	    	By kyc_btn = By.xpath("//label[@for='KYCYes']//span");
	    	util.waitForElement(kyc_btn);
	    	
	    	util.clickElement(kyc_btn);
	    	
	    }
	    
	    @DataProvider(name = "ckyc_number")
	    public Object[][] ckyc() {
	        return new Object[][] {
	            { "10085792349036" },
	        };
	    }
	    
	    @Test(priority = 23, dependsOnMethods = "kyc_button", dataProvider = "ckyc_number")
	    public void ckyc_number(String num) {
	    	
	    	By ckyc_no = By.xpath("//input[@id='dckycNumber']");
	    	util.waitForElement(ckyc_no);
	    	{
	    	util.sendKeysToElement(ckyc_no, num);
	    	}
	    }
	    
	    @Test(priority = 24, dependsOnMethods = "ckyc_number")
	    public void dob_calender()

	    {
	    	By calender = By.xpath("//input[@id='kycDob']");
	    	util.waitForElement(calender);
	    	driver.findElement(calender).click();  //clicked
	    	
	    	Select year = new Select(driver.findElement((By.xpath("//select[@aria-label='Select year']"))));   //select year
	        year.selectByVisibleText("1996");
	        
	        Select month = new Select(driver.findElement((By.xpath("//select[@aria-label='Select month']"))));   //select year
	        month.selectByVisibleText("Sep");
	        
	        driver.findElement(By.xpath("//a[normalize-space()='14']")).click();
	    }
	    
	    @DataProvider(name = "enter_email")
	    public Object[][] Verifykyc() {
	        return new Object[][] {
	            { "swapqa@simsontech.onmicrosoft.com" },
	        };
	    }
	    
	    @Test(priority = 25, dependsOnMethods = "dob_calender", dataProvider = "enter_email" )
	    public void email(String em)

	    {
	    	By email = By.xpath("//input[@id='Email']");
	    	util.waitForElement(email);
	    	driver.findElement(email).sendKeys(em);
	    }
	    
	    @Test(priority = 26, dependsOnMethods = "email")
	    public void Verify_kyc()

	    {
	    	By verifykyc = By.xpath("//button[@id='KYCSubmit']");
	    	util.waitForElement(verifykyc);
	    	driver.findElement(verifykyc).click();
	    }


	    @Test(priority = 27, dependsOnMethods = "Verify_kyc")
	    public void Marital () throws InterruptedException

		{
			 By mar = By.xpath("//select[@id='MaritialStatusdrpdwn']"); 
			 util.waitForElement(mar);
	       Select marital = new Select(driver.findElement(mar));
	       marital.selectByVisibleText("Single");
		}	

	    @DataProvider(name = "pancard")
	    public Object[][] pan_card() {
	        return new Object[][] {
	            { "EVDPB3193P" },
	        };
	    }
	    
	    @Test(priority = 28, dependsOnMethods = "Marital", dataProvider = "pancard")
		public void pan (String pn) throws InterruptedException

		{
	    	By pan = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/fieldset[1]/form[1]/div[7]/div[1]/label[1]/input[1]");   
	    	util.waitForElement(pan);
	    	driver.findElement(pan).clear();
	    	Thread.sleep(200);
			driver.findElement((pan)).sendKeys(pn);
			
		}
	    
	    @DataProvider(name = "email_reset")
	    public Object[][] email_set() {
	        return new Object[][] {
	            { "swapqa@simsontech.onmicrosoft.com" },
	        };
	    }
	    
	    @Test(priority = 29, dependsOnMethods = "pan", dataProvider = "email_reset")
		public void email_reset (String ema) throws InterruptedException

		{
	    	By email = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/fieldset[1]/form[1]/div[5]/div[1]/label[1]/input[1]");   
	    	util.waitForElement(email);
	    	driver.findElement(email).clear();
	    	Thread.sleep(200);
			driver.findElement((email)).sendKeys(ema);
			
		}

	    @Test(priority = 30, dependsOnMethods = "email_reset")
		public void insureddetails_btn ()

		{
	    	By insured_btn = By.xpath("//button[@ng-click=\"BasicDetailsFormSubmit('ContactDetails');\"]");
	    	util.waitForElement(insured_btn);
			driver.findElement((insured_btn)).click();
			
		}
	    
	    @DataProvider(name = "pincode")
	    public Object[][] pincode() {
	        return new Object[][] {
	            { "400001" },
	        };
	    }
	    
	    @Test(priority = 31, dependsOnMethods = "insureddetails_btn", dataProvider = "pincode")
	    public void address (String pc) throws InterruptedException

	    {
	            By pincode = By.xpath("//div[@class='form-group']//input[@id='Rc_Pin']");
	            Thread.sleep(2000);
	            util.waitForElement(pincode);
	            driver.findElement(pincode).clear();
	    		driver.findElement(pincode).sendKeys(pc);
	    		List<WebElement> make_list = driver.findElements(By.xpath("//ul[@id='ui-id-1']//li"));
	    		System.out.println("Make list size is "+make_list.size());
	    		
	    		for (int i=0; i<make_list.size();i++)
	    		{
	    			if(make_list.get(i).getText().equals(pc))
	    			{
	    				make_list.get(i).click();
	    				break;
	    			}
	    		
	    		}
	    }

	    @Test(priority = 32, dependsOnMethods = "address")
	    public void current_address ()

	    {
	    	By currentaddress = By.cssSelector("label[for='materialContactFormCopy7'] span");
	    	util.waitForElement(currentaddress);
	    	driver.findElement(currentaddress).click();
	    	
	    }

	    
	    @Test(priority = 33, dependsOnMethods = "current_address")
	    public void address_btn ()

	    {
	    	By address_btn = By.xpath("//button[@ng-click=\"ContactFormDetailsSubmit('NomineeDetails');\"]");
	    	util.waitForElement(address_btn);
	    	driver.findElement(address_btn).click();	
	    }
	    
	    
	    @DataProvider(name = "nominee")
	    public Object[][] nomine() {
	        return new Object[][] {
	            { "Test abc" },
	        };
	    }
	    
	    @Test(priority = 34, dependsOnMethods = "address_btn", dataProvider = "nominee")
	    public void nomin_name (String nme) throws InterruptedException

	    {
	    	By nominee_name = By.xpath("//input[@id='Nominee_FName']");
	    	util.waitForElement(nominee_name);
	    	util.sendKeysToElement(nominee_name, nme);
	    	Thread.sleep(4000);
	    	
	    }

	    @Test(priority = 35, dependsOnMethods = "nomin_name")
	    public void nominee_marital () throws InterruptedException 

	    {
	    	By nominee_marital = By.xpath("//select[@id='Nominee_Relationship']");
	    	Thread.sleep(2000);
	    	util.waitForElement(nominee_marital);
	         
	         Select rltn = new Select(driver.findElement(nominee_marital));
	         rltn.selectByVisibleText("Sibling");
	    }	

	    @Test(priority = 36, dependsOnMethods = "nominee_marital")
	    public void nominee_dob()

	    {
	    	
	        By nmne_dob = By.xpath("//input[@id='Nominee_Age']");
	        util.waitForElement(nmne_dob);
	    	driver.findElement(nmne_dob).click();
	    	
	    	By nmne_year = By.xpath("//select[@aria-label='Select year']");
	    	Select year = new Select(driver.findElement((nmne_year)));   
	        year.selectByVisibleText("1995");  
	        
	        By nomne_mnth = By.xpath("//select[@aria-label='Select month']");
	        Select month = new Select(driver.findElement((nomne_mnth)));   
	        month.selectByVisibleText("Sep"); 
	        
	        By nmne_date = By.xpath("//a[normalize-space()='14']");
	        driver.findElement(nmne_date).click();
	    }

	    @Test(priority = 37, dependsOnMethods = "nominee_dob")
	    public void nominee_btn()

	    {
	    	By nomne_btn = By.xpath("//button[@ng-click=\"NomineeFormDetailsSubmit('VehicleDetails');\"]");
	    	util.waitForElement(nomne_btn);
	        driver.findElement(nomne_btn).click();
	    
	    }
		

		 @DataProvider(name = "engine")
		    public Object[][] engine_num() {
		        return new Object[][] {
		            { "ABCDEF12345678" },
		        };
		    }
		
		 @Test(priority = 38, dependsOnMethods = "nominee_btn", dataProvider = "engine")
		public void engine(String en)
		
		{
			By engine_no = By.xpath("//input[@id='Engine_No']");
			util.waitForElement(engine_no);
			driver.findElement(engine_no).sendKeys(en);
		}
		 
		 @DataProvider(name = "chasis")
		    public Object[][] chasis_num() {
		        return new Object[][] {
		            { "GHIJKL90123456" },
		        };
		    }
		
		 @Test(priority = 39, dependsOnMethods = "engine", dataProvider = "engine")
		public void chasis(String ch)
		
		{
			By chasis_no = By.xpath("//input[@id='Chassis_No']");
			util.waitForElement(chasis_no);
			driver.findElement(chasis_no).sendKeys(ch);
		}
		

		 @Test(priority = 40, dependsOnMethods = "chasis")
		public void loan()
		
		{
			By loan = By.xpath("//label[@for='materialContactFormCopy5']//span");
			util.waitForElement(loan);
			driver.findElement(loan).click();
		}
		
		 
		 @Test(priority = 41, dependsOnMethods = "loan")
		public void vehicle_button()
		
		{
			js.executeScript("window.scrollBy(0, 200);");
			By vhcle_btn = By.xpath("//button[@ng-click=\"VehicleDetailsFormSubmit('PrePolicyDetails');\"]");		
			util.waitForElement(vhcle_btn);
			driver.findElement(vhcle_btn).click();
		}

		
		 
		@Test(priority = 42 , dependsOnMethods = "vehicle_button")
		public void previous_policy()
		
		{
			By policy_no = By.xpath("//input[@id='PrePolicy_Number']");
			util.waitForElement(policy_no);
			driver.findElement(policy_no).click();
			driver.findElement(policy_no).sendKeys("P6567567487854f");
			
//			By calenend_date = By.xpath("//input[@id='PrePolicy_Enddate']");
		    By policy_type = By.xpath("//select[@id='PrePolicy_Type']");
		    Select policy_drpdwn = new Select(driver.findElement(policy_type));
		    policy_drpdwn.selectByVisibleText("Comprehensive(OD + TP)");
		    
		    
		    By prepol_btn = By.xpath("//button[@ng-click=\"PrePolicyDetailsFormSubmit('ConfirmDetails');\"]");
		    util.waitForElement(prepol_btn);
		    driver.findElement(prepol_btn).click();
		    
		} 
		 

			@Test(priority = 43, dependsOnMethods = "previous_policy")
			public void terms()
			
			{
				By T_C = By.xpath("//label[normalize-space()='Terms & Conditions']");
				util.waitForElement(T_C);
				driver.findElement(T_C).click();
			}
			
			@Test(priority = 44, dependsOnMethods = "terms")
			public void existing_cust()
			
			{
				By existing_cust = By.xpath("//label[contains(@for,'materialContactFormCopy11')]");
				util.waitForElement(existing_cust);
				driver.findElement(existing_cust).click();
			}
			
			@Test(priority = 45, dependsOnMethods = "existing_cust")
			public void Make_payment()
			
			{
				By Mke_paymnt = By.xpath("//button[@id='btnMakePayment']");
				util.waitForElement(Mke_paymnt);
				driver.findElement(Mke_paymnt).click();
			}
			
			@Test(priority = 46, dependsOnMethods = "Make_payment")
			public void Make_payment_rel()
			
			{
				By rel_payment = By.xpath("//input[@id='btnCCavenue']");
				util.waitForElement(rel_payment);
				driver.findElement(rel_payment).click();
			}
	     
			@Test(priority = 47, dependsOnMethods = "Make_payment_rel")
			public void net_banking()
			{
				By cc_avenue = By.xpath("/html[1]/body[1]/form[1]/div[1]/div[4]/div[2]/div[3]/div[3]/div[1]/ul[1]/li[3]/div[1]/span[2]");
				util.waitForElement(cc_avenue);
				driver.findElement(cc_avenue).click();

			}
		

	        
		@Test(priority = 48, dependsOnMethods = "net_banking")
	    public void select_bank()
	    {

	    	By bnk = By.xpath("//select[@id='netBankingBank']");   
	    	Select avenue_bank = new Select(driver.findElement((bnk)));
	    	avenue_bank.selectByVisibleText("Avenues Test for New TC");  
	    }
	    

		@Test(priority = 49, dependsOnMethods = "select_bank")    //submit net banking
		public void Rel_payment()
		
		{
		     	
			     js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollBy(0, 200);");
		        By mke_pmnt = By.xpath("/html[1]/body[1]/form[1]/div[1]/div[4]/div[2]/div[3]/div[3]/div[1]/div[1]/div[3]/div[1]/div[5]/span[1]/a[1]");
		                                
		        util.waitForElement(mke_pmnt);

		        if(driver.findElement(mke_pmnt).isDisplayed() && driver.findElement(mke_pmnt).isEnabled())
		        {
		        	 driver.findElement(mke_pmnt).click();
		        }
		        else
		        {
		        	System.out.println("Element is not interactable");
		        }
		}
	    
		
		@Test(priority = 50, dependsOnMethods = "Rel_payment")  //success response
		public void success_payment()
		
		{
			By reponse = By.xpath("//button[@id='btn']");
			util.waitForElement(reponse);
			driver.findElement(reponse).click();
		}
	    
		
		@Test(priority = 51, dependsOnMethods = "success_payment")      //policy number
		public void get_policyno ()
		
		{
			By policy_no = By.xpath("//div[@class='text-center']//h3[1]");
			util.waitForElement(policy_no);
			System.out.println("Policy no. is for the 4th testcase of the SAOD car: "+ driver.findElement(policy_no).getText());
		} 

	}

