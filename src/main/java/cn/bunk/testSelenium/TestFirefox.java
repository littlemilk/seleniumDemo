package cn.bunk.testSelenium;

import java.beans.EventHandler;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class TestFirefox {

	@Test
	public void startTest() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");
		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
		WebDriver driver = new FirefoxDriver();
//		driver.get("http://www.baidu.com");
//		WebElement input = driver.findElement(By.name("wd")); input.sendKeys("脚本之家");
//		System.out.println("输入内容....."); driver.findElement(By.id("su")).click();
//		System.out.println("点击搜索......"); Thread.sleep(2000); List<WebElement> list =
//		driver.findElement(By.className("t")).findElements(By.tagName("a"));
//		list.get(1).click();
//		driver.navigate();
//		driver.findElement(By.xpath("//*[@id=\"kw\"]")).sendKeys("选择成功");
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		WebElement input = (WebElement) jse.executeScript("return jQuery.find('#kw')");
//		WebElement btn = (WebElement) jse.executeScript("return jQuery.find('.bg s_btn')");
//		input.sendKeys("selenium");
//		btn.click();
		
		
		
		//注册监听驱动
		EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
		eDriver.register(new MyWebDriverListener());
		eDriver.get("http://www.baidu.com");
		eDriver.findElement(By.id("kw")).sendKeys("the life's struggle");
		eDriver.findElement(By.id("su")).click();
		//故意触发空指针异常
		try {
			eDriver.findElement(By.id("xxxx"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
	}
	class MyWebDriverListener implements WebDriverEventListener{

		@Override
		public void beforeAlertAccept(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterAlertAccept(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterAlertDismiss(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeAlertDismiss(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeNavigateTo(String url, WebDriver driver) {
			System.out.println("页面跳转前的URL为：" + driver.getCurrentUrl());
			
		}

		@Override
		public void afterNavigateTo(String url, WebDriver driver) {
			System.out.println("页面跳转后的URL为：" + driver.getCurrentUrl());
			
		}

		@Override
		public void beforeNavigateBack(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterNavigateBack(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeNavigateForward(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterNavigateForward(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeNavigateRefresh(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterNavigateRefresh(WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeFindBy(By by, WebElement element, WebDriver driver) {
			System.out.println("查找元素时的条件为：" + by.toString());
			
		}

		@Override
		public void afterFindBy(By by, WebElement element, WebDriver driver) {
			System.out.println("找到元素的条件为：" + by.toString());
			
		}

		@Override
		public void beforeClickOn(WebElement element, WebDriver driver) {
			System.out.println("页面单击的元素为：" + element.getAttribute("value"));
			
		}

		@Override
		public void afterClickOn(WebElement element, WebDriver driver) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("点击元素后跳转的URL为：" + driver.getCurrentUrl());
		}

		@Override
		public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
			System.out.println("更改前的值为：" + element.getAttribute("value"));
			
		}

		@Override
		public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
			System.out.println("更改后的值为：" + element.getAttribute("value"));
			
		}

		@Override
		public void beforeScript(String script, WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterScript(String script, WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeSwitchToWindow(String windowName, WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterSwitchToWindow(String windowName, WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onException(Throwable throwable, WebDriver driver) {
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yy_MM_dd_hh_mm_ss");
			String now = df.format(date);
			File scr = ((TakesScreenshot)driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
			File file = new File("E://" + now + ".png");
			try {
				FileUtils.copyFile(scr, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("异常原因：" + throwable.getMessage());
			
		}

		@Override
		public <X> void beforeGetScreenshotAs(OutputType<X> target) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeGetText(WebElement element, WebDriver driver) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterGetText(WebElement element, WebDriver driver, String text) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
