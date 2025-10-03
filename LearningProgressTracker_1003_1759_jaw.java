// 代码生成时间: 2025-10-03 17:59:50
package com.example.learningprogresstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LearningProgressTracker {
    public static void main(String[] args) {
        SpringApplication.run(LearningProgressTracker.class, args);
    }
}

/*
 * LearningProgressService.java
 *
 * Service class for learning progress management.
 */

package com.example.learningprogresstracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LearningProgressService {
    private static final Logger logger = LoggerFactory.getLogger(LearningProgressService.class);

    // Simulated learning progress data
    private int totalLessons = 10;
    private int completedLessons = 0;

    public int getTotalLessons() {
        return totalLessons;
    }

    public void setTotalLessons(int totalLessons) {
        this.totalLessons = totalLessons;
    }

    public int getCompletedLessons() {
        return completedLessons;
    }

    public void setCompletedLessons(int completedLessons) {
        this.completedLessons = completedLessons;
    }

    public double calculateProgress() {
        if (totalLessons == 0) {
            throw new IllegalArgumentException("Total lessons cannot be zero.");
        }
        return (double) completedLessons / totalLessons * 100;
    }
}

/*
 * LearningProgressController.java
 *
 * REST controller for learning progress tracking.
 */

package com.example.learningprogresstracker.controller;

import com.example.learningprogresstracker.service.LearningProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LearningProgressController {
    @Autowired
    private LearningProgressService learningProgressService;

    @GetMapping("/progress")
    public String calculateProgress(@RequestParam(name = "completed", defaultValue = "0") int completed) {
        try {
            learningProgressService.setCompletedLessons(completed);
            double progress = learningProgressService.calculateProgress();
            return "Learning Progress: " + progress + "%";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}
