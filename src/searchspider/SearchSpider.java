/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchspider;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

/**
 *
 * @author ACCURA
 */
public class SearchSpider {

    public static void main(String[] args) throws IOException {
        final WebClient webClient = new WebClient();
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        HtmlPage page1 = webClient.getPage("http://www.google.com");
        HtmlInput input1 = page1.getElementByName("q");
        input1.setValueAttribute("add.php?bookid=");
        HtmlSubmitInput submit1 = page1.getElementByName("btnK");
        page1 = submit1.click();
        HtmlDivision search = page1.getHtmlElementById("ires");
        DomNodeList<DomNode> divs = page1.querySelectorAll("#ires ._NId .srg .g");
        for (DomNode div : divs) {
            DomNode titleNode = div.querySelector(".rc .r a");
            DomNode linkNode = div.querySelector(".rc .s cite");
            //System.out.println(titleDiv);
            String title = titleNode.asText();
            String link = linkNode.asText();
            System.out.println(" " + link);
            List<NameValuePair> urlArgs = URLEncodedUtils.parse(link, Charset.defaultCharset());
            System.out.println(urlArgs);
            for (NameValuePair urlArg : urlArgs) {
                System.out.println(urlArg.getValue());
            }
            System.out.println(".............");

            webClient.close();
        }
    }

}
