package New_car;

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

public class TC_01 extends Launch_browser01 {
    private WebDriverUtils util;
    JavascriptExecutor js;

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
    

    @DataProvider(name = "fullname")
    public Object[][] fullname() {
        return new Object[][] {
            { "Vishal Bilandi" },
        };
    }

    @Test(priority = 6, dependsOnMethods = "variant", dataProvider = "fullname")
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

    @Test(priority = 7, dependsOnMethods = "fullname", dataProvider = "Mobilenum")
    public void mobnum(String mn) {
        By mobile_number = By.xpath("//div[@class='col-lg-3']//input[@placeholder='Mobile']");
        util.waitForElement(mobile_number);
        driver.findElement(mobile_number).sendKeys(mn);
    }

    @Test(priority = 8, dependsOnMethods = "mobnum")
    public void term_con() {
        By tc = By.xpath("//div[@class='col-xs-12 col-sm-12 col-md-12 col-lg-12']//label[@id='trnCndtion']");
        util.waitForElement(tc);
        driver.findElement(tc).click();
    }

    @Test(priority = 9, dependsOnMethods = "term_con")
    public void quote_btn() {
        By get_quote = By.id("carfinalstep");
        util.waitForElement(get_quote);
        driver.findElement(get_quote).click();
    }
    
    @Test(priority = 10, dependsOnMethods = "quote_btn")
    public void premium() {
    	
    	By prem_btn = By.xpath("//button[@id='buy_Reliance']");
    	util.waitForElement(prem_btn);
    	{
    	util.clickElement(prem_btn);
    	}
    }
    
    @Test(priority = 11, dependsOnMethods = "premium")
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
    
    @Test(priority = 12, dependsOnMethods = "kyc_button", dataProvider = "ckyc_number")
    public void ckyc_number(String num) {
    	
    	By ckyc_no = By.xpath("//input[@id='dckycNumber']");
    	util.waitForElement(ckyc_no);
    	{
    	util.sendKeysToElement(ckyc_no, num);
    	}
    }
    
    @Test(priority = 13, dependsOnMethods = "ckyc_number")
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
    
    @Test(priority = 14, dependsOnMethods = "dob_calender", dataProvider = "enter_email" )
    public void email(String em)

    {
    	By email = By.xpath("//input[@id='Email']");
    	util.waitForElement(email);
    	driver.findElement(email).sendKeys(em);
    }
    
    @Test(priority = 15, dependsOnMethods = "email")
    public void Verify_kyc()

    {
    	By verifykyc = By.xpath("//button[@id='KYCSubmit']");
    	util.waitForElement(verifykyc);
    	driver.findElement(verifykyc).click();
    }


    @Test(priority = 16, dependsOnMethods = "Verify_kyc")
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
    
    @Test(priority = 17, dependsOnMethods = "Marital", dataProvider = "pancard")
	public void pan (String pn) throws InterruptedException

	{
    	By pan = By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/fieldset[1]/form[1]/div[7]/div[1]/label[1]/input[1]");   
    	util.waitForElement(pan);
    	driver.findElement(pan).clear();
    	Thread.sleep(200);
		driver.findElement((pan)).sendKeys(pn);
		
	}

    @Test(priority = 18, dependsOnMethods = "pan")
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
    
    @Test(priority = 19, dependsOnMethods = "insureddetails_btn", dataProvider = "pincode")
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

    @Test(priority = 20, dependsOnMethods = "address")
    public void current_address ()

    {
    	By currentaddress = By.cssSelector("label[for='materialContactFormCopy7'] span");
    	util.waitForElement(currentaddress);
    	driver.findElement(currentaddress).click();
    	
    }

    
    @Test(priority = 21, dependsOnMethods = "current_address")
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
    
    @Test(priority = 22, dependsOnMethods = "address_btn", dataProvider = "nominee")
    public void nomin_name (String nme) throws InterruptedException

    {
    	By nominee_name = By.xpath("//input[@id='Nominee_FName']");
    	util.waitForElement(nominee_name);
    	util.sendKeysToElement(nominee_name, nme);
    	Thread.sleep(4000);
    	
    }

    @Test(priority = 23, dependsOnMethods = "nomin_name")
    public void nominee_marital () throws InterruptedException 

    {
    	By nominee_marital = By.xpath("//select[@id='Nominee_Relationship']");
    	Thread.sleep(2000);
    	util.waitForElement(nominee_marital);
         
         Select rltn = new Select(driver.findElement(nominee_marital));
         rltn.selectByVisibleText("Sibling");
    }	

    @Test(priority = 24, dependsOnMethods = "nominee_marital")
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

    @Test(priority = 25, dependsOnMethods = "nominee_dob")
    public void nominee_btn()

    {
    	By nomne_btn = By.xpath("//button[@ng-click=\"NomineeFormDetailsSubmit('VehicleDetails');\"]");
    	util.waitForElement(nomne_btn);
        driver.findElement(nomne_btn).click();
    
    }
	
	By nxt = By.xpath("//button[contains(@ng-hide,\"(perposalData.PolicyType | lowercase) != 'newcar'\")]");
	
	 @DataProvider(name = "engine")
	    public Object[][] engine_num() {
	        return new Object[][] {
	            { "ABCDEF12345678" },
	        };
	    }
	
	 @Test(priority = 26, dependsOnMethods = "nominee_btn", dataProvider = "engine")
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
	
	 @Test(priority = 27, dependsOnMethods = "engine", dataProvider = "engine")
	public void chasis(String ch)
	
	{
		By chasis_no = By.xpath("//input[@id='Chassis_No']");
		util.waitForElement(chasis_no);
		driver.findElement(chasis_no).sendKeys(ch);
	}
	
	
	 @Test(priority = 28, dependsOnMethods = "chasis")
	public void loan()
	
	{
		By loan = By.xpath("//label[contains(@for,'materialContactFormCopy5')]//span");
		util.waitForElement(loan);
		driver.findElement(loan).click();
	}
	
	 @Test(priority = 29, dependsOnMethods = "loan")
	public void vehicle_button()
	
	{
		By vhcle_btn = By.xpath("//button[@ng-hide=\"(perposalData.PolicyType | lowercase) != 'newcar'\"]"); 
		util.waitForElement(vhcle_btn);
		driver.findElement(vhcle_btn).click();
	}


		@Test(priority = 30, dependsOnMethods = "vehicle_button")
		public void terms()
		
		{
			By T_C = By.xpath("//label[normalize-space()='Terms & Conditions']");
			util.waitForElement(T_C);
			driver.findElement(T_C).click();
		}
		
		@Test(priority = 31, dependsOnMethods = "terms")
		public void existing_cust()
		
		{
			By existing_cust = By.xpath("//label[contains(@for,'materialContactFormCopy11')]");
			util.waitForElement(existing_cust);
			driver.findElement(existing_cust).click();
		}
		
		@Test(priority = 32, dependsOnMethods = "existing_cust")
		public void Make_payment()
		
		{
			By Mke_paymnt = By.xpath("//button[@id='btnMakePayment']");
			util.waitForElement(Mke_paymnt);
			driver.findElement(Mke_paymnt).click();
		}
		
		@Test(priority = 33, dependsOnMethods = "Make_payment")
		public void Make_payment_rel()
		
		{
			By rel_payment = By.xpath("//input[@id='btnCCavenue']");
			util.waitForElement(rel_payment);
			driver.findElement(rel_payment).click();
		}
     
		@Test(priority = 34, dependsOnMethods = "Make_payment_rel")
		public void net_banking()
		{
			By cc_avenue = By.xpath("/html[1]/body[1]/form[1]/div[1]/div[4]/div[2]/div[3]/div[3]/div[1]/ul[1]/li[3]/div[1]/span[2]");
			util.waitForElement(cc_avenue);
			driver.findElement(cc_avenue).click();

		}
	
//		By bnk = By.xpath("//select[@id='netBankingBank']");   
//        util.waitForElement(bnk);
//        driver.findElement(bnk).();
        
	@Test(priority = 35, dependsOnMethods = "net_banking")
    public void select_bank()
    {

    	By bnk = By.xpath("//select[@id='netBankingBank']");   
    	Select avenue_bank = new Select(driver.findElement((bnk)));
    	avenue_bank.selectByVisibleText("Avenues Test for New TC");  
    }
    

	@Test(priority = 36, dependsOnMethods = "select_bank")    //submit net banking
	public void Rel_payment()
	
	{
	     	
		     js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0, 200);");
//	        By mke_pmnt = By.id("SubmitBillShip");
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
    
	
	@Test(priority = 37, dependsOnMethods = "Rel_payment")  //success response
	public void success_payment()
	
	{
		By reponse = By.xpath("//button[@id='btn']");
		util.waitForElement(reponse);
		driver.findElement(reponse).click();
	}
    
	
	@Test(priority = 38, dependsOnMethods = "success_payment")      //policy number
	public void get_policyno ()
	
	{
		By policy_no = By.xpath("//div[@class='text-center']//h3[1]");
		util.waitForElement(policy_no);
		System.out.println("Policy no. is for the first testcase of the new car: "+ driver.findElement(policy_no).getText());
	} 

}