package com.automationpracticeold;

import core.BaseTest;
import com.google.common.collect.Ordering;
import com.sun.org.glassfish.gmbal.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UISearchSortCartTestOld extends BaseTest {

    @Description("Perform tests: search, sort, add to cart")
    //@Parameters(value = {"Dress", "t-shirt", "Summer"})
    @Test(dataProvider = "searchQueryDataProvider")
    public void uiSearchSortCartTest(String query) throws InterruptedException {

        // 1. открываем сайт http://automationpractice.com/
        driver.get(baseUrl);

        // 2. в поле поиска вводим ключевое слово: 'Summer' и нажимаем значок поиска (лупу)
        WebElement search = driver.findElement(By.xpath("//input[@id='search_query_top']"));
        search.sendKeys(query);
        driver.findElement(By.xpath("//button[@name='submit_search']")).click();

        // 3. проверяем, что над списком продуктов отображается надпись 'SEARCH  "SUMMER"'
        WebElement searchResultLabel = driver.findElement(By.xpath("//span[@class='lighter']"));
        String searchResultLabelText = searchResultLabel.getText().replaceAll("\"", "").toLowerCase();
        Assert.assertEquals(searchResultLabelText, query.toLowerCase());

        // 4. открываем дропдаун сортировки и выбираем опцию 'Price: Highest first'
        driver.findElement(By.xpath("//div[@id='uniform-selectProductSort']")).click();
        driver.findElement(By.xpath("//select[@id='selectProductSort']/option[@value='price:desc']")).click();

        // 5. проверяем, что элементы отсортированы в соответствии с выбранной опцией (сейчас сортировка идёт по старой
        // цене - если у товара есть скидка, нужно смотреть на старую цену)
        //TODO with old price (пока что начала делать с просто прайсом, т.к. так проще)
        List<WebElement> priceElement = driver.findElements(By.xpath("//span[@class='price product-price']"));
        List<Double> priceNumber = new ArrayList<Double>();

        for (int i = 0; i < priceElement.size(); i++) {
            String priceText = priceElement.get(i).getText();//
            if (priceText.isEmpty())
                continue;
            priceText = priceText.replace("$", "");
            System.out.println(priceText);
            priceNumber.add(Double.valueOf(priceText));
        }

        System.out.println();
        for (int i = 0; i < priceNumber.size(); i++) {
            System.out.println(priceNumber.get(i));
        }

        //Collections.sort(priceNumber);
        boolean isSorted = Ordering.natural().isOrdered(priceNumber);
        System.out.println("Is sorted: " + isSorted);

        if (!isSorted)
            System.out.println("Prices are sorted incorrect");

/*        try {
            Assert.assertEquals(isSorted, true);
        } catch (NumberFormatException e) {
            System.err.println("Prices are sorted incorrect!");
        }*/

        // 6. берем первый из найденных товаров и запоминаем его полное название и цену
        WebElement productName = driver.findElement(By.xpath("//*[@id='center_column']//*[@class='product-name']"));
        String productNameText = productName.getText();
        System.out.println("Saved Product: " + productNameText);

        WebElement productPrice = driver.findElement(By.xpath("//*[@id='center_column']//*/div[1]/*[@class='price product-price']"));
        String productPriceText = productPrice.getText();
        System.out.println("Saved Price: " + productPriceText);
        //Спешл прайс: *[@data-title = 'Unit price']/span/span[@class='price special-price']
        //старый прайс: //*[@data-title = 'Unit price']/span/span[@class='old-price']
        //прайс: //*[@data-title = 'Unit price']/span/span[@class='price']

        // 7. добавляем его в корзину
        Actions action = new Actions(driver);
        action.moveToElement(productName).build().perform();
        WebElement addToCartBtn = driver.findElement(By.xpath("//a[@title='Add to cart']"));
        addToCartBtn.click();

        // 8. Открываем корзину и сравниваем название и цену в колонке "Total" у товара, на соответствие с сохраненными значениями
        WebElement checkoutBtn = driver.findElement(By.xpath("//a[@title='Proceed to checkout']"));
        checkoutBtn.click();

        WebElement productNameInCart = driver.findElement(By.xpath("//td/p[@class='product-name']"));
        String productNameInCartText = productNameInCart.getText();
        System.out.println("Product name in cart: " + productNameInCartText);

        //WebElement productPriceInCart = driver.findElement(By.xpath("//span[@class='price special-price']"));
        WebElement productPriceInCart = driver.findElement(By.xpath("//td[@class='cart_total'][@data-title='Total']"));
        String productPriceInCartText = productPriceInCart.getText();
        System.out.println("Product price in cart: " + productPriceInCartText);

        Assert.assertEquals(productNameInCartText, productNameText);
        Assert.assertEquals(productPriceInCartText, productPriceText);

        driver.findElement(By.xpath("//*[@class='icon-trash']")).click();

        // 9. используя аннотацию параметризации тестов, добавьте кроме 'Summer' сценарии поиска 'Dress' и 't-shirt'
        //(пока сделала через DataProvider)
    }

    @DataProvider(name = "searchQueryDataProvider")
    public Object[][] searchQueryDataProvider() {
        List<String> data = new ArrayList<>();
        data.add("Summer");
        data.add("t-shirt");
        data.add("Dress");

        Object[][] result = new Object[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            result[i] = data.get(i).split(",");
        }
        System.out.println(result.toString());
        return result;
    }
}
