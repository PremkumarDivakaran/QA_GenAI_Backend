package com.testleaf.controller;

import com.testleaf.llm.LLMTestGenerator;
import com.testleaf.llm.TestCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class TestCaseGenerationController {

    private final LLMTestGenerator llmTestGenerator;
    private final TestCodeGenerator testCodeGenerator;

    /**
     * Generates test code from the provided User Story details and test types.
     * <p>
     * Example usage:
     * POST /api/generateTestCases
     * Body (raw JSON):
     * {
     * "userStoryDetails": "As a user, I want to reset my password so that I can access my account if I forget it.",
     * "acceptanceCriteria": "1. The user should be able to request a password reset by entering their registered email.
     * 2. If the email is valid, the system should send a reset link to the user's email."
     * }
     */
    @PostMapping("/generateTestCases")
    public ResponseEntity<String> generateTestCases(@RequestBody UserStoryRequest request) {
        try {
            String llmResponse = llmTestGenerator.generateTestCasesFromUserStory(
                    request.getUserStoryDetails(),
                    request.getAcceptanceCriteriaDetails()
            );
            String content;
            if (llmResponse.trim().startsWith("{")) {
                JSONObject jsonObj = new JSONObject(llmResponse);
                content = jsonObj.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
            } else {
                content = llmResponse;
            }
            String output = extractTestCases(content);
            return ResponseEntity.ok(output);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error generating test code: " + e.getMessage());
        }
    }

    public static String extractTestCases(String content) {
        String startIndex = "```csv";
        String endIndex = "```";

        // Define regex pattern to extract content between start_index and end_index
        String regex = startIndex + "\\s*(.*?)\\s*" + endIndex;
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            return matcher.group(1).trim(); // Extract matched test cases
        }

        return "No test cases found";
    }


    // DTO for user story details and test types
    public static class UserStoryRequest {
        private String userStoryDetails;
        private String acceptanceCriteriaDetails;

        public String getUserStoryDetails() {
            return userStoryDetails;
        }

        public void setUserStoryDetails(String userStoryDetails) {
            this.userStoryDetails = userStoryDetails;
        }

        public String getAcceptanceCriteriaDetails() {
            return acceptanceCriteriaDetails;
        }

        public void setAcceptanceCriteriaDetails(String acceptanceCriteriaDetails) {
            this.acceptanceCriteriaDetails = acceptanceCriteriaDetails;
        }
    }
}

