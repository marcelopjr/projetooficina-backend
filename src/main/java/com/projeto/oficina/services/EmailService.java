package com.projeto.oficina.services;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.projeto.oficina.entities.Usuarios;
import com.projeto.oficina.exception.GlobalException;


@Component
public class EmailService {
	@Autowired
	private JavaMailSender emailSender;

	@Value("${spring.mail.username}")
	private String emailRemetente;
	
	@Value("${spring.mail.host}")
	private String emailServerHost;
	
	@Value("${spring.mail.port}")
	private Integer emailServerPort;
	
	@Value("${spring.mail.username}")
	private String emailServerUserName;
	
	@Value("${spring.mail.password}")
	private String emailServerPassword;
	
	@Value("${spring.mail.protocol}")
	private String emailServerProtocol;
	
	@Value("${url.front.end}")
	private String urlfront;
	
	public JavaMailSender javaMailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		Properties prop = new Properties();

		mailSender.setHost(emailServerHost);
		mailSender.setPort(emailServerPort);
		mailSender.setUsername(emailServerUserName);
		mailSender.setPassword(emailServerPassword);
		mailSender.setProtocol("smtp");
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", true);
		mailSender.setJavaMailProperties(prop);

		return mailSender;

	}

	public void emailCadastro(Usuarios usuario) throws MessagingException, GlobalException {

		this.emailSender = javaMailSender();

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		

		try {
			helper.setFrom(emailRemetente);
			helper.setTo(usuario.getEmail());
			helper.setSubject("Oficina JR - Confirme seu e-mail");
			
			SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
			DecimalFormat dfMoeda = new DecimalFormat("R$ ,##0.00");

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<html>\r\n");
			sBuilder.append("	<body>\r\n");
			sBuilder.append("		<div align=\"center\">\r\n");
			sBuilder.append("			Oficina SPEEDCAR CONTATO\r\n");
			sBuilder.append("		</div>\r\n");
			sBuilder.append("		<br/>\r\n");
			sBuilder.append("		<center>\r\n");
			
			
			sBuilder.append("<p>");
			sBuilder.append("Para ativar seu email,");
			sBuilder.append("<a href='"+urlfront+"/ativaremail"+"?k="+usuario.getChaveAtivarEmail()+"&u="+ usuario.getEmail() +"' >");
			
			sBuilder.append("clique aqui!");
			sBuilder.append("</a>");
			sBuilder.append("</p>");
			
			
			sBuilder.append("		</center>\r\n");
			sBuilder.append("	</body>\r\n");
			sBuilder.append("</html>");

			helper.setText(sBuilder.toString(), true);

			emailSender.send(message);
		

		} catch (Exception e) {
			throw new GlobalException("Houve erro ao enviar o email de cadastro: ");
		}

	}
	
	public void emailNovaSenha(Usuarios usuario) throws MessagingException, GlobalException {

		this.emailSender = javaMailSender();

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		

		try {
			helper.setFrom(emailRemetente);
			helper.setTo(usuario.getEmail());
			helper.setSubject("Oficina JR - Recuperação de senha");
			
			SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
			DecimalFormat dfMoeda = new DecimalFormat("R$ ,##0.00");

			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("<html>\r\n");
			sBuilder.append("	<body>\r\n");
			sBuilder.append("		<div align=\"center\">\r\n");
			sBuilder.append("			Oficina SPEEDCAR CONTATO\r\n");
			sBuilder.append("		</div>\r\n");
			sBuilder.append("		<br/>\r\n");
			sBuilder.append("		<center>\r\n");
			
			
			sBuilder.append("<p>");
			sBuilder.append("Para alterar sua senha, ");
			sBuilder.append("<a href='"+urlfront+"/alterarsenha"+"?k="+usuario.getChaveRecuperarSenha()+"&u="+ usuario.getEmail() +"' >");
			
			sBuilder.append("clique aqui!");
			sBuilder.append("</a>");
			sBuilder.append("</p>");
			
			
			sBuilder.append("		</center>\r\n");
			sBuilder.append("	</body>\r\n");
			sBuilder.append("</html>");

			helper.setText(sBuilder.toString(), true);

			emailSender.send(message);
		

		} catch (Exception e) {
			throw new GlobalException("Houve erro ao enviar o email de cadastro: ");
		}

	}
	

}
