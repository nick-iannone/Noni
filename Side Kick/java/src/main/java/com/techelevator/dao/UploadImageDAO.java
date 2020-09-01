package com.techelevator.dao;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

@Service
public class UploadImageDAO {


	
	public void saveFile(File file) {
		
		BufferedImage image = null;
		
			try {
				File imageFile = file;
				image = ImageIO.read(imageFile);
				ImageIO.write(image, "jpg", new File("../../../../../../../vue/src/assets/image.jpg"));
			}
			catch(IOException e) {
				System.out.println(e.getMessage());
			}
	}
		
}
