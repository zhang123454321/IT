
package com.example.blog.controller;

import com.example.blog.bean.Comment;
import com.example.blog.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public String addComment(@RequestBody Comment comment) {
        if (commentService.addComment(comment)) {
            return "评论添加成功";
        } else {
            return "评论添加失败";
        }
    }
}
