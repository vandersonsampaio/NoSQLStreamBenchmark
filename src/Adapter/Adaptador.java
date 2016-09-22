package Adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import DAO.*;

public class Adaptador {	
	private String PATH_VIDEO = "D:\\Vanderson\\Desktop\\blog\\1.PNG";
	
	private IDAO dao;
	
	public Adaptador(String fonteDados){
		switch (fonteDados) {
		case "C":
			this.dao = new CassandraDAO();
			break;
			
		case "V":
			this.dao = new VoldemortDAO();
			break;
			
		case "H":
			this.dao = new HBaseDAO();
			break;
			
		case "R":
			this.dao = new RedisDAO();
			break;
			
		case "M":
			this.dao = new MongoDBDAO();
			break;

		}
	}

	public byte[] obterFilme(String resolucao) {
		return dao.obter(resolucao);
	}

	public boolean inserirFilme(String resolucao, byte[] dados) {
		if(dados == null || dados.length == 0)
			return dao.inserir(resolucao, getVideo());
		else
			return dao.inserir(resolucao, dados);
	}

	public boolean limparBase(String resolucao) {
		dao.limpar();
		return true;
	}
	
	private byte[] getVideo() {
		FileInputStream fileInputStream=null;

        File file = new File(PATH_VIDEO);

        byte[] bFile = new byte[(int) file.length()];

	    try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
		    fileInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	    return bFile;
	}
}
