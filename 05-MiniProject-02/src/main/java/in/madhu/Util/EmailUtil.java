package in.madhu.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender jms;
	
	public boolean sendEmail(String object,String body,String to) {
		
		boolean issent=false;
		try {
			MimeMessage mm=jms.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mm);
			helper.setTo(to);
			helper.setSubject(object);
			helper.setText(body, true);
			jms.send(mm);
			issent=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return issent;
	}
	

}
