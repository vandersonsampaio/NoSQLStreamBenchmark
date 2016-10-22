package Adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import Util.Util;
import DAO.*;

public class Adaptador {
	private IDAO dao;

	public Adaptador(String fonteDados) {
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
		byte[] retorno = dao.obter(resolucao);
		dao.close();
		return retorno;
	}

	public long inserirFilme(String resolucao, byte[] dados) {
		dao.remover(resolucao);

		long retorno;
		
		if (dados == null || dados.length == 0)
			retorno = inserirVideoPadrao(resolucao);
		else{
			long tempoIni = System.currentTimeMillis();
			dao.inserir(resolucao, dados);
			long tempoFim = System.currentTimeMillis();
			retorno = tempoFim - tempoIni;
		}

		dao.close();

		return retorno;
	}

	public boolean limparBase(String resolucao) {
		dao.limpar();
		dao.close();
		return true;
	}

	private long inserirVideoPadrao(String resolucao) {
		String pathVideo = new Util()
				.getValueByName("files", resolucao, "path");

		FileInputStream fileInputStream = null;

		File file = new File(pathVideo);

		byte[] bFile;

		while (true) {
			if (Runtime.getRuntime().freeMemory() > file.length()) {
				bFile = new byte[(int) file.length()];
				break;
			} else if (Runtime.getRuntime().freeMemory() != 0) {
				bFile = new byte[(int) ((int) Runtime.getRuntime().freeMemory() * 0.67)];
				break;
			}else{
				System.out.println("Sem memória livre!");
			}
		}

		long tempoIni = System.currentTimeMillis();
		
		try {
			fileInputStream = new FileInputStream(file);

			while (fileInputStream.read(bFile) != -1) {
				dao.adicionar(resolucao, bFile);
			}

			fileInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long tempoFim = System.currentTimeMillis();
		return tempoFim - tempoIni;
	}
}
