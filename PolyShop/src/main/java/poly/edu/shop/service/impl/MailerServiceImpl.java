package poly.edu.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import poly.edu.shop.model.MailInfo;
import poly.edu.shop.service.MailerService;

@Service
public class MailerServiceImpl implements MailerService{
	List<MailInfo> list = new ArrayList<>();
	
	@Autowired
	JavaMailSender sender;
	
	@Override
	public void send(MailInfo mailInfo) throws MessagingException{
		//tao message
		MimeMessage message = sender.createMimeMessage();
		
		//Su dung Helper de thiet lap cac thong tin can thiet cho message
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom(mailInfo.getFrom());
		helper.setTo(mailInfo.getTo());
		helper.setSubject(mailInfo.getSubject());
		helper.setText(mailInfo.getBody(), true);
		helper.setReplyTo(mailInfo.getFrom());
		
		sender.send(message);
	}
	
	@Override
	public void queue(MailInfo mailInfo) {
		list.add(mailInfo);
	}
	
	@Override
	public void queue(String from, String to, String subject, String body) {
		queue(new MailInfo(from, to, subject, body));
	}
	
	@Scheduled(fixedDelay = 5000)
	public void run() {
		while (!list.isEmpty()) {
			MailInfo mailInfo = list.remove(0);
			try {
				this.send(mailInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
