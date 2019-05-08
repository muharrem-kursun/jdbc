package database;

import developer.Developer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
    private Connection connection = null;

    private final String DATABASE = "jdbc:mysql://localhost:3306/developers";
    private final String USER_NAME = "root";
    private final String PASSWORD = "123";

    private final String ADD_DATA = "insert into developer(Did,Dname,Dsurname,Dage,Dgender,Ddepartment) values (?,?,?,?,?,?)";

    private int id =1;

    Developer developer = new Developer();


    public  Database()
    {
        try {
            connection = DriverManager.getConnection(DATABASE,USER_NAME,PASSWORD);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void insert(Developer developer)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_DATA);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,developer.getName());
            preparedStatement.setString(3,developer.getSurName());
            preparedStatement.setInt(4,developer.getAge());
            preparedStatement.setString(5,developer.getGender());
            preparedStatement.setString(6,developer.getDepartment());
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    id++;
    }

}
