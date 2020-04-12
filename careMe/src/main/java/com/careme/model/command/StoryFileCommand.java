package com.careme.model.command;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class StoryFileCommand {
	
	public void upload(MultipartFile file) {
		String name = file.getOriginalFilename();
		int index = name.lastIndexOf(".");
		String ext = name.substring(index);
		String n_name = System.currentTimeMillis() + "_" + new Random().nextInt(50) + "." + ext;
		File n_file = new File("/Users/bagdajeong/git/project/projectCareMe//" + n_name);
		try {
			file.transferTo(n_file);
		} catch(IllegalStateException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(n_file.getPath());
	}
	
}
