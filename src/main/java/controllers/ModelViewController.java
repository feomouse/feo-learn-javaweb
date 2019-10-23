package controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import domin.view.MyViewModel;

@Controller
public class ModelViewController {
    private MyViewModel doaViewModel;
    
    public ModelViewController() {
        this.doaViewModel = new MyViewModel();
    }
    
    @RequestMapping(value="/getModel")
    public String getModelView(Model model) {
        model.addAttribute("myModel", doaViewModel);
        
        return "ShowModel";
    }
    
    @RequestMapping(value="/eidtModel")
    public String updateModelView(Model model) {
        model.addAttribute("model", new MyViewModel());
        return "EditModel";
    }
    
    @RequestMapping(value="/addModel")
    public String addModelView(@Valid @ModelAttribute MyViewModel model, BindingResult bindingResult, Model models) {
        if(bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            models.addAttribute("errorCode", fieldError.getCode());
            models.addAttribute("errorName", fieldError.getObjectName());
            models.addAttribute("errorField", fieldError.getField());
            
            return "ErrorPage";
        }
        
        doaViewModel = model;
        
        return "redirect:/getModel";
    }
}
