package com.zee.utl;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * file输入输出工具类
 * @author lxy
 *
 */
@Component
public class MailSenderUtil {
	
	private static Logger log = LoggerFactory.getLogger(MailSenderUtil.class);
	@Value("${mail.smtp.username}")
	private String username;		//发送方邮箱
	@Value("${mail.smtp.password}")
	private String password;		//发送方邮箱授权码
	@Value("${mail.smtp.smtpServer}")
	private String smtpServer;		//邮件协议
	@Value("${mail.smtp.port}")
	private String port;	//邮件端口
	@Value("${mail.smtp.fromMailAddress}")
	private String fromMailAddress;	//发送方邮箱
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getSmtpServer() {
		return smtpServer;
	}
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

	public String getFromMailAddress() {
		return fromMailAddress;
	}
	public void setFromMailAddress(String fromMailAddress) {
		this.fromMailAddress = fromMailAddress;
	}
//	 private static String toMailAddress = "changying@jusfoun.com"; //发送目标邮箱 
		 
	 public void mailSimple(String toMailAddress,String subject,String content) {
	        Properties props = new Properties(); 
	        props.put("mail.smtp.auth", "true"); 
	        props.put("mail.smtp.host", smtpServer);
	        props.put("mail.smtp.port", port); 
	        props.put("mail.transport.portocol", "smtp");
	     
	        try {
	        	 MailSSLSocketFactory sf=new MailSSLSocketFactory();
	 	        sf.setTrustAllHosts(true);
	 	        props.put("mail.smtp.ssl.enable", "true"); 
	 	        props.put("mail.smtp.ssl.socketFactory", sf);
	 	        // 获得邮件会话对象 
//	 	        Session session = Session.getDefaultInstance(props, 
//	 	                new SmtpAuthenticator(username, password)); 
	 	        
	 	       Session session = Session.getInstance(props, new Authenticator(){
	 	          protected PasswordAuthentication getPasswordAuthentication() {
	 	              return new PasswordAuthentication(username, password);
	 	          }});
	 	        /** *************************************************** */ 
	        	   // 创建MIME邮件对象 
		        MimeMessage mimeMessage = new MimeMessage(session); 
		        mimeMessage.setFrom(new InternetAddress(fromMailAddress));// 发件人 
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress( 
				        toMailAddress));
				 mimeMessage.setSubject(subject); 
			        mimeMessage.setSentDate(new Date());// 发送日期 
			        BodyPart mdp = new MimeBodyPart();// 新建一个存放信件内容的BodyPart对象 
			       mdp.setContent(content, "text/html;charset= GB2312");// 设置发送邮件内容为HTML类型,并为中文编码 
			       
			       // mdp.setContent("测试java邮件发送", "text/html;charset=UTF-8");// 设置发送邮件内容为HTML类型,并为中文编码
			        Multipart mm = new MimeMultipart(); 
			        mm.addBodyPart(mdp); 
			        mimeMessage.setContent(mm); 
			        mimeMessage.saveChanges(); 
			        Transport.send(mimeMessage);// 发送邮件 
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 收件人 
	        catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}         
	 }  
    
	//随机生成验证码  
	 public class RandomTools {  
	   
	     /** 生成随机数验证码 */  
	     public String randomCode() {  
	         Integer res = (int)(Math.random()*1000000);  
	         return res+"";  
	     }  
	 }  
	 //加密 解密  
	 public class SecurityUtil {  
	     public String md5(String password) throws NoSuchAlgorithmException {  
	         MessageDigest md = MessageDigest.getInstance("MD5");  
	         md.update(password.getBytes());  
	         return new BigInteger(1,md.digest()).toString(16);  
	     }  
	       
	     public String md5(String username,String password) throws NoSuchAlgorithmException {  
	         MessageDigest md = MessageDigest.getInstance("MD5");  
	         md.update(username.getBytes());  
	         md.update(password.getBytes());  
	         return new BigInteger(1,md.digest()).toString(16);  
	     }  
	 }
	 
}

/**
 * Smtp认证
 */ 
class SmtpAuthenticator extends Authenticator { 
    String username = null; 
    String password = null; 
 
    // SMTP身份验证 
    public SmtpAuthenticator(String username, String password) { 
        this.username = username; 
        this.password = password; 
    } 
 
    public PasswordAuthentication getPasswordAuthentication() { 
        return new PasswordAuthentication(this.username, this.password); 
    } 
 
} 
