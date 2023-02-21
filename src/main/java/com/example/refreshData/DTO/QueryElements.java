package com.example.refreshData.DTO;

import com.example.refreshData.Config.Attribute;
import com.example.refreshData.Config.Conditions;
import com.example.refreshData.Config.View;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QueryElements {
    private View view;
    private List<Attribute> attributes;
    private Conditions condition;
}
