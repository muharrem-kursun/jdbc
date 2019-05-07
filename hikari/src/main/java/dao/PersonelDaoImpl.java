package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import model.Personel;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.sql.DataSource;
import java.sql.*;

public class PersonelDaoImpl implements PersonelDao {


    private  static final Logger LOGGER =  Logger.getLogger(PersonelDaoImpl.class);

    private final String DATABASE="jdbc:mysql://localhost:3306/deneme2";
    private final String USER_NAME = "root";
    private final String PASSWORD = "123";

    private final String INSERT = "insert into personel(Pid,Pad,Psoyad) values(?,?,?)";
    private final String DELETE = "delete from personel where Pid = ?";
    private final String UPDATE = "update personel set  Pad = ? ,Psoyad = ? where Pid = ? ";
    private final String SELECT = "select * from personel";


    Connection connection = null;
    private DataSource dataSource  = null;

    public  PersonelDaoImpl ()
    {
        try {
            PropertyConfigurator.configure("write.properties");
            DataSource dataSource = getDataSource();
            connection = dataSource.getConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

 public DataSource getDataSource() {
        if(dataSource == null)
        {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/deneme2");
            hikariConfig.setUsername("root");
            hikariConfig.setPassword("123");
            hikariConfig.setMaximumPoolSize(10);
            hikariConfig.setAutoCommit(false);
            hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
            hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
            hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            try{
                dataSource = new HikariDataSource(hikariConfig);
            }catch (Exception E)
            {
                E.printStackTrace();
            }

        }

        return dataSource;
}

    @Override
    public void insert(Personel personel) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, personel.getId());
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
    public void update(Personel personel) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, personel.getId());
            preparedStatement.setString(2,personel.getAd());
            preparedStatement.setString(3,personel.getSoyad());
            preparedStatement.executeUpdate();
            LOGGER.info("update işlemi başarılı");
        }
        catch (SQLException e)
        {
            LOGGER.error("update işlemi basarisiz  " + e);
        }

    }

    @Override
    public void delete(Personel personel) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, personel.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("delete işlemi başarılı");
        }
        catch (SQLException e)
        {
            LOGGER.error("delete işlemi basarisiz  " + e);
        }

    }

    @Override
    public void select() {
        try {

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
}
