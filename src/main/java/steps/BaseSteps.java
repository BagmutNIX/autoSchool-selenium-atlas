package steps;

import org.openqa.selenium.WebDriver;

//пока не юзею этот класс. но я так понимаю, что должен быть всегда общий класс BaseSteps, от которого экстендятся остальнйе Steps
public class BaseSteps {

    protected WebDriver driver;
    //protected String baseUrl;
 public BaseSteps(WebDriver driver) {
        this.driver = driver;
    }

    //private BaseDataProvider dataProvider;


}
