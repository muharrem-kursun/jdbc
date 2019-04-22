package personel.dao.impl;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import personel.dao.PersonelDao;
import model.Personel;

import java.sql.*;


public class PersonelDaoImpl implements PersonelDao {

    private final String DATABASE="jdbc:mysql://localhost:3306/deneme2";
    private final String USER_NAME = "root";
    private final String PASSWORD = "123";
    private final String INSERT="insert into personel(Pid,Pad,Psoyad) values(?,?,?)";
    private final String DELETE="delete from personel where Pid = ?";
    private final String UPDATE="update personel set  Pad = ? ,Psoyad = ? where Pid = ? ";
    private final String SELECT="select * from personel";
    private static  final Logger LOGGER = Logger.getLogger(PersonelDaoImpl.class);
    Connection connection = null;


    @Override
    public void insert(Personel personel) {
        try {
            PropertyConfigurator.configure("write.properties");
            connection = DriverManager.getConnection(DATABASE,USER_NAME,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1,personel.getId());
            preparedStatement.setString(2,personel.getAd());
            preparedStatement.setString(3,personel.getSoyad());
            preparedStatement.executeUpdate();
                LOGGER.info("insert işlemi başarılı");
    }
        catch (SQLException e)
        {
            LOGGER.error("insert işlemi basarisiz  " + e);
        }

    }

    @Override
    public void delete(Personel personel) {
        try {
            PropertyConfigurator.configure("write.properties");
            connection = DriverManager.getConnection(DATABASE,USER_NAME,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,personel.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("delete islemi basarili");
        }
        catch (Exception e)
        {
            LOGGER.error("delete işlemi basarısız  " +  e);
        }

    }

    @Override
    public void update(Personel personel) {
        try{
            PropertyConfigurator.configure("write.properties");
            connection = DriverManager.getConnection(DATABASE,USER_NAME,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,personel.getAd());
            preparedStatement.setString(2,personel.getSoyad());
            preparedStatement.setInt(3,personel.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("Update islemi basarili ");

        }
        catch (SQLException e)
        {
            LOGGER.error("update işlemi basarısız " +e);
        }
    }

    @Override
    public void select() {
        try {
            PropertyConfigurator.configure("write.properties");
            connection = DriverManager.getConnection(DATABASE,USER_NAME,PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int id = resultSet.getInt("Pid");
                String ad = resultSet.getString("Pad");
                String soyad = resultSet.getString("Psoyad");
                System.out.println("id : " + id + "  ad  : "+ ad + " soyad : " + soyad);

            }
            LOGGER.info("select işlemi başarılı");
        }catch (SQLException e)
        {
           LOGGER.error("select işlemi basarısız  " + e);
        }
    }
    public void join() {

    }
}
