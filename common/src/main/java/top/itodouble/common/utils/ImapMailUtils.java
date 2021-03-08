package top.itodouble.common.utils;

import com.sun.mail.imap.IMAPMessage;
import org.apache.commons.lang.StringUtils;
import top.itodouble.common.enums.YesOrNoEnum;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @Description imap邮件公共方法
 * @ClassName ImapMailUtils
 * @Author lanshunbin
 * @date 2020.04.16 14:51
 */
public class ImapMailUtils {

    public ImapMailUtils(String host, String username, String password) {
        this.host = host;
        this.username = username;
        this.password = password;
    }

    public ImapMailUtils(String host, String port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public ImapMailUtils(String host, String port, Integer sslFlag, String username, String password) {
        this.host = host;
        this.port = port;
        this.sslFlag = sslFlag;
        this.username = username;
        this.password = password;
    }

    private String protocol = "imap";
    private Folder folder;
    private Store store;
    private String host;
    private String port;
    private Integer sslFlag;
    private String username;
    private String password;
    private final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    private Folder openFolderInbox() throws MessagingException {
        if (null != folder && null != store) {
            return folder;
        }
        Properties props = new Properties();
        props.put("mail.store.protocol", protocol);
        props.put("mail.imap.host", this.host);
        if (StringUtils.isNotBlank(this.port)) {
            props.put("mail.imap.port", this.port);
        }
        if (null != this.sslFlag && YesOrNoEnum.YES.getSnData().equals(this.sslFlag)) {
            props.setProperty("mail.imap.ssl.enable", "true");
            props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
        }
        // 创建Session实例对象
        Session session = Session.getInstance(props);
        // 创建IMAP协议的Store对象
        store = session.getStore(protocol);
        // 连接邮件服务器
        store.connect(this.username, this.password);
        // 获得收件箱
        folder = store.getFolder("INBOX");
        // 以读写模式打开收件箱
        folder.open(Folder.READ_WRITE);
        return folder;
    }

    /**
     * 获取所有收到的邮件
     *
     * @return
     */
    public List<Message> getAllMessages() throws MessagingException {
        Folder folder = this.openFolderInbox();
        // 获得收件箱的邮件列表
        Message[] allMessages = folder.getMessages();
        if (allMessages.length > 0) {
            return Arrays.asList(allMessages);
        }
        return null;
    }

    /**
     * 获取所有未读邮件
     *
     * @return
     */
    public List<Message> getAllNewMessages() throws MessagingException {
        Folder folder = this.openFolderInbox();
        Message[] messages = folder.getMessages(folder.getMessageCount() - folder.getUnreadMessageCount() + 1, folder.getMessageCount());
        return Arrays.asList(messages);
    }

    /**
     * 设置为已读
     *
     * @param message
     * @throws MessagingException
     */
    public void setRead(Message message) throws MessagingException {
        IMAPMessage msg = (IMAPMessage) message;
        msg.setFlag(Flags.Flag.SEEN, true);   //设置已读标志
    }

    /**
     * 判断邮件中是否包含附件
     *
     * @param part 邮件内容
     * @return 邮件中存在附件返回true，不存在返回false
     * @throws MessagingException
     * @throws IOException
     */
    public static boolean isContainAttachment(Part part) throws MessagingException, IOException {
        boolean flag = false;
        if (part.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    flag = true;
                } else if (bodyPart.isMimeType("multipart/*")) {
                    flag = isContainAttachment(bodyPart);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("application") != -1) {
                        flag = true;
                    }

                    if (contentType.indexOf("name") != -1) {
                        flag = true;
                    }
                }

                if (flag) break;
            }
        } else if (part.isMimeType("message/rfc822")) {
            flag = isContainAttachment((Part) part.getContent());
        }
        return flag;
    }

    /**
     * 获取文件
     *
     * @param part
     * @return
     * @throws MessagingException
     * @throws IOException
     */
    public static List<BodyPart> parseAtta(Part part) throws MessagingException, IOException {
        if (part.isMimeType("multipart/*")) {
            List<BodyPart> bodyPartList = new ArrayList<>();
            Multipart multipart = (Multipart) part.getContent();
            //复杂体邮件包含多个邮件体
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                //获得复杂体邮件中其中一个邮件体
                BodyPart bodyPart = multipart.getBodyPart(i);
                bodyPartList.add(bodyPart);
            }
            return bodyPartList;
        } else if (part.isMimeType("message/rfc822")) {
            parseAtta((Part) part.getContent());
        }
        return null;
    }

    /**
     * 获取发件人邮件地址
     *
     * @param message
     * @return
     */
    public static String getFromAddress(Message message) {
        Address[] fromAddress;
        try {
            fromAddress = message.getFrom();
            InternetAddress from = (InternetAddress) fromAddress[0];
            return from.getAddress();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭
     */
    public void close() {
        if (null != folder) {
            try {
                folder.close(true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        if (null != store) {
            try {
                store.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
