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

    public String getHighestRank() {
        ranks = baseFunc.getAllElements(HOTNESS_RANK);
        int highestRank = 0;
        String rankValue = "";
        for (WebElement rank : ranks) {
            if (rank.getText().contains("Rank: ")) {
                if (highestRank < Integer.parseInt(rank.getText().substring(6))) {
                    highestRank = Integer.parseInt(rank.getText().substring(6));
                }
                rankValue = Integer.toString(highestRank);
            }
        }
        return rankValue;
    }

    public void pushTheHotnessGame() {
        ranks = baseFunc.getAllElements(HOTNESS_RANK);
        for (WebElement rank : ranks) {
            if (rank.getText().contains(getHighestRank())) {
                rank.click();
                break;
            }
        }
    }
}
