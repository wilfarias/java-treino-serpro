package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {

	private final static int MAX_FILES_PER_FOLDER = 1000;

	private Path diretorio;

	private Path arquivo;

	private CaminhoArquivo(Path diretorio, Path arquivo) {
		super();
		this.diretorio = diretorio;
		this.arquivo = arquivo;
	}

	public Path getDiretorio() {
		return diretorio;
	}

	public Path getArquivo() {
		return arquivo;
	}

	public static CaminhoArquivo getInstance(Integer fileId) {
		String path = "/tmp/";
		String file = null;
		int folder = 1;
		boolean folderLimitNotAchieved = true;

		while (folderLimitNotAchieved) {
			if (fileId <= (folder * MAX_FILES_PER_FOLDER)) {
				path += folder + "/";
				file = path + fileId;
				folderLimitNotAchieved = false;
			}
			folder++;
		}
		return new CaminhoArquivo(Paths.get(path), Paths.get(file));

	}

}