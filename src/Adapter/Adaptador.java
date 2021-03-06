package Adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import Util.Util;
import DAO.*;

public class Adaptador {
	private IDAO dao;
	private static byte[] bFile = null;

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
		else {
			retorno = dao.inserir(resolucao, dados);
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

		if (Adaptador.bFile == null) {
			String pathVideo = new Util().getValueByName("files", resolucao,
					"path");

			FileInputStream fileInputStream = null;

			File file = new File(pathVideo);

			Adaptador.bFile = new byte[(int) file.length()];

			try {
				fileInputStream = new FileInputStream(file);
				fileInputStream.read(Adaptador.bFile);
				fileInputStream.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return dao.adicionar(resolucao, bFile);
	}
}
