package com.example.application.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.contracts.application.services.EMailService;

@Service
public class MessagingService {
	private final EMailService srv;
	
	public MessagingService(EMailService srv) {
		this.srv = srv;
	}
	
	public void sendWelcomeEmail(String to, String name) {
		String subject = "Bienvenido a nuestra aplicación";
		String body = "Hola " + name + ",\n\n¡Gracias por unirte a nosotros!";
		srv.sendEmail(to, subject, body);
	}
	
	@Async
	public void sendWelcomeEmailAsync(String to, String name) {
		String subject = "Bienvenido a nuestra aplicación";
		String body = "Hola " + name + ",\n\n¡Gracias por unirte a nosotros!";
		srv.sendEmailAsync(to, subject, body);
	}

	public void sendEmail(String to, String subject, String body) {
        srv.sendEmail(to, subject, body);
	}

	@Async
	public void sendEmailAsync(String to, String subject, String body) {
        srv.sendEmailAsync(to, subject, body);
	}

	public void sendEmail(String to, String subject, String body, String attachmentPath) {
        srv.sendEmailWithAttachment(to, subject, body, attachmentPath);
	}

	@Async
	public void sendEmailAsync(String to, String subject, String body, String attachmentPath) {
        srv.sendEmailWithAttachmentAsync(to, subject, body, attachmentPath);
	}

	public void sendMimeEmail(String to, String subject, String htmlBody, boolean isHtml) {
        srv.sendMimeEmail(to, subject, htmlBody, isHtml);
	}

	@Async
	public void sendMimeEmailAsync(String to, String subject, String htmlBody, boolean isHtml) {
        srv.sendMimeEmailAsync(to, subject, htmlBody, isHtml);
	}

}
