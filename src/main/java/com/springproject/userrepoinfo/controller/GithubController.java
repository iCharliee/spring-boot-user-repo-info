package com.springproject.userrepoinfo.controller;

import com.springproject.userrepoinfo.exception.UserNotFoundException;
import com.springproject.userrepoinfo.service.GithubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class GithubController {

    private final GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/github/repos")
    public List<Map<String, Object>> getRepositories(String userLogin) {
        if (userLogin == null || userLogin.trim().isEmpty()) {
            throw new UserNotFoundException("User login is required.");
        }

        return githubService.fetchRepositories(userLogin);
    }
}

