package DAO;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HBaseDAO implements IDAO {
	
	private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
	private String HOST_CONNECTION = "178.62.254.52";
	private Client cliente;
	
	public HBaseDAO(){
		this.cliente = Client.create(); 
	}
	
	@Override
	public byte[] obter(String resolucao){
		return null;
	}
	
	@Override
	public boolean inserir(String resolucao, byte[] dados){
		try {
			WebResource service = cliente.resource("http://" + HOST_CONNECTION + ":8030/" +
					URLEncoder.encode("test", UTF8_CHARSET.displayName()) + "/" +
					URLEncoder.encode("movie", UTF8_CHARSET.displayName()) + "/" +
					URLEncoder.encode("resolucao", UTF8_CHARSET.displayName()) + "/" +
					URLEncoder.encode("240p", UTF8_CHARSET.displayName()));
			
			ClientResponse response = service.put(ClientResponse.class);
			
			if(response.getStatus() == 200)
				System.out.println("Deu certo!");
			else
				System.out.println("Deu errado: " + response.getStatus());
			
			return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public void limpar(){
		
	}
	
	@Override
	public void close(){
		
	}
	
}

