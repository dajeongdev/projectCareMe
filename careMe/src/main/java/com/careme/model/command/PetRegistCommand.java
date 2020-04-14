package com.careme.model.command;

import org.springframework.web.multipart.MultipartFile;

public class PetRegistCommand {
	private MultipartFile profileImage;
	private String name;
	private int pet_idx;
	private int species;
	private String neutralized;
}
