package com.deviceservice.Controller;

import com.deviceservice.Model.Device;
import com.deviceservice.Repository.DeviceRepository;

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

    @RequestMapping("/")
    public ModelAndView getDeviceList() {
        ModelAndView mav = new ModelAndView("device-list");
        mav.addObject("list", deviceRepository.findAll());
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addDeviceForm() {
        ModelAndView mav = new ModelAndView("device-form");
        mav.addObject("device", new Device());
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView saveDevice(@Valid @ModelAttribute("device") Device device,
                                 BindingResult bindingResult) {
        ModelAndView mav = new ModelAndView();
//        mav.addObject("categories", categoryService.listAll());

        if (bindingResult.hasErrors()) {
            mav.addObject("device", device);
            mav.setViewName("device-form");
        } else {
            deviceRepository.save(device);
            mav.setViewName("redirect:/");
        }
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editDevice( @PathVariable("id") Long deviceId) {
        ModelAndView mav = new ModelAndView("device-form");
        mav.addObject("device", deviceRepository.findById(deviceId));
        return mav;
    }
}
