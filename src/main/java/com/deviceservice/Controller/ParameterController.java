package com.deviceservice.Controller;

import com.deviceservice.Model.Parameter;
import com.deviceservice.Repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/parameters/")
public class ParameterController {

    @Autowired
    private ParameterRepository parameterRepository;



//    @RequestMapping("/device-{id}")
//    public ModelAndView getParameters(@PathVariable("id") Long deviceId) {
//        return getParameterListView(deviceId);
//    }


    @PostMapping("/add")
    public ModelAndView saveParameter(@ModelAttribute("parameter") Parameter parameter)
    {
        parameterRepository.save(parameter);
        return new ModelAndView("redirect:/" );
    }

//    private ModelAndView getParameterListView(Long deviceId) {
//        return new ModelAndView("param", "parameters",
//                parameterRepository.findParametersByDeviceId(deviceId));
//    }
}