package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/phone")
@Log4j
public class PhoneController {

    @GetMapping("/phoneproduct") // 소문자로 통일
    public String phoneProductPage() {
        log.info("상품 페이지 이동완료...");
        return "phone/phoneproduct"; // 뷰 이름 반환
    }
}
