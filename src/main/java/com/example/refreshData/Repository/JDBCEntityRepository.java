package com.example.refreshData.Repository;

import com.example.refreshData.DTO.EntityDTO;
import com.example.refreshData.DTO.Query;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JDBCEntityRepository {

    private static final String URL = "jdbc:mysql://localhost:3307/natural_demo";
    private static final String USER = "root";
    private static final String PASS = "root";

    public EntityDTO getEntity(Query query){

        String queryString = query.getQueryString();
        EntityDTO entity = new EntityDTO();
        entity.setName(query.getView().getName());
        entity.setAttributeTitles(query.getColumnNamesAsList());
        List<List<Object>> entities = new ArrayList<>();


        try(Connection connection = DriverManager.getConnection(URL,USER,PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryString)) {
            
            assert resultSet != null;
            ResultSetMetaData metaData = resultSet.getMetaData();
            entity.setName(metaData.getTableName(1));


            while(resultSet.next()) {
                List<Object> attributeValues = new ArrayList<>();
                
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                   attributeValues.add(resultSet.getObject(i));
                }
                entities.add(attributeValues);

            }
            entity.setAttributeValues(entities);

            return entity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }

