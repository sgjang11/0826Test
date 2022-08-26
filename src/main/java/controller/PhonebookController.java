package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.PhonebookServiceInter;
import vo.PhonebookVO;

@Controller
public class PhonebookController {

	@Autowired
	PhonebookServiceInter service;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/insert")
	public ModelAndView insert(PhonebookVO pb) {
		ModelAndView mv=new ModelAndView();
		service.insert(pb);
		mv.addObject("list",service.getList());
		mv.setViewName("list");
		return mv;
	}
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mv=new ModelAndView();
		mv.addObject("list",service.getList());
		mv.setViewName("list");
		return mv;
	}

	@RequestMapping("/find")
	public ModelAndView find(String search) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("list",service.find(search));
		mv.setViewName("list");
		return mv;
	}

	@RequestMapping("/findOne")
	public ModelAndView findOne(int idx) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("list",service.findOne(idx));
		mv.setViewName("findOne");
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(PhonebookVO pb) {
		ModelAndView mv=new ModelAndView();
		service.update(pb);
		mv.addObject("list",service.getList());
		mv.setViewName("list");
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(int idx) {
		ModelAndView mv=new ModelAndView();
		service.delete(idx);
		mv.addObject("list",service.getList());
		mv.setViewName("list");
		return mv;
	}
	
}
