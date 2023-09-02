package com.springproject.userrepoinfo.service;

import com.springproject.userrepoinfo.exception.UserNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GithubService {

    private static final String GITHUB_API_BASE_URL = "https://api.github.com/users/%s/repos";

    public List<Map<String, Object>> fetchRepositories(String userLogin) {
        ResponseEntity<List<Map<String, Object>>> responseEntity = callGithubApi(userLogin);
        validateResponse(responseEntity, userLogin);
        return transformResponse(responseEntity.getBody());
    }

    private ResponseEntity<List<Map<String, Object>>> callGithubApi(String userLogin) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = String.format(GITHUB_API_BASE_URL, userLogin);
        return restTemplate.exchange(apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Map<String, Object>>>() {});
    }

    private void validateResponse(ResponseEntity<List<Map<String, Object>>> responseEntity, String userLogin) {
        if (responseEntity.getStatusCode() != HttpStatus.OK || responseEntity.getBody() == null || responseEntity.getBody().isEmpty()) {
            throw new UserNotFoundException("User " + userLogin + " not found.");
        }
    }

    private List<Map<String, Object>> transformResponse(List<Map<String, Object>> originalResponse) {
        return originalResponse.stream()
                .filter(repo -> !Boolean.TRUE.equals(repo.get("fork")))
                .map(this::transformRepo)
                .collect(Collectors.toList());
    }

    private Map<String, Object> transformRepo(Map<String, Object> repo) {
        String repoName = (String) repo.get("name");
        Map<String, Object> ownerInfo = (Map<String, Object>) repo.get("owner");
        String ownerLogin = (String) ownerInfo.get("login");

        repo.clear();
        repo.put("Repository Name", repoName);
        repo.put("Owner Login", ownerLogin);
        return repo;
    }
}

