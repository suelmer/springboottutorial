package org.shawn.tutorials.springbootmvctutorial;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinResponse;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@SpringUI(path = "/vaadinlogin")
@Theme("valo")
public class LoginUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(new Label("Vaadin login page.", ContentMode.HTML));
        String err = request.getParameter("error");
        if (null != err) {
            Map<String, String[]> params = request.getParameterMap();
            for (String key : params.keySet()) {
                StringBuilder sb = new StringBuilder();
                for (String value : params.get(key)) {
                    sb.append(String.format("%s,", value));
                }
                System.err.println(String.format("[%s = %s]", key, sb.toString()));
            }
            Notification.show("Login error");
        }
		layout.addComponent(new Button("Login as admin", e -> {
            // 创建HttpClient实例
            HttpClient client = new DefaultHttpClient();
            // 根据URL创建HttpPost实例
            HttpPost post = new HttpPost("http://localhost:8080/login");
            // 构造post参数
            String cookie = VaadinService.getCurrentRequest().getHeader("cookie");
            if (null != cookie) {
                post.setHeader("cookie", cookie);
            }
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("username", "admin"));
            params.add(new BasicNameValuePair("password", "password1"));
            // 编码格式转换
            UrlEncodedFormEntity entity = null;
            try {
                entity = new UrlEncodedFormEntity(params);
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            // 传入请求体
            post.setEntity(entity);
            // 发送请求，得到响应体
            org.apache.http.HttpResponse response = null;
            try {
                response = client.execute(post);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            HttpRequest springRequest = null;



            // 判断是否正常返回
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析数据
                org.apache.http.HttpEntity resEntity = response.getEntity();
                String data = null;
                try {
                    data = EntityUtils.toString(resEntity);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println(data);
            }

            Header location = response.getFirstHeader("Location");
            if (response.getStatusLine().getStatusCode() == 302) {
                for (org.apache.http.Header header : response.getAllHeaders()) {
                    VaadinService.getCurrentResponse().setHeader(header.getName(), header.getValue());
                }
                getPage().setLocation(location == null?"/":location.getValue());
            }
        }));
		setContent(layout);

	}

}
