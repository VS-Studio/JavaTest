/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

/**
 *
 * @author Administrator
 */
public class StrTest {

    public static void main(String[] args) throws Exception {
        System.out.println("呵呵");
        TestStr();
    }

    public static void charsetTest() {
        Charset charset = Charset.defaultCharset();

        System.out.println(Charset.isSupported("gb2312") ? "ok" : "false");
        System.out.println(charset);
    }

    public static void TestUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }

    public static void TestStr() {
        Calendar rightNow = Calendar.getInstance();
        Long rightNowTime = rightNow.getTimeInMillis();
        rightNow.setTimeInMillis(rightNowTime - 86400 * 2 * 1000);
        String dateStr = String.format("%d-%d-%d %d:%d:%d", rightNow.get(Calendar.YEAR), (rightNow.get(Calendar.MONTH) + 1),
                rightNow.get(Calendar.DAY_OF_MONTH), rightNow.get(Calendar.HOUR_OF_DAY),
                rightNow.get(Calendar.MINUTE), rightNow.get(Calendar.SECOND));
        
        System.out.println(dateStr);
    }

    public static void testHttpHeader() throws Exception {
        String header = "GET / HTTP/1.1\r\n"
                + "Host: www.scofier.com\r\n"
                + "Accept-Encoding: gzip,deflate,identity\r\n"
                + "\r\n";

        header = "GET https://www.baidu.com:91/xxx.html HTTP/1.1\r\n"
                + //"Host: www.baidu.com\r\n" +
                "Accept-Encoding: gzip,deflate,identity\r\n"
                + "\r\n";

        prints(getHeaderRequest(header));
    }

    public static HashMap<String, String> getHeaderRequest(String header) {
        HashMap<String, String> rets = new HashMap<String, String>();
        String[] headers = header.split("\r\n");
        if (headers != null && !"".equals(headers[0])) {
            String[] requests = headers[0].split(" ");
            rets.put("method", requests[0]);
            rets.put("port", "80");//默认80端口
            rets.put("protocol", "http");
            if (requests[1] != null) {
                rets.put("url", requests[1]);
                try {
                    URL url = new URL(requests[1]);
                    rets.put("host", url.getHost());
                    rets.put("port", "" + ((url.getPort() == -1) ? url.getDefaultPort() : url.getPort()));
                    rets.put("protocol", url.getProtocol());
                } catch (Exception e) {
                    for (String h : headers) {
                        if (h.startsWith("Host:")) {
                            String host = h.split(":")[1].trim();
                            rets.put("host", host);
                            rets.put("url", "http://" + host + requests[1]);
                            break;
                        }
                    }
                }
            }
        }
        return rets;
    }

    public static void prints(Map<String, String> lns) {
        Iterator it = lns.entrySet().iterator();
        while (it.hasNext()) {
            Entry en = (Entry) it.next();
            System.out.println("" + en.getKey() + ":" + en.getValue());
        }
    }

    public static void prints(String[] lns) {
        for (String h : lns) {
            print("=>" + h);
        }
    }

    public static void print(String ln) {
        System.out.println(ln);
    }
}
