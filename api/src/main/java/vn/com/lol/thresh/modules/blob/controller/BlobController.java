package vn.com.lol.thresh.modules.blob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.lol.common.controller.BaseController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BlobController extends BaseController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}
