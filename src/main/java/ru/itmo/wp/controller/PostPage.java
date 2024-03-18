package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @AnyRole({Role.Name.ADMIN, Role.Name.WRITER})
    @GetMapping("post/{id}")
    public String getPost(@PathVariable String id,
                          Model model) {
        Post post;
        try {
            post = postService.find(Long.parseLong(id));
        } catch (NumberFormatException e) {
            post = null;
        }
        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        return "PostPage";
    }

    @Guest
    @PostMapping("post/{id}")
    public String writeComment(@PathVariable String id,
                               @Valid @ModelAttribute("comment") Comment comment,
                               BindingResult bindingResult,
                               Model model,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            Post post;
            try {
                post = postService.find(Long.parseLong(id));
            } catch (NumberFormatException e) {
                post = null;
            }
            model.addAttribute("post", post);
            return "PostPage";
        }
        postService.writeComment(getUser(httpSession), postService.find(Long.parseLong(id)), comment);
        putMessage(httpSession, "You published new comment");
        return "redirect:/post/" + id;
    }
}
