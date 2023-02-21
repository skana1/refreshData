package com.example.refreshData.DTO;

import com.example.refreshData.Config.Attribute;
import com.example.refreshData.Config.Conditions;
import com.example.refreshData.Config.View;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Query {

    private View view;
    private List<Attribute> attributes;
    private Conditions condition;
    private String queryString;
    private String[] stringConditions;

    public Query(View view, List<Attribute> attributes, Conditions condition) {
        this.view = view;
        this.attributes = attributes;
        this.condition = condition;
        this.stringConditions = stringConditions;
        System.out.print(getCondition());
        this.queryString = " SELECT " + getColumns() + " FROM " + getTable() + " WHERE " + getCondition();
    }


    private String getTable() {
        return this.view.toString();
    }

    private String getColumns() {
        return this.attributes.stream()
                .map(Attribute::toString)
                .collect(Collectors.joining(", "));
    }

    private String getCondition() {
        if (stringConditions.length == 0) {
            return this.condition.getAttribute().toString() +
                    this.condition.getOperator() + this.condition.getValue();
        }
        return String.join(",", stringConditions);
    }

    public List<String> getColumnNamesAsList() {
        List<String> columnNames = new ArrayList<>();

        for (Attribute a : this.attributes) {
            columnNames.add(a.getName());
        }
        return columnNames;
    }
}
