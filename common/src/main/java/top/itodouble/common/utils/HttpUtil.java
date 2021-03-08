package top.itodouble.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();
    private static final String UTF_8 = "UTF-8";

    private HttpUtil() {
    }

    /*
     * 返回接口状态码
     * */
    public static Integer getHttpCode(String url) {
        try {
            URL u = new URL(url);
            URLConnection uc = u.openConnection();
            HttpURLConnection huc = (HttpURLConnection)uc;
            return huc.getResponseCode();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送HttpGet请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendGet(String url) {

        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = HTTP_CLIENT.execute(httpget);
        } catch (IOException e) {
            logger.error("", e);
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            logger.error("", e);
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return result;
    }
    /**
     *
    * Description: 添加header的httpGet
    *
    * @param url
    * @param hearders
    * @return
     * @throws IOException
     */
    public static String sendHeardGet(String url, Map<String, String> hearders) {
        HttpGet httpget = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30 * 1000)
                .setConnectTimeout(10 * 1000)
                .build();
        httpget.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            for (String key : hearders.keySet()) {
                String value = hearders.get(key);
                if (StringUtils.isNotBlank(value)) {
                    httpget.addHeader(key, value);
                }
            }
            response = HTTP_CLIENT.execute(httpget);
        } catch (IOException e) {
            logger.error("", e);
        }
        String result = null;
        try {
            if (null == response) {
                return result;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
            }
        } catch (ParseException | IOException e) {
            logger.error("", e);
        } finally {
            try {
            	if(null != response){
            		response.close();
            	}
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为map
     *
     * @param url
     * @param map
     * @return
     * @throws IOException
     */
    public static String sendPost(String url, Map<String, String> map) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (Entry<String, String> entry : map.entrySet()) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
        HttpPost httppost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30 * 1000)
                .setConnectTimeout(10 * 1000)
                .build();
        httppost.setConfig(requestConfig);
        httppost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = HTTP_CLIENT.execute(httppost);
        } catch (IOException e) {
            logger.error("", e);
        }
        String result = null;
        if (null == response) {
            return result;
        }
        HttpEntity entity1 = response.getEntity();
        try {
            if (null != entity1) {
                result = EntityUtils.toString(entity1);
            }
        } catch (ParseException | IOException e) {
            logger.error("", e);
        }
        return result;
    }

    /**
     * 发送不带参数的HttpPost请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String sendPost(String url) {
        HttpPost httppost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            response = HTTP_CLIENT.execute(httppost);
        } catch (IOException e) {
            logger.error("", e);
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            logger.error("", e);
        }
        return result;
    }
    /**
     *
    * Description: post
    *
    * @param url
    * @param body
    * @return
     * @throws IOException
     */
    public static String sendPost(String url, String body) {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            if (StringUtils.isNotBlank(body)) {
                StringEntity entity = new StringEntity(body, Consts.UTF_8);
                entity.setContentEncoding(UTF_8);
                httpPost.setEntity(entity);
            }
            response = HTTP_CLIENT.execute(httpPost);
        } catch (IOException e) {
            logger.error("", e);
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity);
        } catch (ParseException | IOException e) {
            logger.error("", e);
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为jsonObj
     *
     * @param url
     * @param jsonObj
     * @return
     * @throws Exception
     */
    public static String httpPostWithJson(String url, JSONObject jsonObj) {
        String result = null;

        HttpPost post = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();

            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");

            // 构建消息实体
            StringEntity entity = new StringEntity(jsonObj.toString(), Consts.UTF_8);
            entity.setContentEncoding(UTF_8);
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);

            // 检验返回码
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity1 = response.getEntity();
                result = EntityUtils.toString(entity1);
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            if (post != null) {
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    logger.error("", e);
                }
            }
        }
        return result;
    }

    /**
     * post传入附件 返回字符串
     * @param postUrl 请求地址
     * @param file 附件
     * @param fileParamName 附件请求参数名
     * @param textBody 请求体
     * @return
     * @throws Exception
     */
    public static String postWithFile(String postUrl, File file, String fileParamName, Map<String, String> textBody) throws Exception {
        HttpPost httpPost = new HttpPost(postUrl);
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart(fileParamName, new FileBody(file));
        for (Entry<String,String> entry:textBody.entrySet()){
            if (StringUtils.isNotBlank(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())){
                multipartEntityBuilder.addTextBody(entry.getKey(), entry.getValue());
            }
        }
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        HttpEntity req = multipartEntityBuilder.build();

        httpPost.setEntity(req);
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                .build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        ConnectionSocketFactory plainSF = new PlainConnectionSocketFactory();
        Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslsf)
                .register("http", plainSF)
                .build();
        HttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(r);
        CloseableHttpClient httpClient = HttpClients.custom()
                .disableCookieManagement()
                .setRedirectStrategy(new DefaultRedirectStrategy())
                .setConnectionManager(cm)
                .build();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String result = "";
        try {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity, "utf-8");
            }
        } finally {
            response.close();
        }
        return result;
    }

    /**
     * webservice调用
     * @param url
     * @param targetNameSpace
     * @param methodName
     * @param body
     * @return
     * @throws ServiceException
     * @throws RemoteException
     */
    public static String webserviceSend(String url, String targetNameSpace, String methodName,String paramName, String body) throws ServiceException, RemoteException {
        System.out.println("------------------------请求webservice");
        String responseMsg;
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(url);
        call.setOperationName(new QName(targetNameSpace, methodName));
        call.setReturnType(Constants.XSD_STRING);
        call.addParameter(paramName, XMLType.XSD_STRING, ParameterMode.IN);
        responseMsg = (String)call.invoke(new Object[] {body});
        return responseMsg;
    }

    public static String urlEncodeUTF8(String URL) {
        String result = URL;
        try {
            result = URLEncoder.encode(URL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String urlDecodeUTF8(String URL) {
        String result = "";
        try {
            result = URLDecoder.decode(URL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


}
