package org.topnetwork.rpclib;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CasonCai
 * @since 2021/5/10 10:28 上午
 **/
public class TopRpcClient {

    private String ip;

    private int port;

    private String url;

    public TopRpcClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.url = "http://" + ip + ":" + port;
    }

    public String listVoteUsed(String address) {
        Map<String, String> params = new HashMap<>();
        params.put("account_addr", "T20000MVfDLsBKVcy1wMp4CoEHWxUeBEAVBL9ZEa");
        params.put("node_account_addr", address);
        String resp = callRpc("T00000LZoQ25d4ZJibqRhrRQvDT7TcQ5M1Jwwq3o", "listVoteUsed", params);

        return resp;
    }

    private String callRpc(String target_account_addr, String method, Map<String, String> body) {
        String bodyJson = JSON.toJSONString(body);

        Map<String, String> params = new HashMap<>();
        params.put("target_account_addr", target_account_addr);
        params.put("method", method);
        params.put("body", bodyJson);
        params.put("sequence_id", "80");
        params.put("version", "1.0");

        try {
            return callRequest(url, params);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private String callRequest(String url, Map<String, String> params) throws IOException {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPost post = new HttpPost(url);

            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, Charset.forName("utf-8"));
            post.setEntity(formEntity);

            CloseableHttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //返回json格式
                String res = EntityUtils.toString(response.getEntity());
                return res;
            }
        }

        return null;
    }


}
