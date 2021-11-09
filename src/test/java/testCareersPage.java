import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.*;

public class testCareersPage {

    public static void main(String[] args) throws InterruptedException{
        //System.out.println("Hello ConfigAir!");

        //Navigate to Home page
        String baseUrl = "https://www.configair.com/";

        System.setProperty("webdriver.chrome.driver", "/Users/odnoobayaraa/Project/Selenium/Task/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get(baseUrl);

        //Navigate to Careers page

        //Choose the main menu About us
        WebElement mainmenu = driver.findElement(By.id("menu-item-33"));
        Actions actions = new Actions(driver);
        actions.moveToElement(mainmenu).build().perform();
        Thread.sleep(300);

        //Choose the submenu Careers
        WebElement submenu = driver.findElement(By.id("menu-item-189"));
        actions.moveToElement(submenu).click().build().perform();
        Thread.sleep(300);

        //Verify matching the page text with "We Are Hiring"
//        String greeting = driver.findElement(By.xpath("//h1[text()='We are hiring']")).getText();
//        Assert.assertEquals(greeting, "We Are Hiring", "Greeting message did not match");
        try {
            String greeting = driver.findElement(By.xpath("//h1[text()='We Are Hiring']")).getText();
            Assert.assertEquals(greeting, "We Are hiring", "Greeting message did not match");
            System.out.println("Greeting message was matched");

        } catch (WebDriverException e){
            System.out.println("Unsuccessful.");
        }


        //Find top 3 the most frequently used words

        ArrayList<String> words = new ArrayList<String>();
        ArrayList<String> mostUsedWords = new ArrayList<String>();
        ArrayList<Integer> numberOfUsed = new ArrayList<Integer>();
        List<double[]> values = new ArrayList<double[]>(2);
        Map<String, Integer> unsortMap = new HashMap<>();

        String word=null;
        int count = 0, maxCount = 0;


        List<WebElement> lines = driver.findElements(By.tagName("p"));
//        List<WebElement> lines = driver.findElements(By.tagName("p" | "h3");
//        List<WebElement> lines = driver.findElements(By.cssSelector("div:contains('elementor-element elementor-element')"));
//        List<WebElement> lines = driver.findElements(By.tagName("h3"));


        System.out.println(lines.size());

        for (WebElement webElement : lines) {
            String name = webElement.getText();
            //String string[] = name.toLowerCase().split("([,.\\s]+)");
            String string[] = name.toLowerCase().split(" ");

            for (String s : string){
                words.add(s);
                //System.out.println(words);
            }
        }

        System.out.println(words);

        //Determine the most repeated word in the list
        for(int i = 0; i < words.size(); i++) {
            count = 1;
            //Count each word in the list and store it in variable count
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(i).equals(words.get(j))) {
                    count++;
                }
            }

            //If maxCount is less than count then store value of count in maxCount
            //and corresponding word to variable word
            if(count > maxCount){
                maxCount = count;
                word = words.get(i);
                //System.out.println("Most repeated word: " + word);
                unsortMap.put(word, maxCount);

            }

        }

        // Order values in the map to find the most repeated words
        Map<String, Integer> orderedMap = new LinkedHashMap<>();
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> orderedMap.put(x.getKey(), x.getValue()));


        //Get first 3  the most frequently used words and their number of used
        int c = 0;
        Iterator<String> iterator =  orderedMap.keySet().iterator();
        while(iterator.hasNext() && c <2) {
            String key = iterator.next();
            System.out.println("Key: " + key + ", Value: " + orderedMap.get(key));
            c++;
        }



        //Close browser
        //driver.quit();

    }


}
