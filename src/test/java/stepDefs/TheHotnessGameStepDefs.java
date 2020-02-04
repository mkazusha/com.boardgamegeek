package stepDefs;

import baseFunc.BaseFunc;
import baseFunc.API;
import baseFunc.Helper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.xml.sax.SAXException;
import pages.GamePage;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.net.MalformedURLException;


public class TheHotnessGameStepDefs {
    private final String URL = "https://boardgamegeek.com";
    private final String BASEPATH = "/xmlapi/boardgame";
    private String response;
    private BaseFunc baseFunc = new BaseFunc();
    private Helper helper = new Helper(baseFunc);
    private GamePage gamePage = new GamePage(baseFunc);
    private API api = new API(baseFunc, gamePage);
    private String popularPoll;

    @Given("I open homepage of the site")
    public void open_page() {
        baseFunc.openPage(URL);
    }

    @When("I navigate to the page of the game with highest rank in The Hotness left side menu")
    public void check_rank() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        helper.pushTheHotnessGame();
    }

    @When("I retrieve information about a particular game from site api")
    public void get_api() throws MalformedURLException {
        response = api.getResponse(URL, BASEPATH);
    }

    @When("I parse response to get most voted option in {string} poll")
    public void get_most_voted_language_dependence_poll(String category) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        popularPoll = api.popularPoll(response, category);
    }

    @Then("I assert Language Dependence text presented in the page Description block equals the most voted Language Dependence level")
    public void check_description_block() {
        gamePage.checkDescription(popularPoll);
    }

    @Then("I close browser")
    public void close_browser() {
        baseFunc.closeBrowser();
    }
}
