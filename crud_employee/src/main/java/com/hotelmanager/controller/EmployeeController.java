package com.hotelmanager.controller;

import com.hotelmanager.dto.EmployeeDTO;
import com.hotelmanager.entity.EmployeeEntity;
import com.hotelmanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public ModelAndView listEmployee(Pageable pageable) {
        Page<EmployeeEntity> employeeEntities = employeeService.findAllByDeletedIsFalse(pageable);
        ModelAndView modelAndView = new ModelAndView("employee/list");
        modelAndView.addObject("employees",employeeEntities);
        return modelAndView;
    }

    @GetMapping("/searchName")
    public ModelAndView searchNameEmployee(@ModelAttribute("name") Optional<String> name, Pageable pageable){
        Page<EmployeeEntity> employeeEntities = employeeService.findAllByDeletedIsFalseAndNameContaining(name.get(),pageable);
        ModelAndView modelAndView = new ModelAndView("employee/list","employees",employeeEntities);
        if(employeeEntities.getTotalElements() == 0){
            modelAndView.addObject("message","No result");
            return modelAndView;
        }else {
            modelAndView.addObject("name",name.get());
            return modelAndView;
        }
    }

    @GetMapping("/create")
    public ModelAndView showCreateEmployeeForm(){
        ModelAndView modelAndView = new ModelAndView("employee/create");
        modelAndView.addObject("employees",new EmployeeDTO());
        return modelAndView;
    }

    @PostMapping("/create")
    public String saveEmployee(@Valid @ModelAttribute("employees") EmployeeDTO employeeDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "/employee/create";
        }
        else {
            employeeService.create(employeeDTO);
            redirectAttributes.addFlashAttribute("message","New Employee created successfully");
            return "redirect:/employees/list";
        }
    }
    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteEmployeeForm(@PathVariable String id){
        EmployeeDTO employeeDTO = employeeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("employee/delete");
        modelAndView.addObject("employees", employeeDTO);
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteEmployee(@ModelAttribute("employees") EmployeeDTO employeeDTO, RedirectAttributes redirectAttributes){
        employeeService.delete(employeeDTO.getId());
        redirectAttributes.addFlashAttribute("message","Employee deleted successfully");
        return "redirect:/employees/list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditEmployeeForm(@PathVariable String id){
        EmployeeDTO employeeDTO = employeeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("employee/edit");
        modelAndView.addObject("employees", employeeDTO);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editEmployee(@ModelAttribute("employees") EmployeeDTO employeeDTO, RedirectAttributes redirectAttributes, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/employee/edit";
        }else {
            employeeService.update(employeeDTO);
            redirectAttributes.addFlashAttribute("message","Employee updated successfully");
            return "redirect:/employees/list";
        }
    }

    @GetMapping("/view/{id}")
    public ModelAndView showViewEmployeeForm(@PathVariable String id){
        EmployeeDTO employeeDTO = employeeService.findById(id);
        ModelAndView modelAndView = new ModelAndView("employee/view");
        modelAndView.addObject("employees",employeeDTO);
        return modelAndView;
    }
}
