package com.careme.controller;

import java.io.File;

import java.io.IOException;
import java.util.List;

import javax.activation.CommandMap;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.careme.model.dto.StoryBoardDto;
import com.careme.model.dto.StoryFileDto;
import com.careme.service.StoryService;
import com.google.gson.Gson;
import com.lowagie.text.pdf.codec.Base64.InputStream;

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
	
	@RequestMapping(value = "/view/story/storyForm")
	public String insertPage() {
		return "/story/storyForm";
	}
	
	public ModelAndView articleInsert() {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
	
	@RequestMapping(value = "/storyForm", method = RequestMethod.POST)
	public String upload(MultipartFile[] uploadFiles, Model model) {
		String result = "";
		for(MultipartFile f : uploadFiles) {
			result += saveFile(f);
		}
		model.addAttribute("result", result);
		return "fileupload";
	}
	
	private static final String UPLOAD_PATH = "d://ParkDajeong/PORTFOLIO/upload//";
	
	private String saveFile(MultipartFile file) {
		String saveName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		File saveFile = new File(UPLOAD_PATH, saveName);
		try {
			file.transferTo(saveFile);
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		return saveName;
	}
	
	/*
	 * @RequestMapping(value = "/view/story/storyDetail", method =
	 * RequestMethod.POST) public String articleInsert(StoryFileDto dto,
	 * MultipartFile report) { StoryFileController sfc = new StoryFileController();
	 * sfc.upload(report); return "/story/storyDetail"; }
	 */
	
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
