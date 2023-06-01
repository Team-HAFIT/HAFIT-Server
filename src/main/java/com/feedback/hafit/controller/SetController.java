package com.feedback.hafit.controller;

import com.feedback.hafit.entity.SetExecDTO;
import com.feedback.hafit.repository.SetRepository;
import com.feedback.hafit.service.SetService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

//@RestController
@Controller
@RequestMapping("/set")
//@CrossOrigin(origins= "http://172.18.3.18:3001")
public class SetController {

//    @Autowired
//    private SetService setService;
//
//    @Autowired
//    private SetRepository setRepository;

    @GetMapping("/exec")
//    @CrossOrigin(origins= "http://172.18.3.18:3001")
    public String getHtml() {
        return "exec";
    }

    @GetMapping("/execmodule")
//    @CrossOrigin(origins= "http://172.18.3.18:3001")
    public String Html() {
        return "execmodule";
    }

//    @PostMapping("/ready")
//    @CrossOrigin(origins= "http://172.18.3.18:3001")
//    public boolean set(@RequestBody String jsonString, Model model) {
//        try {
//            JSONObject jsonObject = new JSONObject(jsonString);
//            int repsPerSet = jsonObject.getInt("repsPerSet");
//            int totalSets = jsonObject.getInt("totalSets");
//            int weight = jsonObject.getInt("weight");
//            int restTime = jsonObject.getInt("restTime");
//            model.addAttribute("repsPerSet", repsPerSet);
//            model.addAttribute("totalSets", totalSets);
//            model.addAttribute("weight", weight);
//            model.addAttribute("restTime", restTime);
//            return true;
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
