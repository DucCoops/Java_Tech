package com.lab02;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product, String>{

    private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "abc123";

    public ProductDAO() {
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

    public  List<Product> query(String sql,  Object... parameters) {
        List<Product> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParameter(statement,parameters);
            rs = statement.executeQuery();
            while (rs.next()){
                results.add(new Product(rs.getString("id"),rs.getString("name"),rs.getInt("price")));
            }
            return results;

        }catch (SQLException e){
            return null;
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (rs != null) {
                    rs.close();
                }

            }catch (SQLException e){
                return null;
            }
        }
    }

    public int update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(sql);
            setParameter(statement,parameters);
            statement.executeUpdate();
            connection.commit();
            return 1;
        }
        catch (SQLException e){
            if (connection != null) {
                try {
                    connection.rollback();
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
            return -1;
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            }catch (SQLException e2){

                e2.printStackTrace();

            }
        }
    }

    private void setParameter(PreparedStatement statement, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                Object parameter = parameters[i];
                int index = i+1;
                if (parameter instanceof Integer){
                    statement.setInt(index,(int) parameter);
                }
                if (parameter instanceof String){
                    statement.setString(index,(String) parameter);
                }
                if (parameter instanceof Float){
                    statement.setFloat(index,(float) parameter);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public String add(Product item) {
        String sql = "INSERT INTO PRODUCT VALUES(ES(?,?,?)";
        update(sql,item.getId(),item.getName(),item.getPrice());
        return item.getId();
    }

    @Override
    public List<Product> readAll() {
        String sql = "SELECT * FROM PRODUCT";
        return query(sql);
    }

    @Override
    public Product read(String id) {
        String sql = "SELECT * FROM PRODUCT WHERE ID = ?";
        List<Product> list= query(sql,id);
        if (list.isEmpty()){
            return  null;
        }else
            return list.get(0);

    }

    @Override
    public boolean update(Product item) {
        String sql = "UPDATE PRODUCT  SET NAME = ?, PRICE = ? WHERE ID = ?";
        int check = update(sql,item.getName(),item.getPrice(),item.getId());
        if (check == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM PRODUCT WHERE ID = ?";
        int check = update(sql,id);
        if (check == 1)
            return true;
        return false;
    }

    public static void  createTable(){
        try{
            String sql = "DROP TABLE IF EXISTS PRODUCT";
            Connection cnt = getConnection();
            Statement statement = (Statement) cnt.createStatement();
            statement.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS Procduct( ID VARCHAR(5) PRIMARY KEY, NAME VARCHAR(50), PRICE int )";
            statement.executeUpdate(sql);
            cnt.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
