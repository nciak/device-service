package com.deviceservice.Controller;

import com.deviceservice.Model.Category;
import com.deviceservice.Model.Device;
import com.deviceservice.Model.Parameter;
import com.deviceservice.Repository.CategoryRepository;
import com.deviceservice.Repository.DeviceRepository;

import com.deviceservice.Repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class DeviceController {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ParameterRepository parameterRepository;

    @RequestMapping("/")
    public ModelAndView getDeviceList() {
        ModelAndView mav = new ModelAndView("device-list");
        mav.addObject("list", deviceRepository.findAll());
        mav.addObject("parameter", new Parameter());
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addDeviceForm() {
        ModelAndView mav = new ModelAndView("device-form");
        mav.addObject("device", new Device());
        mav.addObject("categories", categoryRepository.findAll());

        return mav;
    }

    @PostMapping("/add")
    public ModelAndView saveDevice(@Valid @ModelAttribute("device") Device device,
                                 BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("categories", categoryRepository.findAll());

        if (bindingResult.hasErrors()) {
            mav.addObject("device", device);

            mav.setViewName("device-form");
        } else {
            deviceRepository.save(device);
            mav.setViewName("redirect:/show/"+device.getId());
        }


        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editDevice( @PathVariable("id") Long deviceId) {
        ModelAndView mav = new ModelAndView("device-form");
        mav.addObject("device", deviceRepository.findById(deviceId).orElseThrow(()->
                new IllegalArgumentException("Object not exist")));
        mav.addObject("categories", categoryRepository.findAll());
        return mav;
    }

    @GetMapping("/remove/{id}")
    public String deleteDevice(@PathVariable("id") Long deviceId) {
        deviceRepository.deleteById(deviceId);
        return "redirect:/";
    }



    @GetMapping("/show/{id}")
    public ModelAndView showDevice( @PathVariable("id") Long deviceId) {
        ModelAndView mav = new ModelAndView("device-show");
        mav.addObject("device", deviceRepository.findById(deviceId).orElseThrow(()->
                new IllegalArgumentException("Object not exist")));
        mav.addObject("parameter", new Parameter());
        mav.addObject("parameters", parameterRepository.findParametersByDeviceId(deviceId));

        return mav;
    }


    @PostMapping("/show/{id}")
    public ModelAndView saveParameter(@ModelAttribute("parameter") Parameter parameter) {
        ModelAndView mav = new ModelAndView("device-show");
        mav.addObject("device", deviceRepository.findById(parameter.getDevice().getId()).orElseThrow(()->
                new IllegalArgumentException("Object not exist")));
        parameterRepository.save(parameter);
        return new ModelAndView("redirect:/show/" + parameter.getDevice().getId());
    }


    @GetMapping("/addcategory")
    public ModelAndView addCategoryForm() {
        ModelAndView mav = new ModelAndView("category-form");
        mav.addObject("category", new Category());
        mav.addObject("categories", categoryRepository.findAll());
        return mav;
    }

    @PostMapping("/addcategory")
    public ModelAndView saveCategory(@Valid @ModelAttribute("category") Category category,
                                   BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();

        if (bindingResult.hasErrors()) {
            mav.addObject("category", category);
            mav.setViewName("category-form");
        } else {
            categoryRepository.save(category);
            mav.setViewName("redirect:/addcategory");
        }
        return mav;
    }


}

