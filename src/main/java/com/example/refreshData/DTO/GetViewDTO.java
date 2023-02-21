package com.example.refreshData.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetViewDTO {
    public String name;
    public String limit;
    public String offset;
    public String requestConditions;
}
