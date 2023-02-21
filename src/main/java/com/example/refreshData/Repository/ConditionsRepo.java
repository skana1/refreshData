package com.example.refreshData.Repository;

import com.example.refreshData.Config.Conditions;
import com.example.refreshData.Config.View;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionsRepo extends JpaRepository<Conditions, Long> {
    Conditions findConditionsByView(View view);
}
