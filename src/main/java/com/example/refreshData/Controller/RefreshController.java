package com.example.refreshData.Controller;

import com.example.refreshData.DTO.QueryElements;
import com.example.refreshData.Service.RefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@org.springframework.stereotype.Controller
@RestController
@RequiredArgsConstructor

public class RefreshController {
    private final RefreshService refreshService;

    @GetMapping("/refreshData")
    public Map<String, QueryElements> getRefreshData(){
        return refreshService.refreshData();
    }
}
