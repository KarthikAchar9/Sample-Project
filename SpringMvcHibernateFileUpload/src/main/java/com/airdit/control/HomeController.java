package com.airdit.control;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.airdit.dao.FileUploadDAO;
import com.airdit.dao.VideoUploadDAO;
import com.airdit.model.UploadFile;
import com.airdit.model.UploadVideo;

/**
 * Handles requests for the file upload page.
 */
@Transactional
@Controller
public class HomeController {
	@Autowired
	private FileUploadDAO fileUploadDao;
	
	@Autowired
	private VideoUploadDAO videoUploadDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showUploadForm(HttpServletRequest request) {
		return "Upload";
	}

	@RequestMapping(value = "/doUpload", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile[] fileUpload)
			throws Exception {

		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {

				System.out.println("Saving file: " + aFile.getOriginalFilename());

				UploadFile uploadFile = new UploadFile();
				uploadFile.setFileName(aFile.getOriginalFilename());
				uploadFile.setData(aFile.getBytes());
				fileUploadDao.save(uploadFile);
			}
		}

		return "Success";
	}

	/*@RequestMapping(value = "/videoUpload", method = RequestMethod.POST)
	public String handleVideoUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile[] fileUpload)
			throws Exception {
		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile vfile : fileUpload) {

				System.out.println("Saving video: " + vfile.getOriginalFilename());
				UploadVideo video = new UploadVideo();
				video.setFileName(vfile.getOriginalFilename());
				video.setData(vfile.getBytes());
				videoUploadDao.save(video);
			}
		}
		return "success";
	}*/
}
