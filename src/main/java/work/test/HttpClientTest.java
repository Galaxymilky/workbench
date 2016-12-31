package work.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import work.utils.StrUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created by niu_ben on 2016/12/29.
 */
public class HttpClientTest {

    /**
     * HttpClient连接SSL
     */
    public void ssl() {
        CloseableHttpClient httpClient = null;
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream fis = new FileInputStream(new File("D:\\tomcat.keystore"));
            try {
                //load keyStore
                trustStore.load(fis, "123456".toCharArray());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //自己CA和所有自签名的证书
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
            //只允许使用TLSv1协议
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            //创建http请求（GET）
            HttpGet httpGet = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action");
            System.out.println("Executing request" + httpGet.getRequestLine());
            CloseableHttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            System.out.println("---------------------------");
            System.out.println(response.getStatusLine());
            if (entity != null) {
                System.out.println("Response content length: " + entity.getContentLength());
                System.out.println(EntityUtils.toString(entity));
                EntityUtils.consume(entity);
            }
            response.close();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 上传文件
     */
    public void upload(String filePath, String uploadUrl) {
        if (StrUtils.isNullOrEmpty(filePath))
            filePath = "D:\\1.txt";

        if (StrUtils.isNullOrEmpty(uploadUrl))
            uploadUrl = "http://localhost:8080/myDemo/Ajax/serivceFile.action";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uploadUrl);

        FileBody fileBody = new FileBody(new File(filePath));
        StringBody stringBody = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

        HttpEntity reqHttpEntity = MultipartEntityBuilder.create().addPart("bin", fileBody).addPart("comment", fileBody).build();

        httpPost.setEntity(reqHttpEntity);

        System.out.println("executing request " + httpPost.getRequestLine());
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            System.out.println("-------------------------");
            System.out.println(response.getStatusLine());
            HttpEntity resHttpEntity = response.getEntity();
            if (resHttpEntity != null) {
                System.out.println("Response content length: " + resHttpEntity.getContentLength());
            }

            EntityUtils.consume(resHttpEntity);

            response.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    /**
     * Example how to use multipart/form encoded POST request.
     */
    public void clientMultipartFormPost(String filePath) throws IOException {
        if (StrUtils.isNullOrEmpty(filePath)) {
            System.out.println("File path not given");
            System.exit(1);
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("http://localhost:8080/servlets-examples/servlet/RequestInfoExample");

            FileBody bin = new FileBody(new File(filePath));
            StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addPart("bin", bin)
                    .addPart("comment", comment)
                    .build();

            httppost.setEntity(reqEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
    }
}
