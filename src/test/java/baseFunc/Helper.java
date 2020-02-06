package baseFunc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Helper {
    private final By HOTNESS_RANK = By.xpath(".//span[@class='ng-binding']");
    private BaseFunc baseFunc;
    List<WebElement> ranks;

    public Helper (BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void pushTheHotnessGame() {
        ranks = baseFunc.getAllElements(HOTNESS_RANK);
        for (WebElement rank : ranks) {
            if (rank.getText().contains("Rank: ")) {
                if (Integer.parseInt(rank.getText().substring(6)) == 1) {
                    rank.click();
                    break;
                }
            }
        }
    }
}
