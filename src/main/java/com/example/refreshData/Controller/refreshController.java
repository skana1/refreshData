package com.example.refreshData.Controller;

import com.example.refreshData.DTO.EntityDTO;
import com.example.refreshData.Service.RefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@org.springframework.stereotype.Controller
@RestController
@RequiredArgsConstructor
public class refreshController {

    public RefreshService refreshService;

    @GetMapping("/refreshData")
    public EntityDTO getRefeshData(){
        return refreshService.refreshData();
    }

}
