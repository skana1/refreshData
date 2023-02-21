package com.example.refreshData.Repository;

import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class JDBCReference {

    public void jdbc() {

        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/natural_demo", "root", "root");
                Statement stmt =con.createStatement() ){

            String sqlStatement = "CREATE TABLE IF NOT EXISTS employees " +
                    "(emp_id int PRIMARY KEY AUTO_INCREMENT," +
                    "name varchar(30)," +
                    "position varchar(30)," +
                    "salary double)";

            stmt.execute(sqlStatement);

            int updateCount = stmt.getUpdateCount();

            switch (updateCount) {
                case 0:
                    System.out.println("NO rows were affected or db structure update command!");
                case -1:
                    System.out.println("The command was a SELECT query");
                default:
                    System.out.println(updateCount + " rows were affected!");
            }

            // Update Statements================================================

            String insertStatement = "INSERT INTO employees(name, position, salary)" +
                    "VALUES('john', 'developer', 2000)";

            int affectedRows = stmt.executeUpdate(insertStatement);
            // if affectedRows == 0 : statement updated db structure


            // Select Statements===============================================

            String selectStatement = "SELECT * FROM employees";

            try(ResultSet resultSet = stmt.executeQuery(selectStatement)){
                List<Employee> employees = new ArrayList<>();

                while (resultSet.next()) {
                    Employee emp = new Employee();
                    emp.setId(resultSet.getInt("emp_id"));
                    emp.setName(resultSet.getString("name"));
                    emp.setPosition(resultSet.getString("position"));
                    emp.setSalary(resultSet.getDouble("salary"));
                    employees.add(emp);
                }

            }

            //PreparedStatement================================================

            String updatePositionSql = "UPDATE employees SET position=? WHERE emp_id=?";

            try(PreparedStatement pstmt = con.prepareStatement(updatePositionSql)) {
                pstmt.setString(1,"lead developer");
                pstmt.setInt(2,1);
                int rowsAffected = pstmt.executeUpdate();
            }


            //Updatable ResultSet===============================================

            try(Statement updatableStmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)){
                try(ResultSet updatableResultSet = updatableStmt.executeQuery(selectStatement)) {
                    updatableResultSet.moveToInsertRow();
                    updatableResultSet.updateString("name", "mark");
                    updatableResultSet.updateString("position", "analyst");
                    updatableResultSet.updateDouble("salary", 2000);
                    updatableResultSet.insertRow();

                    /* Navigating the ResultSet:
                    * first(), last(), beforeFirst(), beforeLast(), next(), previous(),
                    * getRow()-> rowNum, moveToInsertRow()-> move to empty row, moveToCurrentRow()->move back
                    * absolute(int row)-> move to specified row
                    * relative(int nrRows)->move the cursor the given nr of rows*/

                    /*Updating the ResultSet:
                    * update[type]()-> Updates the ResultSet obj not db tables
                    * updateRow()-> persist changes to db
                    * insertRow(),deleteRow()-> add or del row in db
                    * refreshRow()-> refresh ResultSet with any changes in db
                    * cancelRowUpdates()-> to cancel changes made to the current row*/
                }
            }

            //Transactions======================================================

            String updatePositionStatement = "UPDATE employees SET position=? WHERE emp_id=?";
            PreparedStatement pstmt = con.prepareStatement(updatePositionStatement);
            pstmt.setString(1, "lead developer");
            pstmt.setInt(2, 1);

            String updateSalaryStatement = "UPDATE employees SET salary=? WHERE emp_id=?";
            PreparedStatement pstmt2 = con.prepareStatement(updateSalaryStatement);
            pstmt2.setDouble(1, 3000);
            pstmt2.setInt(2, 1);

            boolean autoCommit = con.getAutoCommit();
            try{
                con.setAutoCommit(false);
                pstmt.executeUpdate();
                pstmt2.executeUpdate();
                con.commit();
            }catch (SQLException e) {
                con.rollback();
            }finally {
                con.setAutoCommit(autoCommit);
            }

            //DatabaseMetadata=====================================================

            DatabaseMetaData dbmd = con.getMetaData();
            ResultSet tableResultSet = dbmd.getTables(null, null, "%", null);
            while (tableResultSet.next()) {
                System.out.println(tableResultSet.getString("TABLE_NAME"));
            }

            ResultSet resultSet = null; //not null of course
            ResultSetMetaData rsmd = resultSet.getMetaData(); //Metadata about a ResultSet
            int nrColumns = rsmd.getColumnCount();

            IntStream.range(1, nrColumns).forEach(i -> {
                try {
                    System.out.println(rsmd.getColumnName(i));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });



        }catch (SQLException e){
            e.printStackTrace();
        }


    }


    @Data
     class Employee {
        private int id;
        private String name;
        private String position;
        private double salary;

    }


}
