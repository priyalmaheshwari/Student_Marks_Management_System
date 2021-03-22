package com.maheshwari.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import com.maheshwari.demo.model.Student;
import com.maheshwari.demo.model.StudentPassword;
import com.maheshwari.demo.model.TeacherPassword;
import com.maheshwari.demo.repository.StudentPasswordRepo;
import com.maheshwari.demo.repository.StudentRepo;
import com.maheshwari.demo.repository.TeacherPasswordRepo;

@Controller
public class StudentController {

	@Autowired
	StudentRepo repo;
	@Autowired
	StudentPasswordRepo sprepo;
	@Autowired
	TeacherPasswordRepo tprepo;
	@RequestMapping("/home")
	public String home()
	{
//		List<Student> stu = new ArrayList<>();
//		stu = repo.findAll();
//		model.addAttribute("stu", stu);
		return "home";
		
	}
	@RequestMapping("/studentlogin.html")
	public String studentlogin()
	{
		return "studentlogin";
	}
	
	@RequestMapping("/teacherlogin.html")
	public String teacherlogin()
	{
		return "teacherlogin";
	}
	
	@RequestMapping("/createnewstu.html")
	public String createnew()
	{
		return "createnewstu";
	}
	@RequestMapping("/addstudent")
	public String addstudent(StudentPassword stud)
	{
		//@RequestParam int id,@RequestParam String pass
	//	StudentPassword stud = new StudentPassword(id,pass);
		sprepo.save(stud);
		return "studentlogin";
	}
	@RequestMapping("/checkpassword")
	public String checkpassword(StudentPassword s,Model model)
	{
		int id=s.getId();
		StudentPassword sp = sprepo.findById(id).orElse(null);
		if(sp==null)
			return "error1";
		String correctpass = sp.getPass();
		if(s.getPass().equals(correctpass))
			{
			Student stu = repo.findById(id).orElse(null);
			model.addAttribute("stu", stu);
			return "display";
			}
		return "error";
	}
	
	@RequestMapping("/checkteachpass")
	public String checkteachpass(TeacherPassword s,Model model)
	{
		int id=s.getId();
		TeacherPassword sp = tprepo.findById(id).orElse(null);
//		if(sp==null)
//			return "error2";
		String correctpass = sp.getPass();
		if(s.getPass().equals(correctpass))
			{
			List<Student> stu = new ArrayList<>();
			stu = repo.findAll();
			model.addAttribute("stu", stu);
			return "showlist";
			}
		return "error2";
	}
	
	@RequestMapping("/displayinfo")
	public String displayinfo(@RequestParam int id,Model model)
	{
		
		Student stu = repo.findById(id).orElse(null);
		model.addAttribute("stu", stu);
		return "display";
	}
	
	@RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("update");
        Student std = repo.findById(id).orElse(null);
        mav.addObject("student", std);
        return mav;
        
    }
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable(name="id")int id,Model model)
	{
		repo.deleteById(id);
		List<Student> stu = new ArrayList<>();
		stu = repo.findAll();
		model.addAttribute("stu", stu);
		return "showlist";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student std,Model model) {
        repo.save(std);
        List<Student> stu = new ArrayList<>();
		stu = repo.findAll();
		model.addAttribute("stu", stu);
		return "showlist";
    }
	
	@RequestMapping("/createNew")
	public String createNew(Student s,Model model)
	{
		repo.save(s);
		List<Student> stu = new ArrayList<>();
		stu = repo.findAll();
		model.addAttribute("stu", stu);
		return "showlist";
		
	}
//	@RequestMapping("/showlist")
//	public String showlist(Model model)
//	{
//		List<Student> stu = new ArrayList<>();
//		stu = repo.findAll();
//		model.addAttribute("stu", stu);
//		return "showlist";
//	}
	
}
