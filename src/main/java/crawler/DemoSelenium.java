package crawler;

import cn.edu.hfut.dmic.webcollector.crawldb.DBManager;
import cn.edu.hfut.dmic.webcollector.crawler.Crawler;
import cn.edu.hfut.dmic.webcollector.fetcher.Executor;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BerkeleyDBManager;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


/**
 * 本教程演示如何利用WebCollector爬取javascript生成的数据
 *
 * @author hu
 */
public class DemoSelenium {

    static {
        //turn-off Selenium logging
        Logger logger = Logger.getLogger("com.gargoylesoftware.htmlunit");
        logger.setLevel(Level.OFF);
    }


    public static void main(String[] args) throws Exception {
        Executor executor=new Executor() {
            public void execute(CrawlDatum datum, CrawlDatums next) throws Exception {
            	HtmlUnitDriver driver =new HtmlUnitDriver();
                String url=datum.getUrl();
            	driver.get(url);
            	Document doc=Jsoup.parse(driver.getPageSource(), url);
                System.out.println(doc.title());
                Elements links=doc.select("a[href]");
                BufferedWriter bw = Files.newBufferedWriter(Paths.get("test.txt"));
                for(int i=0;i<links.size();i++){
                    Element link=links.get(i);
                    /*fetch the absolute path of href*/
                    String href=link.attr("abs:href");
                    /*add new href into task queue. Here only the link start with http://news.hfut.edu.cn*/
                    //if(href.startsWith("http://news.hfut.edu.cn/")){
                        //next.add(href);
                        bw.write(href+"\n");
                }
                bw.close();
            }
        };
        
        //create a BerkeleyDBManager for the crawler
        DBManager manager=new BerkeleyDBManager("crawl");
        //创建一个Crawler需要有DBManager和Executor
        Crawler crawler= new Crawler(manager,executor);
        crawler.setThreads(20);
        //maximum links to crawl in each iteration
        crawler.setTopN(100);
        crawler.addSeed("http://news.hfut.edu.cn");
        //crawling depth=2
        crawler.start(1);
        
        /*
        HtmlUnitDriver driver =new HtmlUnitDriver(true);
        //driver.setJavascriptEnabled(true);
        driver.get("http://seo.chinaz.com/?host=www.tuicool.com");
        System.out.println(driver.findElement(By.tagName("html")).getText());
        */
    }

}