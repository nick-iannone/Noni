package regen.view;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	
	private Clip clip;
	
	public SoundEffect() {
		
	}
		
	public void setFile(String filePath) {

		try {
			File file = new File(filePath);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);				
			clip = AudioSystem.getClip();
			clip.open(sound);
		} catch (Exception e) {
				e.printStackTrace();
		}
	}
	
	public void play(String fileName) {
		
		String filePath = "/Users/niannone/Development/REGen/java/REGen Sounds/" + fileName + ".wav";
		setFile(filePath);
		clip.start();
	}
}
