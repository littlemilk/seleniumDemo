package cn.bunk.testSelenium;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import cn.bunk.testSelenium.TestFirefox.MyWebDriverListener;
/**
 * 
 * @author 洪bin
 *
 */
public class Demo {

	private WebDriver driver;
	
	@Before
	public void init() {
		
		String d = "firefox";
		
		switch (d) {
		case "firefox":
			//Firefox驱动，配置驱动程序所在路径，启动程序路径
			System.setProperty("webdriver.gecko.driver", "E:\\geckodriver.exe");
			System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
			driver = new FirefoxDriver();
			break;
			
        case "chrome":
        	//chrome驱动，默认安装启动程序已配置到环境变量，所以只需配置驱动程序路径
    		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
    		driver = new ChromeDriver();
			break;
			
        case "ie":
        	//IE驱动
    		System.setProperty("webdriver.ie.driver", "E:\\IEDriverServer.exe");
    		driver = new InternetExplorerDriver();
	        break;
		}
		
	}
	
	@Test
	public void excute() throws InterruptedException {
		driver.get("https://www.baidu.com");
		
		//切换窗口
		//changeWindow(); 
		
		//获取cookie
		//getTheCookie();  
		
		//设置等待超时
		//waitFor();  
		
		//监听事件
		//eventListener();
		
		
	}

	/**
	 * 为测试操作添加事件
	 * @author 洪bin
	 */
	private void eventListener() {
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

	/**
	 * 设置等待最大时间
	 * @author 洪bin
	 */
	private void waitFor() {
		Timeouts timeouts = driver.manage().timeouts();
		//查找元素最大等待时间设置为5秒
		timeouts.implicitlyWait(5, java.util.concurrent.TimeUnit.SECONDS); 
		//页面跳转或刷新超时时间设置为5秒
		timeouts.pageLoadTimeout(25, java.util.concurrent.TimeUnit.SECONDS);
		//设置异步执行超时时间
		timeouts.setScriptTimeout(5, java.util.concurrent.TimeUnit.SECONDS);
	}

	/**
	 * 获取cookie
	 * @author 洪bin
	 */
	private void getTheCookie() {
		Set<Cookie> cookies = driver.manage().getCookies();
		for(Cookie c : cookies) {
			System.out.println(c.getName());  //名称
			System.out.println(c.getValue()); //值
			System.out.println(c.getDomain()); //所在域
			System.out.println(c.getPath());   //路径
			System.out.println(c.getExpiry()); //过期时间
		}
	}

	/**
	 * 切换浏览器窗口
	 */
	private void changeWindow() throws InterruptedException {
		String thisHandle = driver.getWindowHandle();
		//System.out.println(thisHandle);
		driver.findElement(By.linkText("登录")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("立即注册")).click();
		Thread.sleep(1000);
		//获取所有窗口标识码
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles.size());
		for(String handle:handles) {
			
			System.out.println(handle);
			if(handle != thisHandle) {
				driver.switchTo().window(handle);
			}
		}
		Thread.sleep(1000);
		WebElement input = driver.findElement(By.name("userName"));
		input.sendKeys("dadada");
		System.out.println(input.getAttribute("class"));
	}
	
	/**
	 * 实现事件代码
	 * @author 洪bin
	 *
	 */
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
