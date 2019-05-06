package dao.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dao.personelDao;
import model.personel;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class personelDaoImpl implements personelDao {

    private final String select = "select * from personel";
    private final String delete = "delete from personel where Pid = ?";
    private final String update = "update personel set Pad = ?, Psoyad ,Pid = ? ";
    private final String insert = "insert into personel(Pid,Pad,Psoyad) values(?,?,?)";
    private  static final Logger LOGGER =  Logger.getLogger(personelDaoImpl.class);
    private DataSource dataSource ;
    Connection connection = null;

    public DataSource getDataSource() {
      if(dataSource == null)
      {
          HikariConfig hikariConfig = new HikariConfig();
          hikariConfig.setJdbcUrl("jdbc : mysql : // localhost/deneme2");
          hikariConfig.setUsername("root");
          hikariConfig.setPassword("123");
          hikariConfig.setMaximumPoolSize(10);
          hikariConfig.setAutoCommit(false);
          hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
          hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
          hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

          dataSource = new HikariDataSource(hikariConfig);
      }

        return dataSource;
    }

    @Override
    public void insert() {
        try {
            PropertyConfigurator.configure("write.properties");
            connection = DriverManager.getConnection("jdbc : mysql : // localhost/deneme2","root","123");
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
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
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void select() {

    }
}
