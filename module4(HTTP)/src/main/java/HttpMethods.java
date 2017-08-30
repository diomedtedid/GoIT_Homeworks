import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by User on 23.08.2017.
 */
public class HttpMethods {
    private static final String URL1 = "http://petstore.swagger.io/v2";

    public void ex1SimpleGet(){
        printSeparatorLine();
        System.out.println("Simple http GET \r\n");


        String url = URL1 + "/pet/1";
        HttpRequestBase httpGet = new HttpGet(url);

        System.out.println("Executing request: " + httpGet.getRequestLine());

        sendHttpRequest(httpGet);

    }

    private void sendHttpRequest(HttpRequestBase httpMethod) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpclient.execute(httpMethod)){

            HttpEntity httpEntity = httpResponse.getEntity();
            System.out.println("Answer header: " + httpResponse.toString());
            System.out.println("Answer content: " + new BufferedReader(new InputStreamReader(httpEntity.getContent())).readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ex2GetWithParam () {
        printSeparatorLine();

    }

    public void ex3GetWithMultParams () {
        printSeparatorLine();
        System.out.println("Http GET with parameters\r\n");

        String url = URL1 + "/user/login";

        HttpGet httpGet = new HttpGet();
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameter("user", "testuser")
                    .addParameter("password", "secret");

            httpGet.setURI(uriBuilder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        System.out.println("Executing request: " + httpGet.getRequestLine());
        sendHttpRequest(httpGet);

    }

    public void ex4SimplePost () {
        printSeparatorLine();
        System.out.println("Simple POST method\r\n");

        String url = URL1 + "/pet";
        HttpPost httpPost = new HttpPost(url);
        String massage = "{\n" +
                "  \"id\": 7,\n" +
                "  \"category\": {\n" +
                "    \"id\": 7,\n" +
                "    \"name\": \"Sharik\"\n" +
                "  },\n" +
                "  \"name\": \"Sharik\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 7,\n" +
                "      \"name\": \"Sharik\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        HttpEntity httpEntity = new StringEntity(massage, ContentType.APPLICATION_JSON);
        httpPost.setEntity(httpEntity);
        System.out.println("Executing request: " + httpPost.toString());
        System.out.println("Executing request body: " + massage);

       sendHttpRequest(httpPost);

    }

    public void ex5SimpleDelete () {
        printSeparatorLine();
    }

    public void ex6httpResponces () {
        printSeparatorLine();
    }

    public void ex7GetWithCloseSession () {
        printSeparatorLine();
    }

    public void ex8GetWithSession () {
        printSeparatorLine();
    }

    public void ex9PostWithLoging () {
        printSeparatorLine();
    }

    public void ex10PostWithFileLoad () {
        printSeparatorLine();
    }

    private void printSeparatorLine() {
        System.out.println("\r\n*************************************\r\n");
    }
}
