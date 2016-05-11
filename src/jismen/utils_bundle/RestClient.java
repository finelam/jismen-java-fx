package jismen.utils_bundle;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Finelam on 24/04/2016.
 */
public class RestClient {

    private static final String SERVER = "http://192.168.56.101/jismen-symfony/web/app.php/api/";

    public RestClient() throws URISyntaxException {
    }

    /**
     *
     * @param path
     * @param params
     * @return JSONObject
     * @throws URISyntaxException
     */
    public static JSONObject get(String path, HashMap<String, Object> params) throws URISyntaxException {
        try {
            String url = getAbsoluteUrl(path);
            CloseableHttpClient client = HttpClientBuilder.create().build();
            if (params != null){
                url += '?';
                for (String key : params.keySet()){
                    url += key + "=" + params.get(key).toString() + '&';
                }
            }
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            return jsonifyResponse(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param path
     * @param params
     * @return JSONObject
     */
    public static JSONObject post(String path, HashMap<String, Object> params) {
        try {
            String url = getAbsoluteUrl(path);
            CloseableHttpClient client = HttpClientBuilder.create().build();
            List<NameValuePair> postParams = new ArrayList<NameValuePair>();
            for (String key : params.keySet()){
                postParams.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new UrlEncodedFormEntity(postParams));
            HttpResponse response = client.execute(httpPost);
            return jsonifyResponse(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param path
     * @return JSONObject
     */
    public static JSONObject delete(String path){
        try {
            String url = getAbsoluteUrl(path);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpDelete httpDel = new HttpDelete(url);
            HttpResponse response = httpClient.execute(httpDel);
            return jsonifyResponse(response);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param path
     * @param params
     * @return JSONObject
     * @throws IOException
     */
    public static JSONObject put(String path, HashMap<String, Object> params){
        try {
            String url = getAbsoluteUrl(path);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            List<NameValuePair> putParams = new ArrayList<NameValuePair>();
            for (String key : params.keySet()){
                putParams.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
            HttpPut httpPut = new HttpPut(url);
            httpPut.setEntity(new UrlEncodedFormEntity(putParams));
            HttpResponse response = httpClient.execute(httpPut);
            return jsonifyResponse(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param url
     * @return String
     */
    private static String getAbsoluteUrl(String url){
        return SERVER + url;
    }

    /**
     *
     * @param response
     * @return JSONObject
     * @throws IOException
     */
    private static JSONObject jsonifyResponse(HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() != 200){
            return new JSONObject("{success:false}");
        }
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null){
            result.append(line);
        }
        return new JSONObject(result.toString());
    }
}
