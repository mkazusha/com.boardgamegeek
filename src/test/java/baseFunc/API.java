package baseFunc;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pages.GamePage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API {
    private BaseFunc baseFunc;
    private GamePage page;
    private static String pollValue;

    public API(BaseFunc baseFunc, GamePage page) {
        this.baseFunc = baseFunc;
        this.page = page;
    }

    public String getResponse(String baseURL, String basePath) throws MalformedURLException {
        RestAssured.baseURI = baseURL;
        RestAssured.basePath = basePath;
        Response response = given().contentType(ContentType.XML).get("/" + page.getGameID());
        return response.getBody().asString();
    }

    public static File createXMLFile(String responseBody) throws IOException {
        File file = new File("responseXML.xml");
        if (file.exists()) {
            file.delete();
        }
        FileWriter fw = new FileWriter(file);
        fw.write(responseBody);
        fw.close();
        return file;
    }

    public static Integer getVoteNumber(Element element) {
        return Integer.parseInt(element.getAttribute("numvotes"));
    }

    public static String getLanguageDependence(Element element) {
        return element.getAttribute("value");
    }

    public static String popularPoll(String response, String objectTitle) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        return getPopularPoll(findObject(prepareXMLDocument(createXMLFile(response)), objectTitle));
    }

    public static String getPopularPoll(NodeList nodes) {
        HashMap<Integer, String> polls = new HashMap<Integer, String>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            polls.put(getVoteNumber((Element) node),getLanguageDependence((Element) node));
        }
        Integer maxVote = Collections.max(polls.keySet());
        return polls.get(maxVote);
    }

    public static Document prepareXMLDocument(File file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }

    public static NodeList findObject(Document document, String objectTitle) throws XPathExpressionException {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();
        XPathExpression expr = xpath.compile(createXpath(objectTitle));
        Object result = expr.evaluate(document, XPathConstants.NODESET);
        return (NodeList) result;
    }

    public static String createXpath(String objectTitle) {
        return ".//poll[@title='" + objectTitle + "']/results/result";
    }
}
