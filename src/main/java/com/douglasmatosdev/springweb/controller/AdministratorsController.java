package com.douglasmatosdev.springweb.controller;

import com.douglasmatosdev.springweb.models.Administrator;
import com.douglasmatosdev.springweb.repository.AdministratorsRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AdministratorsController {

    @Autowired
    private AdministratorsRespository administratorsRespository;

    @GetMapping("/administrators")
    public String index(Model model) {
        List<Administrator> administrators = (List<Administrator>) administratorsRespository.findAll();
        model.addAttribute("administrators", administrators);

        return "administrators/index";
    }

    @GetMapping("/administrators/new")
    public String add() {
        return "administrators/new";
    }

    @PostMapping("/administrators/create")
    public String create(Administrator administrator) {
        administratorsRespository.save(administrator);
        return "redirect:/administrators";
    }

    @GetMapping("/administrators/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<Administrator> admin = administratorsRespository.findById(id);
        try {
            model.addAttribute("administrator", admin.get());
        } catch (Exception e) {
            return "redirect:/administrators";
        }
        return "/administrators/edit";
    }

    @PostMapping("/administrators/{id}/update")
    public String update(@PathVariable int id, Administrator administrator) {
//        if (!administratorsRespository.exist(id)) {
        if (!administratorsRespository.existsById(id)) {
            return "redirect:/administrators";
        }

        administratorsRespository.save(administrator);

        return "redirect:/administrators";
    }

    @GetMapping("/administrators/delete/{id}")
    public String delete(@PathVariable int id) {
        administratorsRespository.deleteById(id);
        return "redirect:/administrators";
    }
}
