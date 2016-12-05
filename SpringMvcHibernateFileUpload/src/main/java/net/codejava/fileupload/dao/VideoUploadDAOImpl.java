package net.codejava.fileupload.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.codejava.fileupload.model.UploadVideo;

@Repository
public class VideoUploadDAOImpl implements VideoUploadDAO {
	@Autowired
	private SessionFactory sessionfactory;
	
	
	public VideoUploadDAOImpl() {
	}
	
	public VideoUploadDAOImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		this.sessionfactory = sessionFactory;
	}

	@Override
	@Transactional
	public void save(UploadVideo uploadVideo) {
		// TODO Auto-generated method stub
		sessionfactory.getCurrentSession().save(uploadVideo);

	}

}
