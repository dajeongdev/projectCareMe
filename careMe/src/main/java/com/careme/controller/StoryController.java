package com.careme.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.dto.StoryBoardDto;
import com.careme.service.StoryService;

@Controller
public class StoryController {
	@Autowired
	StoryService service;

	public void setService(StoryService service) {
		this.service = service;
	}
	
	@RequestMapping("/view/story/storyMain")
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();
		List<StoryBoardDto> list = service.selectAll();
		mav.setViewName("/story/storyMain");
		mav.addObject("storyMain", list);
		return mav;
	}
	
	@RequestMapping("/view/story/storyForm")
	public ModelAndView articleInsert() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/story/storyForm");
		mav.addObject("storyInsert");
		return mav;
	}
	
	/* @RequestMapping(value = "/view/story/storyForm")
	public String uploading(MultipartHttpServletRequest mpht) {
		List<MultipartFile> fileList = mpht.getFiles("files");
		String src = mpht.getParameter("src");
		System.out.println("src value : " + src);
		
		String path = "D://ParkDajeong/PORTFOLIO/file";
		
		for(MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename();
			long fileSize = mf.getSize();
			
			System.out.println("originFileName : " + originFileName);
			System.out.println("fileSize : " + fileSize);
			
			String safeFile = path + System.currentTimeMillis() + originFileName;
			try {
				mf.transferTo(new File(safeFile));
			} catch(IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/";
	}  */
}
