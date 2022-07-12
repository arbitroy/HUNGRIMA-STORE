package com.example.hungrima_store;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.*;
import java.util.Date;


public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, Integer width, Integer height) throws IOException {
        Parent root;

        FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
        root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene( new Scene(root, width, height));
        stage.centerOnScreen();
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password, String position){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HUNGRIMA", "postgres", "1234");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            }else{
                psInsert = connection.prepareStatement("INSERT INTO users(username, password, position) VALUES (?, ?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.setString(3, position);
                psInsert.executeUpdate();

                changeScene(event, "home.fxml", "Welcome!",1286,800);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void logInUser(ActionEvent event, String username, String password){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HUNGRIMA", "postgres", "1234");
            preparedStatement = connection.prepareStatement("SELECT password, position FROM users WHERE username = ?");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()){
                System.out.println("User not found in the database...");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            }else{
                while(resultSet.next()){
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)){
                        changeScene(event,"home.fxml","Home Page",1286,800);
                    }else{
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void track(ActionEvent event, String pname, Date date, String t_type, Integer quantity, Integer s_count, Integer id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HUNGRIMA", "postgres", "1234");
            preparedStatement = connection.prepareStatement("INSERT INTO transactions(pname, date, t_type, quantity, s_count, id) VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1,pname);
            preparedStatement.setDate(2, (java.sql.Date) date);
            preparedStatement.setString(3, t_type);
            preparedStatement.setInt(4, quantity);
            preparedStatement.setInt(5, s_count);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updatetrack(ActionEvent event, String pname, Integer quantity, Integer id , String t_type){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HUNGRIMA", "postgres", "1234");
            preparedStatement = connection.prepareStatement("UPDATE transactions SET quantity = ?  WHERE id =  ? AND t_type =  ? AND pname = ? ");
            preparedStatement.setInt(1,quantity);
            preparedStatement.setInt(2, id);
            preparedStatement.setString(3, t_type);
            preparedStatement.setString(4, pname);
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteTrack(ActionEvent event, Integer id, String t_type, String pname){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HUNGRIMA", "postgres", "1234");
            preparedStatement = connection.prepareStatement("DELETE FROM transactions WHERE id =  ? AND t_type =  ? AND pname = ? ");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, t_type);
            preparedStatement.setString(3, pname);
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


