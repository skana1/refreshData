package com.example.refreshData.DTO;

public interface QueryServiceInterface {
    EntityDTO getView(String name, String limit, String offset, String[] stringConditions);
    String[] parseConditions(String requestConditions);
}
