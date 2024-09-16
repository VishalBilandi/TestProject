//1.Organisation
//2.With Add ons
//3.With finance


package New_car;

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

public class TC_03 extends Launch_browser01 {
    private WebDriverUtils util;
    JavascriptExecutor js;
    Actions action;

    @BeforeClass
    public void setup() {
        super.setup(); // Ensure superclass setup is also called
        util = new WebDriverUtils(driver); // Initialize WebDriverUtils
    }

    @Test(priority = 0)
    public void clickCarModule() {
        By carModule = By.xpath("//li[@ng-click=\"pageNavigation('car')\"]");
        util.clickElement(carModule);
    }

    @Test(priority = 1, dependsOnMethods = "clickCarModule")
    public void newCar() throws InterruptedException {
        By new_car = By.xpath("//a[@id='newCar']");
        util.clickElement(new_car);
    }

    @DataProvider(name = "rtoData")
    public Object[][] rtoData() {
        return new Object[][] {
            { "MH01" },
        };
    }
    
    
    @Test(priority = 2, dependsOnMethods = "newCar", dataProvider = "rtoData")
    public void rto_num(String rt) throws InterruptedException {
    	

    	       
     		 	By RTO=By.xpath("//input[@id='Regno']");
     		 	util.sendKeysToElement(RTO, rt);
   
     		 	Thread.sleep(300);
     	 	   List<WebElement> elements = driver.findElements((By.xpath("//ul[@id='ui-id-1']//li")));
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
     	        

    @DataProvider(name = "makeData")
    public Object[][] makeData() {
        return new Object[][] {
            { "Hond" },
        };
    }

    @Test(priority = 3, dependsOnMethods = "rto_num", dataProvider = "makeData")
    public void make(String mke) throws InterruptedException {
        By makeLocator = By.xpath("//input[@id='make']");
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
    

    @Test(priority = 4, dependsOnMethods = "make")
    public void model() throws InterruptedException 
    
    {
    	By mdl = By.xpath("//select[@id='newmodels']");
    	util.waitForElement(mdl);
    	driver.findElement(mdl).click();
    	driver.findElement(By.xpath("//option[@value='string:City (Petrol)']")).click();
    	
    }

    @Test(priority = 5, dependsOnMethods = "model")
    public void variant()
    {
    	By vari = By.xpath("//select[@id='newvariant']");
    	util.waitForElement(vari);
    	driver.findElement(vari).click();
    	driver.findElement(By.xpath("//option[@value='string:1.3 LXI (1343 CC)']")).click();
        
    }
    
    @Test(priority = 6, dependsOnMethods = "variant")
    public void organisation () {
        By org = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/section[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[5]/div[1]/div[2]");
        util.waitForElement(org);
        driver.findElement(org).click();
       
    }
    
    @Test(priority = 7, dependsOnMethods = "organisation")
    public void okk () {
   
        By ok = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[2]/section[1]/div[5]/div[1]/div[1]/div[2]/button[1]");
        util.waitForElement(ok);
        driver.findElement(ok).click();
    }

    
    
    @DataProvider(name = "fullname")
    public Object[][] fullname() {
        return new Object[][] {
            { "Vishal Bilandi" },
        };
    }

    @Test(priority = 8, dependsOnMethods = "okk", dataProvider = "fullname")
    public void fullname(String fn) {
        By fullNameLocator = By.xpath("//div[@class='col-lg-3']//input[@id='Name']");
        util.waitForElement(fullNameLocator);
        util.sendKeysToElement(fullNameLocator, fn);
        
    }
    
    
  @DataProvider(name = "Mobilenum")
  public Object[][] mobilenum() {
      return new Object[][] {
          { "9311506762" },
      };
  }
    
  @Test(priority = 9, dependsOnMethods = "fullname", dataProvider = "Mobilenum")
  public void mobnum(String mn) {
      By mobile_number = By.xpath("//div[@class='col-lg-3']//input[@placeholder='Mobile']");
      util.waitForElement(mobile_number);
      driver.findElement(mobile_number).sendKeys(mn);
  }
  

  @Test(priority = 10, dependsOnMethods = "mobnum")
  public void term_con() {
      By tc = By.xpath("//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12']//label[@id='trnCndtion']");
      util.waitForElement(tc);
      driver.findElement(tc).click();
  }
    
  
  @Test(priority = 11, dependsOnMethods = "term_con")
  public void quote_btn() {
      By get_quote = By.id("carfinalstep");
      util.waitForElement(get_quote);
      driver.findElement(get_quote).click();
  }
    

    @Test(priority = 12, dependsOnMethods = "quote_btn")
    public void addons_drpdown () {
    	By open_drpdwn = By.xpath("//li[@id='AddOnsdropdown__filter']");
        util.waitForElement(open_drpdwn);
        driver.findElement(open_drpdwn).click();
    }
    
    @Test(priority = 13, dependsOnMethods = "addons_drpdown")
    public void addons_select () {
        By zerodep = By.xpath("//label[normalize-space()='Zero Depreciation']");
        util.waitForElement(zerodep);
        driver.findElement(zerodep).click();
    }
    
    @Test(priority = 14, dependsOnMethods = "addons_select")
    public void claimfor_zerodep () {
    	
      By number_claims = (By.xpath("//select[@id='NoofClaimCovered']"));
      util.waitForElement(number_claims);
      Select number = new Select(driver.findElement((number_claims)));
      number.selectByVisibleText("One");
    }

    
    @Test(priority = 15, dependsOnMethods = "claimfor_zerodep")
    public void zrodep_btn () {
        By popup_btn = By.id("RsaPlanCrmCheck");
        util.waitForElement(popup_btn);
        driver.findElement(popup_btn).click();

    }
  
 
    
    @Test(priority = 16, dependsOnMethods = "zrodep_btn")
    public void all_addons() throws InterruptedException {
      
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
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) 
            {
                js.executeScript("arguments[0].scrollIntoView(true);", element);
                util.waitForElement(locator);
                element.click();
            }
        }

        Thread.sleep(1000);
        By loss = By.id("lossOfBaggageValue");
        Select lossPreson = new Select(driver.findElement(loss));
        lossPreson.selectByVisibleText("15000");

        Thread.sleep(1000);
        By update = By.xpath("//div[@id='AddOnsid']//button[@id='uppadateAddon']");
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(update));
        util.waitForElement(update);
        driver.findElement(update).click();
    }
    
   
    @Test(priority = 17, dependsOnMethods = "all_addons")
    public void Accessrios_cover() throws InterruptedException
    {
    	Thread.sleep(1000);
        By accessories = By.id("Accessoriesdropdown__filter");
        js.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(accessories));
        driver.findElement(accessories).click();
    }
    
    
    @DataProvider(name = "electric")
    public Object[][] electric() {
        return new Object[][] {
            { "5000" },
        };
    }
    @Test(priority = 18, dependsOnMethods = "Accessrios_cover", dataProvider= "electric")
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
    
    @Test(priority = 19, dependsOnMethods = "elec_acc", dataProvider= "non_electric")
    public void non_elec(String nea) throws InterruptedException
    {
    	Thread.sleep(1000);
    	 By non_electric = By.xpath("//input[@id='nonaccers']");
    	 util.waitForElement(non_electric);
    	 driver.findElement(non_electric).sendKeys(nea);
    	 By update = By.xpath("//button[@id='AcceUpdateResult']");
    	 driver.findElement(update).click();
    	
    }
    
    
    @Test(priority = 20, dependsOnMethods = "non_elec")
    public void pa_cover() throws InterruptedException
    
    {
    	Thread.sleep(1000);
    	By PA_cover = By.xpath("//label[@for='PersonalAccident']//ul[@class='form-control dropdown__filter']");
    	
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
    
    
    @Test(priority = 21, dependsOnMethods = "pa_cover")
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
    
    @Test(priority = 22, dependsOnMethods = "discounts", dataProvider = "cng_value")
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

    
  @Test(priority = 23, dependsOnMethods = "cng")
  public void getquote () {
      By qute_txt = By.xpath("//button[@id='buy_Reliance']");
      util.waitForElement(qute_txt);
      System.out.println("The value of the premium is: "+  driver.findElement(qute_txt).getText());
      js.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(qute_txt));
      driver.findElement(qute_txt).click();
  }
  
  
    @Test(priority = 24, dependsOnMethods = "getquote")
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
    
    @Test(priority = 25, dependsOnMethods = "ckyc_yes", dataProvider = "ckyc_number")
    public void ckyc_number(String num) {
    	
    	By ckyc_no = By.xpath("//input[@id='dckycNumber']");
    	util.waitForElement(ckyc_no);
    	{
    	util.sendKeysToElement(ckyc_no, num);
    	}
    }
    
    @Test(priority = 26, dependsOnMethods = "ckyc_number")
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
    
    @Test(priority = 27, dependsOnMethods = "dob_calender", dataProvider = "enter_email" )
    public void email(String em)

    {
    	By email = By.xpath("//input[@id='Email']");
    	util.waitForElement(email);
    	driver.findElement(email).sendKeys(em);
    }

 
    
    @Test(priority = 28, dependsOnMethods = "email")
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

    @Test(priority = 29, dependsOnMethods = "Verify_kyc", dataProvider = "name_value")
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

    
    @Test(priority = 30, dependsOnMethods = "insured_details")
	public void pan () throws InterruptedException

	{
		
		By gst = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/fieldset[1]/form[2]/div[6]/div[1]/label[1]/input[1]");
		util.waitForElement(gst);
		driver.findElement(gst).sendKeys("03AAACH2645B1ZP");
	}

    @Test(priority = 31, dependsOnMethods = "pan")
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
    
    @Test(priority = 32, dependsOnMethods = "insureddetails_btn", dataProvider = "pincode")
    public void address (String pc) throws InterruptedException

    {

    	
    	    Thread.sleep(5000);
            By pincode = By.xpath("//div[@class='form-group']//input[@id='Rc_Pin']");
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

    @Test(priority = 33, dependsOnMethods = "address")
    public void current_address ()

    {
    	By currentaddress = By.cssSelector("label[for='materialContactFormCopy7'] span"); // previous address same as current.
    	util.waitForElement(currentaddress);
    	driver.findElement(currentaddress).click();
    	
    }

    
    @Test(priority = 34, dependsOnMethods = "current_address")
    public void address_btn ()

    {
    	By address_btn = By.xpath("//button[@ng-click=\"ContactFormDetailsSubmit('VehicleDetails');\"]");             	
    	util.waitForElement(address_btn);
    	driver.findElement(address_btn).click();	
    }
    

	 @DataProvider(name = "engine")
	    public Object[][] engine_num() {
	        return new Object[][] {
	            { "ABCDEF12345678" },
	        };
	    }
	
	 @Test(priority = 35, dependsOnMethods = "address_btn", dataProvider = "engine")
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
	
	 @Test(priority = 36, dependsOnMethods = "engine", dataProvider = "chasis")
	public void chasis(String ch)
	
	{
		By chasis_no = By.xpath("//input[@id='Chassis_No']");
		util.waitForElement(chasis_no);
		driver.findElement(chasis_no).sendKeys(ch);
	}
	
	 @DataProvider(name = "reg_code")
	    public Object[][] reg_c() {
	        return new Object[][] {
	            { "TU" },
	        };
	    }
	 
	 @Test(priority = 37, dependsOnMethods = "chasis", dataProvider = "reg_code")
	 public void reg_code(String regc)
	 
	 {
		 By reg_c = By.xpath("//input[@id='RegC']");
		 util.waitForElement(reg_c);
		 driver.findElement(reg_c).click();
		 util.sendKeysToElement(reg_c, regc);
		 
	 }
	 
	 @DataProvider(name = "reg_num")
	    public Object[][] reg_n() {
	        return new Object[][] {
	            { "4587" },
	        };
	    }
	 
	 @Test(priority = 38, dependsOnMethods = "reg_code", dataProvider = "reg_num")
	 public void reg_num(String regn)
	 
	 {
		 By reg_c = By.xpath("//input[@id='RegD']");
		 util.waitForElement(reg_c);
		 driver.findElement(reg_c).click();
		 util.sendKeysToElement(reg_c, regn);
		 
	 }
	 
	 
	 @Test(priority = 39, dependsOnMethods = "reg_num")
	public void loan_yes()
	
	{
		By loan = By.xpath("//label[@for='materialContactFormCopy4']//span");
		util.waitForElement(loan);
		driver.findElement(loan).click();
	}
	
	 @Test(priority = 40, dependsOnMethods = "loan_yes")
	public void bnk_name()
	
	{
		By bank = By.xpath("//input[@id='finalIns']");
		util.waitForElement(bank);
		driver.findElement(bank).sendKeys("HDFC Bank");
	}
	 
	
	 @Test(priority = 41, dependsOnMethods = "bnk_name")
	public void bnk_city()
	
	{
		By bank = By.xpath("//input[@id='FinCity']");
		util.waitForElement(bank);
		driver.findElement(bank).sendKeys("Mumbai");
	}
	 

	 
	 @Test(priority = 42, dependsOnMethods = "bnk_city")
	public void vehicle_button()
	
	{
		By vhcle_btn = By.xpath("//button[@ng-hide=\"(perposalData.PolicyType | lowercase) != 'newcar'\"]"); 
		util.waitForElement(vhcle_btn);
		driver.findElement(vhcle_btn).click();
	}


		@Test(priority = 43, dependsOnMethods = "vehicle_button")
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
		System.out.println("Policy no. is for the first testcase of the new car: "+ driver.findElement(policy_no).getText());
	}
    
}
