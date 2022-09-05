package poly.edu.shop.service;

import javax.mail.MessagingException;

import poly.edu.shop.model.MailInfo;

public interface MailerService {

	void queue(MailInfo mailInfo);

	void send(MailInfo mailInfo) throws MessagingException;

	void queue(String from, String to, String subject, String body);

}
