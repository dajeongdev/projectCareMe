package com.careme.model.command;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.careme.model.dto.StoryBoardDto;

@Component
public class StoryFileCommand {
	private static final String filePath = "d://ParkDajeong/PORTFOLIO/upload//";
	
	public List<Map<String, Object>> fileInfo(StoryBoardDto dto, MultipartHttpServletRequest mpRequest) throws Exception {
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originFileName = null;
		String originFileExtens = null;
		String saveFileName = null;
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> listMap = null;
		
		int story_board_idx = dto.getStory_board_idx();
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originFileName = multipartFile.getOriginalFilename();
				originFileExtens = originFileName.substring(originFileName.lastIndexOf("."));
				saveFileName = getRandomString() + originFileExtens;
				
				file = new File(filePath + saveFileName);
				multipartFile.transferTo(file);
				listMap = new HashMap<String, Object>();
				listMap.put("story_board_idx", story_board_idx);
				listMap.put("org_file_name", originFileName);
				listMap.put("save_file_name", saveFileName);
				listMap.put("file_size", multipartFile.getSize());
				list.add(listMap);
			}
		}
		return list;
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
