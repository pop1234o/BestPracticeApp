package com.liyafeng.practice.util;



import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 钉钉机器人发送消息
 * https://open-doc.dingtalk.com/docs/doc.htm?spm=a219a.7629140.0.0.karFPe&treeId=257&articleId=105735&docType=1
 *
 * @author jackl
 */
public class DingDingMessageUtil {
    public static String access_token = "f356ee163c1eafa966f6b0fecc8c642372aefcc969d79ac690e50b53c4b845bf";

    public static void sendTextMessage(final String serverName, final String msg) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message message = new Message();
                    message.setMsgtype("text");
                    String mess = "[" + serverName + "];" + msg+"  url:";
                    message.setText(new MessageInfo(mess));
                    //https://oapi.dingtalk.com/robot/send?access_token=f356ee163c1eafa966f6b0fecc8c642372aefcc969d79ac690e50b53c4b845bf
                    URL url = new URL("https://oapi.dingtalk.com/robot/send?access_token=" + access_token);
                    // 建立http连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
                    conn.connect();
                    OutputStream out = conn.getOutputStream();
//                    String textMessage = new JSONObject(message).toString();
                    String textMessage = "json string";
                    byte[] data = textMessage.getBytes();
                    out.write(data);
                    out.flush();
                    out.close();
                    System.out.println(conn.getResponseCode());
                    InputStream in = conn.getInputStream();
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    LogUtil.e("test", new String(data1));
                } catch (Exception e) {
                    e.printStackTrace();
//			log.error("DingDingMessageUtil:sendTextMessage;serverName={};msg={};error={}",serverName,msg,e);
                }
            }
        }).start();


    }


    static class Message {
        private String msgtype;
        private MessageInfo text;

        public String getMsgtype() {
            return msgtype;
        }

        public void setMsgtype(String msgtype) {
            this.msgtype = msgtype;
        }

        public MessageInfo getText() {
            return text;
        }

        public void setText(MessageInfo text) {
            this.text = text;
        }
    }

   static class MessageInfo {
        private String content;

        public MessageInfo(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


    public static void main(String[] args) {
        DingDingMessageUtil.sendTextMessage("1212", "hello");
    }
}

