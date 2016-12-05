package net.codejava.fileupload.dao;

import net.codejava.fileupload.model.UploadFile;

public interface FileUploadDAO {
	void save(UploadFile uploadFile);
}
