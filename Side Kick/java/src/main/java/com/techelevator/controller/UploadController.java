package com.techelevator.controller;

import java.io.File;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.techelevator.dao.TradeSqlDAO;
import com.techelevator.dao.UploadImageDAO;

@CrossOrigin
@RestController 
public class UploadController {
	
private UploadImageDAO imageDAO;
	
	public UploadController (UploadImageDAO imageDAO) {
		this.imageDAO = imageDAO;
		}

//	@RequestMapping (path = "/upload", method = RequestMethod.POST)
//	public void saveImage(@RequestParam MultipartFile file) {
//		imageDAO.saveFile(file);
//	}
	
}
