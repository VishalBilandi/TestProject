//1.Organisation
//2.With NCB
//3.With Add ons
//4.With finance


package Comprehensive;
		import java.util.List;
		import org.openqa.selenium.By;
		import org.openqa.selenium.JavascriptExecutor;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.interactions.Actions;
		import org.openqa.selenium.support.ui.Select;
		import org.testng.annotations.BeforeClass;
		import org.testng.annotations.DataProvider;
		import org.testng.annotations.Test;

		import utils.Launch_browser01;
		import utils.WebDriverUtils;

		public class TC_09 extends Launch_browser01
		{
		    private WebDriverUtils util;
		    JavascriptExecutor js;
		    Actions action;

		    @BeforeClass
		    public void setup() {
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
		    
		    
		    @Test(priority = 3, dependsOnMethods = "comprehensive")
		    public void exisitngTP_popup()
		    
		    {
//		    	By tp_popup = By.xpath("//div[@class='checkbox tremcondition']//label[@id='trnCndtion']");
//		    	util.waitForElement(tp_popup);
//		    	driver.findElement(tp_popup).click();
//		    	
//		       	By popup_okbtn = By.xpath("//button[@ng-disabled='standlonform.confirm.$error.required']");
//		    	util.waitForElement(popup_okbtn);
//		    	driver.findElement(popup_okbtn).click();

		    }
		    
		    
		    
		    @DataProvider(name = "rtoData")
		    public Object[][] rtoData() {
		        return new Object[][] {
		            { "MH01" },
		  
		        };
		    }
		    
		    @Test(priority = 4, dependsOnMethods = "exisitngTP_popup", dataProvider = "rtoData")
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
		    	expiry_year.selectByVisibleText("2021");
		    	
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
		    public void Pre_Expirydate()
		    {
		    	By expiry_calender = By.xpath("//input[@id='policyExdate']");
		    	util.waitForElement(expiry_calender);
		    	driver.findElement(expiry_calender).click();
		    	
		    	By year = By.xpath("//select[@aria-label='Select year']");
		    	Select expiry_year = new Select(driver.findElement(year));
		    	expiry_year.selectByVisibleText("2024");
		    	
		       	By month = By.xpath("//select[@aria-label='Select month']");
		    	Select expiry_month = new Select(driver.findElement(month));
		    	expiry_month.selectByVisibleText("Oct");
		    	
		     	By date = By.xpath("//a[normalize-space()='31']");
		        driver.findElement(date).click();
		    	 	
		    }
		    
		    
		    @Test(priority = 13, dependsOnMethods = "Pre_Expirydate")
		    public void owner_type () 
		    {
		    	
		    	
		        By Org = By.xpath("//div[@ng-class=\"{'boxmainhighlight' : Re_Item.VehicleOwnedBy == 'O'}\"]");
		        
		        util.waitForElement(Org);
		        driver.findElement(Org).click();
		        
		        By lakhs15 = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/section[1]/div[5]/div[1]/div[1]/div[2]/button[1]");
		        util.waitForElement(lakhs15);
		        driver.findElement(lakhs15).click();

		    }
		    
		   
		    @Test(priority = 14, dependsOnMethods = "owner_type")
		    public void ownership_no ()
		    {
		        By owner = By.xpath("//div[@ng-class=\"{'boxmainhighlight' : Re_Item.OwnershipChange == 'No'}\"]");
		        util.waitForElement(owner);
		        driver.findElement(owner).click();
		    }
		    
		    @Test(priority = 15, dependsOnMethods = "ownership_no")
		    public void claim_No ()
		    {
		        By claim = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/section[1]/div[2]/div[1]/form[1]/div[2]/div[1]/div[1]/div[13]/div[1]/div[2]");
		        util.waitForElement(claim);
		        driver.findElement(claim).click();
		        
		    }
		    
		    
		    @Test(priority = 16, dependsOnMethods = "claim_No")
		    public void ncb_drpdwn ()
		    {
		    	
		        By ncb = By.xpath("//select[@id='NCBper']");
		        util.waitForElement(ncb);
		        
		        Select ncb_percent = new Select(driver.findElement(ncb));
		        ncb_percent.selectByVisibleText("20%");
		    }
		    
		    
		  @DataProvider(name = "fullname")
		  public Object[][] fullname() {
		      return new Object[][] {
		          { "Vishal Bilandi" },
		      };
		  }

		  @Test(priority = 17, dependsOnMethods = "ncb_drpdwn", dataProvider = "fullname")
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
		    public void addons_drpdown ()
		    {
//		      By open_drpdwn = By.id("addons_drpdown");
		    	By open_drpdwn = By.xpath("//li[@id='AddOnsdropdown__filter']");
		        util.waitForElement(open_drpdwn);
		        driver.findElement(open_drpdwn).click();
		    }
		    
		    @Test(priority = 22, dependsOnMethods = "addons_drpdown")
		    public void addons_select () 
		    {
		        By zerodep = By.xpath("//label[normalize-space()='Zero Depreciation']");
		        util.waitForElement(zerodep);
		        driver.findElement(zerodep).click();
		    }
		    
		    @Test(priority = 23, dependsOnMethods = "addons_select")
		    public void claimfor_zerodep () 
		    {
		    	
		      By number_claims = (By.xpath("//select[@id='NoofClaimCovered']"));
		      util.waitForElement(number_claims);
		      Select number = new Select(driver.findElement((number_claims)));
		      number.selectByVisibleText("One");
		    }

		    
		    @Test(priority = 24, dependsOnMethods = "claimfor_zerodep")
		    public void zrodep_btn () 
		    {
		        By popup_btn = By.id("RsaPlanCrmCheck");
		        util.waitForElement(popup_btn);
		        driver.findElement(popup_btn).click();

		    }
		  
		    
		    @Test(priority = 25, dependsOnMethods = "zrodep_btn")
		    public void all_addons() throws InterruptedException {
		        // Define all the locators
		        By[] locators = {
		            By.xpath("//label[normalize-space()='Roadside Assistance']"),
		            By.xpath("//label[normalize-space()='Return to Invoice']"),
		            By.xpath("//label[normalize-space()='NCB Protector']"),
		            By.xpath("//label[normalize-space()='Engine Protector']"),
		            By.xpath("//label[normalize-space()='Consumable']"),
		            By.xpath("//label[normalize-space()='Key Replacement']"),
		            By.xpath("//label[normalize-space()='Tyre cover']"),
		            By.xpath("//label[normalize-space()='Motor Protection']"),
		            By.xpath("//label[normalize-space()='Loss of use']"),
		            By.xpath("//label[normalize-space()='Loss of personal belongings']")
		        };

		        // Initialize JavaScript Executor
		        js = (JavascriptExecutor) driver;

		        // Click on check boxes
		        for (By locator : locators) {
//		            WebElement element = driver.findElement(locator);
		            if (driver.findElement(locator).isDisplayed()) 
		            {
		                js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
		                util.waitForElement(locator);
		                driver.findElement(locator).click();
		            }
		        }

		       Thread.sleep(1000);
		        By loss = By.id("lossOfBaggageValue");
		        Select lossPreson = new Select(driver.findElement(loss));
		        lossPreson.selectByVisibleText("15000");

		        // Click on the "update_results" button
		        By update = By.xpath("//div[@id='AddOnsid']//button[@id='uppadateAddon']");
		        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(update));
		        util.waitForElement(update);
		        driver.findElement(update).click();
		    }
		    		    
		    
		    @Test(priority = 26, dependsOnMethods = "all_addons")
		    public void Accessrios_cover() throws InterruptedException
		    {
		    	Thread.sleep(1000);
		    By accessories = By.id("Accessoriesdropdown__filter");
//		    if (driver.findElement(accessories).isDisplayed()) {
		        js.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(accessories));
//		    }
//		    else {
//		        System.out.println("Element is not visible.");
//		    }
		    driver.findElement(accessories).click();
		    }
		    
		       
		    @DataProvider(name = "electric")
		    public Object[][] electric() {
		        return new Object[][] {
		            { "5000" },
		        };
		    }
		    @Test(priority = 27, dependsOnMethods = "Accessrios_cover", dataProvider= "electric")
		    public void elec_acc(String ea) throws InterruptedException
		    {
		    	Thread.sleep(1000);
		    	By electric = By.xpath("//input[@id='accers']");
		    	util.waitForElement(electric);
		    	driver.findElement(electric).isDisplayed();
		    	driver.findElement(electric).sendKeys(ea);
		    	
		    	
		    }
		    
		    @DataProvider(name = "non_electric")
		    public Object[][] non_electric() {
		        return new Object[][] {
		            { "10000" },
		        };
		    }
		    
		    @Test(priority = 28, dependsOnMethods = "elec_acc", dataProvider= "non_electric")
		    public void non_elec(String nea) throws InterruptedException
		    {
		    	
		    	Thread.sleep(1000);
		    	 By non_electric = By.xpath("//input[@id='nonaccers']");
		    	 util.waitForElement(non_electric);
		    	 driver.findElement(non_electric).sendKeys(nea);
		    	 By update = By.xpath("//button[@id='AcceUpdateResult']");
		    	 driver.findElement(update).click();
		    	
		    }
		    
		    
		    @Test(priority = 29, dependsOnMethods = "non_elec")
		    public void pa_cover() throws InterruptedException
		    
		    {
		    	Thread.sleep(1000);
		    	By PA_cover = By.xpath("//li[@id='PersonalAccidentdropdown__filter']");
		    	
		    	util.waitForElement(PA_cover);
		    	driver.findElement(PA_cover).isDisplayed();
		    	driver.findElement(PA_cover).click();
		    	
		    	By paid_driver = By.xpath("//label[normalize-space()='Cover for Paid Driver?']");
		    	util.waitForElement(paid_driver);
		    	driver.findElement(paid_driver).click();
		    	
		    	By CO_pass = By.xpath("//label[normalize-space()='Personal Accident cover for co-passenger?']");
		    	util.waitForElement(CO_pass);
		    	driver.findElement(CO_pass).click();
		    	
		    	By copass_drpdown = By.xpath("//select[@id='SumInsuredCoP']");
		    	Select drpdwn = new Select(driver.findElement(copass_drpdown));
		    	drpdwn.selectByVisibleText("200000");
		    	
		    	By btn_update = By.xpath("//div[@id='PersonalAccidentid']//button[@id='uppadateAddon']");
		    	util.waitForElement(btn_update);
		    	driver.findElement(btn_update).click();

		    	
		    }
		    
		    
		    @Test(priority = 30, dependsOnMethods = "pa_cover")
		    public void discounts () throws InterruptedException
		    
		    {
		    	Thread.sleep(1000);
		    	By disc = By.id("Discountsdropdown__filter");
		    	util.waitForElement(disc);
		    	driver.findElement(disc).click();
		    	
		    	By vol_deduc = By.xpath("//label[normalize-space()='Voluntary Deductible']");
		    	util.waitForElement(vol_deduc);
		    	driver.findElement(vol_deduc).click();
		    	
		    	By ARAI_theft = By.xpath("//label[normalize-space()='ARAI anti theft device installed?']");
		    	util.waitForElement(ARAI_theft);
		    	driver.findElement(ARAI_theft).click();
		    	
		    	By updt_btn = By.xpath("//button[@id='DiscountsSmallClick']");
		    	util.waitForElement(updt_btn);
		    	driver.findElement(updt_btn).click();
		 
		    }
		    
		    @DataProvider(name = "cng_value")
		    public Object[][] cng_value() {
		        return new Object[][] {
		            { "20000" },
		        };
		    }
		    
		    @Test(priority = 31, dependsOnMethods = "discounts", dataProvider = "cng_value")
		    public void cng (String cng) throws InterruptedException
		    
		    {
		    	Thread.sleep(1000);
		    	By cng_drpdown = By.xpath("//li[@id='CNGPremiumdropdown__filter']");
		    	util.waitForElement(cng_drpdown);
		    	driver.findElement(cng_drpdown).click();
		    	
		    	By cng_checkbox = By.xpath("//label[normalize-space()='Externally fitted CNG kit?']");
		    	util.waitForElement(cng_checkbox);
		    	driver.findElement(cng_checkbox).click();
		    	
		    	By cng_value = By.xpath("//input[@id='CNGValue']");
		    	driver.findElement(cng_value).sendKeys(cng); 
		    	
		    	By updte = By.xpath("//button[@ng-disabled='DisableCNGForHDFCRenewal']");
		    	util.waitForElement(updte);
		    	driver.findElement(updte).click();
		    }

		  
		  
		  @Test(priority = 32, dependsOnMethods = "get_quote")
		  public void premium()
		  {
		      By pre_btn = By.xpath("//button[@id='buy_Reliance']");
		      util.waitForElement(pre_btn);
		      js.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(pre_btn));
		     
		      driver.findElement(pre_btn).click();
		      
		   
		      By confirm_btn = By.xpath(" //button[@id='zeroDeptCrmCheck']");
		      util.waitForElement(confirm_btn);
		      driver.findElement(confirm_btn).click();
		  }
		  
		  
		    
		  @Test(priority = 33, dependsOnMethods = "premium")
		    public void ckyc_yes() {
		    	
		    	By kyc_btn = By.xpath("//label[@for='KYCYes']//span");
		    	util.waitForElement(kyc_btn);
		    	
		    	util.clickElement(kyc_btn);
		    	
		    }
		    
		    @DataProvider(name = "ckyc_number")
		    public Object[][] ckyc() {
		        return new Object[][] {
		            { "90077178553736" },
		        };
		    }
		    
		    @Test(priority = 34, dependsOnMethods = "ckyc_yes", dataProvider = "ckyc_number")
		    public void ckyc_number(String num) {
		    	
		    	By ckyc_no = By.xpath("//input[@id='dckycNumber']");
		    	util.waitForElement(ckyc_no);
		    	{
		    	util.sendKeysToElement(ckyc_no, num);
		    	}
		    }
		    
		    @Test(priority = 35, dependsOnMethods = "ckyc_number")
		    public void dob_calender()

		    {
		    	
		    	By calender = By.xpath("//input[@id='kycDob']");
		    	util.waitForElement(calender);
		    	driver.findElement(calender).click();  //clicked
		    	
		    	Select year = new Select(driver.findElement((By.xpath("//select[@aria-label='Select year']"))));   //select year
		        year.selectByVisibleText("1996");
		        
		        Select month = new Select(driver.findElement((By.xpath("//select[@aria-label='Select month']"))));   //select year
		        month.selectByVisibleText("Nov");
		        
		        driver.findElement(By.xpath("//a[normalize-space()='22']")).click();
		    }
		    
		    @DataProvider(name = "enter_email")
		    public Object[][] Verifykyc() {
		        return new Object[][] {
		            { "swapqa@simsontech.onmicrosoft.com" },
		        };
		    }
		    
		    @Test(priority = 36, dependsOnMethods = "dob_calender", dataProvider = "enter_email" )
		    public void email(String em)

		    {
		    	By email = By.xpath("//input[@id='Email']");
		    	util.waitForElement(email);
		    	driver.findElement(email).sendKeys(em);
		    }

		 
		    
		    @Test(priority = 37, dependsOnMethods = "email")
		    public void Verify_kyc()

		    {
		    	By verifykyc = By.xpath("//button[@id='KYCSubmit']");
		    	util.waitForElement(verifykyc);
		    	driver.findElement(verifykyc).click();
		    }



		    @DataProvider(name = "name_value")
		    public Object [][] name() {
		    	return new Object[][] {
		    			{"Abc person"},
		    	
		    };
		    
		}

		    @Test(priority = 38, dependsOnMethods = "Verify_kyc", dataProvider = "name_value")
		    public void insured_details (String cp) throws InterruptedException

			{
				 By salutation = By.xpath("//select[@id='salutation']"); 
				 util.waitForElement(salutation);
		         Select sal = new Select(driver.findElement(salutation));
		         sal.selectByVisibleText("M/S");
		         
		         By cperson = By.xpath("//input[@id='CPerson']");
		         util.waitForElement(cperson);
		         util.sendKeysToElement(cperson, cp);
		         
		         By mnumber = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/fieldset[1]/form[2]/div[3]/div[1]/label[1]/input[1]");
		         util.waitForElement(mnumber);
		         driver.findElement(mnumber).sendKeys("9876543217");

			}	

		    
		    @Test(priority = 39, dependsOnMethods = "insured_details")
			public void pan () throws InterruptedException

			{
				
				By gst = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/fieldset[1]/form[2]/div[6]/div[1]/label[1]/input[1]");
				util.waitForElement(gst);
				driver.findElement(gst).sendKeys("03AAACH2645B1ZP");
			}

		    @Test(priority = 40, dependsOnMethods = "pan")
			public void insureddetails_btn ()

			{
		    	By insured_btn = By.xpath("//button[@ng-click=\"OrgBasicDetailsFormSubmit('ContactDetails');\"]");
		                                	
		    	util.waitForElement(insured_btn);
				driver.findElement((insured_btn)).click();
				
			}
		    
		    
		   
		    @DataProvider(name = "pincode")
		    public Object[][] pincode() {
		        return new Object[][] {
		            { "400001" },
		        };
		    }
		    
		    
		    @Test(priority = 41, dependsOnMethods = "insureddetails_btn", dataProvider = "pincode")
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

		    @Test(priority = 43, dependsOnMethods = "address")
		    public void current_address ()

		    {
		    	By currentaddress = By.cssSelector("label[for='materialContactFormCopy7'] span");
		    	util.waitForElement(currentaddress);
		    	driver.findElement(currentaddress).click();
		    	
		    }

		    
		    @Test(priority = 44, dependsOnMethods = "current_address")
		    public void address_btn ()

		    {
		    	By address_btn = By.xpath("//button[@ng-click=\"ContactFormDetailsSubmit('VehicleDetails');\"]");
		    	util.waitForElement(address_btn);
		    	driver.findElement(address_btn).click();	
		    }
		    
//		    
//		    @DataProvider(name = "nominee")
//		    public Object[][] nomine() {
//		        return new Object[][] {
//		            { "Test abc" },
//		        };
//		    }
		    
//		    @Test(priority = 45, dependsOnMethods = "address_btn", dataProvider = "nominee")
//		    public void nomin_name (String nme) throws InterruptedException
//
//		    {
//		    	By nominee_name = By.xpath("//input[@id='Nominee_FName']");
//		    	util.waitForElement(nominee_name);
//		    	util.sendKeysToElement(nominee_name, nme);
//		    	Thread.sleep(4000);
//		    	
//		    }

//		    @Test(priority = 46, dependsOnMethods = "nomin_name")
//		    public void nominee_marital () throws InterruptedException 
//
//		    {
//		    	By nominee_marital = By.xpath("//select[@id='Nominee_Relationship']");
//		    	Thread.sleep(2000);
//		    	util.waitForElement(nominee_marital);
//		         
//		         Select rltn = new Select(driver.findElement(nominee_marital));
//		         rltn.selectByVisibleText("Sibling");
//		    }	
////
//		    @Test(priority = 47, dependsOnMethods = "nominee_marital")
//		    public void nominee_dob()
//
//		    {
//		    	
//		        By nmne_dob = By.xpath("//input[@id='Nominee_Age']");
//		        util.waitForElement(nmne_dob);
//		    	driver.findElement(nmne_dob).click();
//		    	
//		    	By nmne_year = By.xpath("//select[@aria-label='Select year']");
//		    	Select year = new Select(driver.findElement((nmne_year)));   
//		        year.selectByVisibleText("1995");  
//		        
//		        By nomne_mnth = By.xpath("//select[@aria-label='Select month']");
//		        Select month = new Select(driver.findElement((nomne_mnth)));   
//		        month.selectByVisibleText("Sep"); 
//		        
//		        By nmne_date = By.xpath("//a[normalize-space()='14']");
//		        driver.findElement(nmne_date).click();
//		    }
//
//		    @Test(priority = 48, dependsOnMethods = "nominee_dob")
//		    public void nominee_btn()
//
//		    {
//		    	By nomne_btn = By.xpath("//button[@ng-click=\"NomineeFormDetailsSubmit('VehicleDetails');\"]");
//		    	util.waitForElement(nomne_btn);
//		        driver.findElement(nomne_btn).click();
//		    
//		    }
//			

			 @DataProvider(name = "engine")
			    public Object[][] engine_num() {
			        return new Object[][] {
			            { "ABCDEF12345678" },
			        };
			    }
			
			 @Test(priority = 45, dependsOnMethods = "address_btn", dataProvider = "engine")
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
			
			 @Test(priority = 46, dependsOnMethods = "engine", dataProvider = "engine")
			public void chasis(String ch)
			
			{
				By chasis_no = By.xpath("//input[@id='Chassis_No']");
				util.waitForElement(chasis_no);
				driver.findElement(chasis_no).sendKeys(ch);
			}
			

			 @Test(priority = 47, dependsOnMethods = "chasis")
				public void loan_yes()
				
				{
					By loan = By.xpath("//label[@for='materialContactFormCopy4']//span");
					util.waitForElement(loan);
					driver.findElement(loan).click();
				}
				
				 @Test(priority = 48, dependsOnMethods = "loan_yes")
				public void bnk_name()
				
				{
					By bank = By.xpath("//input[@id='finalIns']");
					util.waitForElement(bank);
					driver.findElement(bank).sendKeys("HDFC Bank");
				}
				 
				
				 @Test(priority = 49, dependsOnMethods = "bnk_name")
				public void bnk_city()
				
				{
					By bank = By.xpath("//input[@id='FinCity']");
					util.waitForElement(bank);
					driver.findElement(bank).sendKeys("Mumbai");
				}
			
			 
			 @Test(priority = 50, dependsOnMethods = "bnk_city")
			public void vehicle_button()
			
			{
				js.executeScript("window.scrollBy(0, 200);");
				By vhcle_btn = By.xpath("//button[@ng-click=\"VehicleDetailsFormSubmit('PrePolicyDetails');\"]");		
				util.waitForElement(vhcle_btn);
				driver.findElement(vhcle_btn).click();
			}

			
			 
			@Test(priority = 51 , dependsOnMethods = "vehicle_button")
			public void previous_policy()
			
			{
				By policy_no = By.xpath("//input[@id='PrePolicy_Number']");
				util.waitForElement(policy_no);
				driver.findElement(policy_no).click();
				driver.findElement(policy_no).sendKeys("P6567567487854f");
				

			    By policy_type = By.xpath("//select[@id='PrePolicy_Type']");
			    Select policy_drpdwn = new Select(driver.findElement(policy_type));
			    policy_drpdwn.selectByVisibleText("Bundled (1 yr OD + 3 yr TP)");
			    
			    
			    By prepol_btn = By.xpath("//button[@ng-click=\"PrePolicyDetailsFormSubmit('ConfirmDetails');\"]");
			    util.waitForElement(prepol_btn);
			    driver.findElement(prepol_btn).click();
				
			} 
			 

				@Test(priority = 52, dependsOnMethods = "previous_policy")
				public void terms()
				
				{
					By T_C = By.xpath("//label[normalize-space()='Terms & Conditions']");
					util.waitForElement(T_C);
					driver.findElement(T_C).click();
				}
				
				@Test(priority = 53, dependsOnMethods = "terms")
				public void existing_cust()
				
				{
					By existing_cust = By.xpath("//label[contains(@for,'materialContactFormCopy11')]");
					util.waitForElement(existing_cust);
					driver.findElement(existing_cust).click();
				}
				
				@Test(priority = 54, dependsOnMethods = "existing_cust")
				public void Make_payment()
				
				{
					By Mke_paymnt = By.xpath("//button[@id='btnMakePayment']");
					util.waitForElement(Mke_paymnt);
					driver.findElement(Mke_paymnt).click();
				}
				
				@Test(priority = 55, dependsOnMethods = "Make_payment")
				public void Make_payment_rel()
				
				{
					By rel_payment = By.xpath("//input[@id='btnCCavenue']");
					util.waitForElement(rel_payment);
					driver.findElement(rel_payment).click();
				}
		     
				@Test(priority = 56, dependsOnMethods = "Make_payment_rel")
				public void net_banking()
				{
					By cc_avenue = By.xpath("/html[1]/body[1]/form[1]/div[1]/div[4]/div[2]/div[3]/div[3]/div[1]/ul[1]/li[3]/div[1]/span[2]");
					util.waitForElement(cc_avenue);
					driver.findElement(cc_avenue).click();

				}
			

		        
			@Test(priority = 57, dependsOnMethods = "net_banking")
		    public void select_bank()
		    {

		    	By bnk = By.xpath("//select[@id='netBankingBank']");   
		    	Select avenue_bank = new Select(driver.findElement((bnk)));
		    	avenue_bank.selectByVisibleText("Avenues Test for New TC");  
		    }
		    

			@Test(priority = 58, dependsOnMethods = "select_bank")    //submit net banking
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
		    
			
			@Test(priority = 59, dependsOnMethods = "Rel_payment")  //success response
			public void success_payment()
			
			{
				By reponse = By.xpath("//button[@id='btn']");
				util.waitForElement(reponse);
				driver.findElement(reponse).click();
			}
		    
			
			@Test(priority = 60, dependsOnMethods = "success_payment")      //policy number
			public void get_policyno ()
			
			{
				By policy_no = By.xpath("//div[@class='text-center']//h3[1]");
				util.waitForElement(policy_no);
				System.out.println("Policy no. is for the 4th testcase of the SAOD car: "+ driver.findElement(policy_no).getText());
			} 

		}

	


