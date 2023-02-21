package com.example.refreshData.Config;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Conditions {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private View view;

    @ManyToOne
    private Attribute attribute;

    private String operator;

    private String value;

    @Override
    public String toString() {
        return "Conditions{" +
                "id=" + id +
                ", view=" + view +
                ", attribute=" + attribute +
                ", operator='" + operator + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
