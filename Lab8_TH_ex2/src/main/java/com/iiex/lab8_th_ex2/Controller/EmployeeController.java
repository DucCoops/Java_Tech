package com.iiex.lab8_th_ex2.Controller;


import com.iiex.lab8_th_ex2.Model.Employee;
import com.iiex.lab8_th_ex2.Service.IEmployService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private IEmployService employService;
    @GetMapping("add")
    public String showAdd(Model model){
        model.addAttribute("employee",new Employee());
        return "add";
    }
    @PostMapping("add")
    public String Add(Model model, @ModelAttribute("employee")Employee employee){
        employService.add(employee);
        return "redirect:/employees";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model,@PathVariable Integer id){
        model.addAttribute("employee",employService.getOne(id));
        return "edit";
    }
    @PostMapping("edit/{id}")
    public String edit(@PathVariable Integer id,@Valid Employee employee){
        Employee employee1 = employService.getOne(id);
        employee1.setName(employee.getName());
        employee1.setEmail(employee.getEmail());
        employee1.setPhone(employee.getPhone());
        employee1.setAddress(employee.getAddress());
        employService.add(employee1);

        return "redirect:/employees";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
        employService.delete(id);
        return "redirect:/employees";
    }
}
