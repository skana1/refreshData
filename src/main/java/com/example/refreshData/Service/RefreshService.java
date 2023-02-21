package com.example.refreshData.Service;

import com.example.refreshData.Config.Attribute;
import com.example.refreshData.Config.Conditions;
import com.example.refreshData.Config.View;
import com.example.refreshData.DTO.*;
import com.example.refreshData.Repository.AttributeRepo;
import com.example.refreshData.Repository.ConditionsRepo;
import com.example.refreshData.Repository.JDBCEntityRepository;
import com.example.refreshData.Repository.ViewRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@org.springframework.stereotype.Service
public class RefreshService {

    public  static Map<String,QueryElements> init;
    private final ViewRepository viewRepo;
    private final AttributeRepo attributeRepo;
    private final ConditionsRepo conditionsRepo;
    private final JDBCEntityRepository entityRepo;

    public RefreshService(ViewRepository viewRepo, AttributeRepo attributeRepo, ConditionsRepo conditionsRepo, JDBCEntityRepository entityRepo) {
        this.viewRepo = viewRepo;
        this.attributeRepo = attributeRepo;
        this.conditionsRepo = conditionsRepo;
        this.entityRepo = entityRepo;
    }

    public EntityDTO refreshData(){
        List<View> views = viewRepo.findAll();
        views.stream().forEach(view -> {
            List<Attribute> attributes = attributeRepo.findAttributesByView(view);
            Conditions condition = conditionsRepo.findConditionsByView(view);
            init.put(view.getName(), new QueryElements(view, attributes, condition));
        });;
        Query query = new Query(RefreshService.init.get(views).getView(), RefreshService.init.get(views).getAttributes(),RefreshService.init.get(views).getCondition());
        return entityRepo.getEntity(query);
    }
    }
