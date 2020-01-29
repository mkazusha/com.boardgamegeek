package pages;

import baseFunc.BaseFunc;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class GamePage {
    private BaseFunc baseFunc;
    private final By DESCRIPTION = By.xpath(".//span[@ng-bind-html='geekitemctrl.geekitem.data.item.polls.languagedependence|to_trusted']");

    public GamePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void checkDescription(String description) {
        WebElement actualDescription = baseFunc.getElement(DESCRIPTION);
        Assertions.assertEquals(actualDescription.getText(), description, "Language dependence text does not match the API response value.");
    }
}
