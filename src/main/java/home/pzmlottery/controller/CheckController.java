package home.pzmlottery.controller;


import home.pzmlottery.service.DrawService;
import home.pzmlottery.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/check")
public class CheckController {

    @Autowired
    private DrawService drawService;

    @PostMapping
    public ModelAndView checkGet(ModelAndView modelAndView){
        drawService.updateDraw();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView checkPost(ModelAndView modelAndView){
        drawService.updateDraw();
        System.out.println("CHECK START");
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
