package in.madhu.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender js;
	
	
		public boolean sendEmail(String sub,String body,String to) {
			boolean issent=false;
			try {

				MimeMessage mimeMessage = js.createMimeMessage();

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
				helper.setSubject(sub);
				helper.setText(body, true);
				helper.setTo(to);

				js.send(mimeMessage);

				issent = true;

			} catch (Exception e) {
				e.printStackTrace();
			}

			return issent;
			
		}

	}



