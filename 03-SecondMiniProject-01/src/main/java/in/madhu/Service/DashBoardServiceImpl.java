package in.madhu.Service;

import java.util.List;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.madhu.Bindings.Quote;
import in.madhu.Bindings.QuoteApiResponse;
@Service
public class DashBoardServiceImpl implements DashBoardService{

	private List<Quote> quotes=null;
	@Override
	public String getQuote() {
		String quoteUrl="https://type.fit/api/quotes";
		Quote[] response=null;
		
		RestTemplate rt=new RestTemplate();
	
		if(quotes==null) {
			
	
		ResponseEntity<String> forEntity=
		rt.getForEntity(quoteUrl, String.class);
		
		String body=forEntity.getBody();
		ObjectMapper mapper=new ObjectMapper();
		
		try {
			response=mapper.readValue(body,Quote[].class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	 
		
		}
		Random r=new Random();
		int nextInt=r.nextInt(response.length-1);
		
		return response[nextInt].getText();
	}

}
