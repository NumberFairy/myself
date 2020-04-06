package cn.ihep.test01.service;


import cn.ihep.test01.utils.GithubUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
public class AccountService {

    public void getTokenFromGithub(String code) throws Exception {
        System.out.println(code);
        String USER_AGENT = "Mozilla/5.0";

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(GithubUtils.getTokenUrl(code));
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        String responseStr = rd.readLine();

        String accessToken = responseStr.substring(responseStr.indexOf('=') + 1, responseStr.indexOf('&'));

        //下面就是用accessToken来获取用户信息的操作
        //添加请求头
        HttpClient client2 = HttpClientBuilder.create().build();
        HttpGet request2 = new HttpGet(GithubUtils.getInfoUrl(accessToken));
        HttpResponse response2 = client.execute(request2);

        BufferedReader rd2 = new BufferedReader(
                new InputStreamReader(response2.getEntity().getContent()));

        String line = "";
        StringBuilder result = new StringBuilder();
        while ((line = rd2.readLine()) != null) {
            result.append(line);
        }

        String infos = result.toString();
        System.out.println(infos);
        JSONObject info = JSONObject.parseObject(infos);
        System.out.println(info.getString("login"));
        System.out.println(info.getString("id"));
        System.out.println(info.getString("node_id"));
        System.out.println(info.getString("avatar_url"));
        System.out.println(info.getString("bio"));
        System.out.println(info.getString("name"));
        System.out.println(info.getString("html_url"));
    }
}
