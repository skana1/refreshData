package com.example.refreshData.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EntityDTO {

    private String name;
    private List<String> attributeTitles;
    private List<List<Object>> attributeValues;

}
