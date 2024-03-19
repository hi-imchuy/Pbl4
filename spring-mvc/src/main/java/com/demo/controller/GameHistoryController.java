package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.GameHistory;
import com.demo.service.GameHistoryService;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/history")
public class GameHistoryController {

    
    private GameHistoryService gameHistoryService = new GameHistoryService();

    @GetMapping
    public List<GameHistory> getUserGameHistories(HttpServletRequest request) {
        if (request == null) {
            throw new RuntimeException("Request is null");
        }
        
        Integer userID = (Integer) request.getSession().getAttribute("userID");
        System.err.println(userID);
        if (userID == null) {
            // Trả về danh sách rỗng hoặc xử lý khác
            return new ArrayList<>();
        }

        // Xác định xem gameHistoryService có bị null không
        
//        if (gameHistoryService == null) {
//            throw new RuntimeException("GameHistoryService is null");
//        }

        return gameHistoryService.getGameHistoriesByUserId(userID);
    }

}
