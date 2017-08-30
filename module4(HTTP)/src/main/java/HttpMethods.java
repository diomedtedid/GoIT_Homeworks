import com.sun.corba.se.impl.orbutil.HexOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

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

        StringEntity httpEntity = new StringEntity(massage, ContentType.APPLICATION_JSON);
        httpPost.setEntity(httpEntity);
        System.out.println("Executing request: " + httpPost.toString());
        System.out.println("Executing request headers: " + httpEntity.toString());
        System.out.println("Executing request body: \r\n" + massage);

       sendHttpRequest(httpPost);

    }

    public void ex5SimpleDelete () {
        printSeparatorLine();
        System.out.println("Simple DELETE http method\r\n");

        HttpDelete httpDelete = new HttpDelete(URL1 +"/pet/7");

        System.out.println("DELETE request :" + httpDelete.toString());
        sendHttpRequest(httpDelete);
    }

    public void ex6httpResponces () {
        printSeparatorLine();
        System.out.println("Different answers\r\n");
        System.out.println("HTTP/1.1 200 OK\n" +
                "\n" +
                "HTTP/1.1 404 Not Found\n" +
                "\n" +
                "HTTP/1.1 301 Moved Permanently\n" +
                "Location: http://www.new-location.org\n" +
                "\n" +
                "\n" +
                "HTTP/1.1 501 Not Implemented\n" +
                "\n" +
                "HTTP/1.1 500 Internal Server Error");

    }

    /*
    7. Написать запрос GET на сервер example.com который говорит серверу за закрывать соеденение.
     */
    public void ex7GetWithCloseSession () {
        printSeparatorLine();
        String url = URL1 + "/user/logout";
        HttpRequestBase httpGet = new HttpGet(url);

        System.out.println("Executing request: " + httpGet.getRequestLine());

        sendHttpRequest(httpGet);
    }


    /*
    Написать запрос GET на сервер example.com в котором передается сессия через сookie.
     */
    public void ex8GetWithSession () {
        printSeparatorLine();
        HttpGet httpGet = new HttpGet(URL1 + "/pet/1");
        httpGet.setHeader("Cookie", "ID=1");
        System.out.println(httpGet);
        sendHttpRequest(httpGet);
    }

    /*
    9. У вас есть логин форма с полями: username, password.
    Написать HTTP запрос который будет отправлен на auth.com/login для входа пользователя.
     */
    public void ex9PostWithLoging () {
        printSeparatorLine();
        System.out.println("User logging with get and parameters\r\n");
        HttpGet httpGet = new HttpGet(URL1 + "/user/login");
        httpGet.setHeader("username", "user");
        httpGet.setHeader("password", "user");
        System.out.println(httpGet );
        sendHttpRequest(httpGet);
    }

    /*
    10. Написать запрос который будет отправлен серверу при загрузке файла file.txt на сейчас upload.com
     */
    public void ex10PostWithFileLoad () {
        printSeparatorLine();
        HttpPost httpPost = new HttpPost(URL1 + "/pet/7/uploadImage");
        File file = new File("module4(HTTP)/src/main/resources/picture.jpg");
        String message = "This is a multipart post";
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addBinaryBody("upfile", file, ContentType.DEFAULT_BINARY, "module4(HTTP)/src/main/resources/picture.jpg");
        builder.addTextBody("text", message, ContentType.DEFAULT_BINARY);
//
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        sendHttpRequest(httpPost);
    }

    private void printSeparatorLine() {
        System.out.println("\r\n*************************************\r\n");
    }
}
