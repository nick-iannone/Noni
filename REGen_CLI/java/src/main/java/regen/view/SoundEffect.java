package regen.view;

import java.io.File;

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
			System.out.println("ERROR LOCATING FILE");
			e.printStackTrace();	
		}
	}
	
	public void play(String fileName) {
		
		try {
			String filePath = "src\\REGen Sounds\\" + fileName + ".wav";
			setFile(filePath);
			clip.start();
		} catch (Exception e) {
			System.out.println("ERROR PLAYING AUDIO");
			e.printStackTrace();
		}
	}
}
