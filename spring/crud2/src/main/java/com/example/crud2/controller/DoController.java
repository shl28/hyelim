package com.example.crud2.controller;

import com.example.crud2.dto.DoDto;
import com.example.crud2.entity.DoIt;
import com.example.crud2.service.DoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
//@RequiredArgsConstructor

public class DoController {

//    DI : 의존성 주입
//    @Autowired
//    private DoService doService;

    // final : 불변성 보장, 테스트 쉬움, 강제 주입-반드시 사용
    private final DoService doService;

    public DoController(DoService doService) {
        this.doService = doService;
    }

    @GetMapping("/mains/add")
    public String addForm(Model model) {
        model.addAttribute("doDto", new DoDto());
        return "mains/add";
    }

    @PostMapping("/mains/create")
    public String create(DoDto dto) {
        DoIt saved = doService.create(dto);
        return "redirect:/list/" + saved.getNum();
    }

    // 전체 조회 : List 받으려면 repository에 override 해야함
    @GetMapping("/list")
    public String list(Model model) {
        List<DoIt> doList = doService.findAll();
        model.addAttribute("DoList", doList);
        return "mains/doList";
    }

    // 단건조회
    @GetMapping("/list/{num}")
    public String detail(@PathVariable("num") Long num, Model model) {
        // 방법 1
        //        Optional<DoIt> result = doService.findById(num);
//        if (result.isPresent()) {
//            model.addAttribute("detail", result.get());
//            return "mains/detail";
//        } else {
//            return "redirect:/list";
//        }

        // 방법 2
//        DoIt doIt = doService.findById(num)
//                .orElseThrow(() -> new IllegalArgumentException("해당 데이터 없음"));
//        model.addAttribute("detail", doIt);
//        return "mains/detail";

        // 방법 3
        return doService.findById(num)
                .map(doIt -> {
                    model.addAttribute("detail", doIt);
                    return "mains/detail";
                }).orElse("redirect:/list");
    }

    // 수정폼
    @GetMapping("/list/{num}/edit")
    public String updateForm(@PathVariable("num") Long num, Model model) {
//        Optional<DoIt> result = doService.findById(num);
//        if (result.isPresent()) {
//            DoIt toDo = result.get();
//            model.addAttribute("editDto",
//                    new DoDto(toDo.getNum(), toDo.getTitle(), toDo.getContent()));
//            return "mains/edit";
//        } else {
//            return "redirect:/list";
//        }

        // 숙제 : map(람다) 방식(방법3)으로 바꿔서 해보기
        return doService.findById(num)
                .map(toDo -> {
                    model.addAttribute("editDto", toDo);
                    return "mains/edit";
                }).orElse("redirect:/edit");
    }

    // 수정 저장
    @PostMapping("/mains/update")
    public String update(DoDto dto) {
//        Optional<DoIt> result = doService.update(dto);
//        if (result.isPresent()) {
//            DoIt saved = result.get();
//            return "redirect:/list/" + saved.getNum();
//        } else {
//            return "redirect:/list";
//        }

        // 숙제 : map(람다) 방식(방법3)으로 바꿔서 해보기
        return doService.update(dto)
                .map(saved -> "redirect:/list/" + saved.getNum())
                .orElse("redirect:/list");
    }

    @GetMapping("/list/{num}/delete")
    public String delete(@PathVariable("num") Long num, RedirectAttributes rttr){
        if (doService.delete(num)){  // 삭제 성공 => true
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
            // addFlashAttribute: 특징 - redirect 시에도 데이터 유지, 한번만 사용가능(휘발성)
        }
        return "redirect:/list";
    }
}
