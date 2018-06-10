package other.HttpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么) 
 * @project_name：${project_name}
 * @author ${user}  
 * @date ${date} ${time} 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class Test {
    public static void main(String[] args) throws Exception {

        URI uri = new URIBuilder().setScheme("http").setHost("***:**")
                .setPath("/***/***").build();

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("params", "test"));

        HttpResponse httpResponse = httpClient.execute(httpPost);

        StatusLine httpStatus = httpResponse.getStatusLine();
        HttpEntity httpEntity = httpResponse.getEntity();

        System.out.println("httpStatusline: " + httpStatus);
        System.out.println("strEntity: " + EntityUtils.toString(httpEntity));
        EntityUtils.consume(httpEntity);
    }

    public static String sendPost(final URI uri,
                                  final List<NameValuePair> params) throws ClientProtocolException,
            IOException,
            NoSuchAlgorithmException,
            KeyManagementException {
        String result = null;
        SSLContext sslContext = SSLContext.getInstance("SSL");

        // set up a TrustManager that trusts everything
        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }}, new SecureRandom());

        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(new SSLSocketFactory(sslContext)).build();

        HttpPost httpPost = new HttpPost(uri);

        httpPost.addHeader("Content-type", "application/json");

        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }
        return result;
    }
}
