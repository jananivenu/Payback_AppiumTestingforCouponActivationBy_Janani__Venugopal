package com.appiumtesting.cucumber;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CouponActivation {
	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void setup() throws InterruptedException {
		try {
			System.out.println("Payback App installation");
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
			dc.setCapability("adbExecTimeout", 50000);
			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
			dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, ""); // Emulator Name
			dc.setCapability(MobileCapabilityType.UDID, ""); // ADB Id
			dc.setCapability("appPackage", "com.android.vending");
			dc.setCapability("appActivity", ".AssetBrowserActivity");
			dc.setCapability("appWaitActivity", ".AssetBrowserActivity");
			// Setting up Android Driver
			driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), dc);

			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView")));
			driver.findElement(By.xpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView"))
					.click();
			WebElement searchbox = driver.findElement(By.xpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.EditText"));
			searchbox.click();
			searchbox.clear();
			searchbox.sendKeys("PAYBACK - Karte und Coupons");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement payback = driver.findElement(By.xpath(
					"//android.widget.LinearLayout[@content-desc=\"Search for 'PAYBACK - Karte und Coupons' \"]/android.widget.LinearLayout/android.widget.TextView"));
			payback.click();

			WebElement Install = driver.findElement(By.xpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.Button"));
			Install.click();
			System.out.println("App Installation initiated");
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//android.widget.Button[contains(@text, 'Open')]")));
			driver.quit();
			Thread.sleep(160000);
			driver = null;
		}

		catch (MalformedURLException e) {

			System.out.println("Cause is" + e.getCause());
			System.out.println("Message is" + e.getMessage());
			e.printStackTrace();
		}
	}

	@Given("User is at the home page")
	public void user_is_at_home_page() throws MalformedURLException {
		DesiredCapabilities dc1 = new DesiredCapabilities();
		dc1.setCapability("appPackage", "de.payback.client.android");
		dc1.setCapability("appActivity", "de.payback.app.deeplinks.StarterActivity");
		dc1.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
		dc1.setCapability("adbExecTimeout", 50000);
		dc1.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc1.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc1.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
		dc1.setCapability(MobileCapabilityType.DEVICE_NAME, ""); // Emulator Name
		dc1.setCapability(MobileCapabilityType.UDID, ""); // ADB Id
		driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), dc1);
		WebElement UserLogin = driver.findElement(By.id("de.payback.client.android:id/welcome_loginbutton"));
		UserLogin.click();
		/***
		 * Could not inspect the Login screen further because of the security
		 * restrictions Hence,used an existing session for the navigation in APP
		 ***/
		System.out.println("User is at home page");
		// throw new io.cucumber.java.PendingException();
	}

	@When("User clicks on the Coupons icon")
	public void user_clicks_on_the_coupons_icon() {

		WebElement Coupon = driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@content-desc='Coupons']/android.widget.FrameLayout/android.widget.ImageView"));
		Coupon.click();
		System.out.println("Inside the Coupons page");
	}

	@And("User selects the coupon in the coupons page")
	public void user_select_the_coupons_in_the_coupon_page() {
		WebElement Filter = driver.findElement(By.id("de.payback.client.android:id/filter_button"));
		Filter.click();
		WebElement REWE = driver.findElement(By.xpath(
				"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]/android.view.ViewGroup/androidx.cardview.widget.CardView/android.view.ViewGroup/android.widget.ImageView"));
		REWE.click();
		System.out.println("Selected the REWE coupon");

	}

	@Then("User activate the first coupon")
	public void user_activate_the_first_coupon() {
		WebElement activateCoupon = driver.findElement(By.id("de.payback.client.android:id/not_activated_button"));
		activateCoupon.click();
		System.out.println("Activated the first coupon");

	}

	@After
	public void teardown() {
		driver.quit();
	}
}
