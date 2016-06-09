package crawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import edu.umass.cs.benchlab.har.*;
import edu.umass.cs.benchlab.har.tools.HarFileReader;

public class SeleniumTest {

	public static void main(String[] args) throws IOException {
		// HtmlUnitDriver driver =new HtmlUnitDriver(true);
		// driver.setJavascriptEnabled(true);
		// driver.get("http://www.sears.com");
		// System.out.println(driver.findElement(By.tagName("html")).getText());
		// driver.quit();
		/*
		 * FirefoxDriver driver2 = new FirefoxDriver();
		 * driver2.get("http://www.sears.com"); //
		 * System.out.println(driver2.findElement(By.tagName("html")).getText())
		 * ; //System.out.println(driver2.getPageSource());
		 * 
		 * //try (BufferedWriter bw =
		 * Files.newBufferedWriter(Paths.get("test.txt"))) { BufferedWriter bw =
		 * Files.newBufferedWriter(Paths.get("test.txt"));
		 * bw.write(driver2.getPageSource()); //} //finally{ driver2.quit(); //}
		 */
		BrowserMobProxy proxy = new BrowserMobProxyServer();
		proxy.start(0);

		// get the Selenium proxy object
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

		// configure it as a desired capability
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

		// start the browser up
		WebDriver driver = new FirefoxDriver(capabilities);

		// enable more detailed HAR capture, if desired (see CaptureType for the
		// complete list)
		proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

		// create a new HAR with the label "yahoo.com"
		proxy.newHar("news.hfut.edu.cn");

		// open yahoo.com
		driver.get("http://news.hfut.edu.cn");

		// get the HAR data
		Har har = proxy.getHar();
		File harFile = new File("1.txt");
		har.writeTo(harFile);

		proxy.stop();
		driver.quit();

		HarFileReader r = new HarFileReader();
		HarLog log = r.readHarFile(harFile);
		HarBrowser browser = log.getBrowser();
		HarEntries entries = log.getEntries();
		List<HarPage> pages = log.getPages().getPages();
		for (HarPage page : pages) {
			System.out.println("page start time: " + ISO8601DateFormatter.format(page.getStartedDateTime()));
			System.out.println("page id: " + page.getId());
			System.out.println("page title: " + page.getTitle());
		}

	}
}
