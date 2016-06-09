package crawler;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Student {
	private int id;
	private String name;
	private String branch;
	private int percentage;
	private int phone;
	private String email;	
	  
	public Student(String name, String branch, int percentage, int phone, String email) {
	      super();
	      this.name = name;
	      this.branch = branch;
	      this.percentage = percentage;
	      this.phone = phone;
	      this.email = email;
	}
	public Student() {}

	   public int getId() {
	      return id;
	   }
			
	   public String getName() {
	      return name;
	   }
		
	   public int getPhone() {
	      return phone;
	   }
		
	   public String getEmail() {
	      return email;
	   }
	   
	   public String getBranch() {
	      return branch;
	   }

	   public int getPercentage() {
	      return percentage;
	   }	
	/*
	public static void main(String[] args) throws IOException {
		
		//Configure mybatis from mybatis-config.xml
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(Resources.getResourceAsStream("mybatis/mybatis-config.xml"));
		
		
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
		FirefoxProfile profile= new FirefoxProfile();
		profile.setPreference("intl.accept_languages", "en-us");
        WebDriver driver = new FirefoxDriver(profile);

        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");
        
        System.out.println(driver.findElement(By.tagName("html")).getText());

        driver.get(driver.findElement(By.partialLinkText("Privacy")).getAttribute("href"));
        System.out.println(driver.findElement(By.tagName("html")).getText());
        //Close the browser
        driver.quit();
    }
    */
}
