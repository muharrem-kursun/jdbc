package jdbc;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.*;

public class App
{
    //sorguları tanımladık
    static  final Logger LOGGER = Logger.getLogger(App.class);
    private static Connection connection = null;
    private final String INSERT= " insert into personel(Pid,Pad,Psoyad) values(?,?,?)";
    private  final  String SELECT = "select * from personel";
    private final  String UPDATE = "update personel set Pad=? ,Psoyad=? where Pid = ? ";
    private final  String DELETE = " delete from personel where Pid = ?";
    App()
    {
        try {
            //bağlantıyı sağladık
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/deneme2", "root", "123");
            LOGGER.info("veritabanı bağlantısı sağlandı. ");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            LOGGER.error("veri tabanı bağlantısı sağlanamadı." , e);
        }
    }

    public static void main( String[] args ) throws SQLException {
        //loglama konfigrasyonunu yaptık.
        PropertyConfigurator.configure("write.properties");
        App app = new App();
        app.insert();
        app.update();
        app.delete();
        app.select();
    }
    private void insert()
    {

        try {
            //bir Statement tanımladık
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1,2);
            preparedStatement.setString(2,"emir");
            preparedStatement.setString(3,"aglamaz");

            preparedStatement.executeUpdate();
            LOGGER.info("insert işlemi başarılı.");

        }
        catch (SQLException e)
        {
            LOGGER.error("insert islemi basarısız ", e);
            e.printStackTrace();
        }
    }
    private void select() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
            ResultSet resultSet= preparedStatement.executeQuery();
            LOGGER.info("select işlemi başarılı. \n");
            while (resultSet.next())
            {
                int id = resultSet.getInt("Pid");
                String ad = resultSet.getString("Pad");
                String soyad = resultSet.getString("Psoyad");
                System.out.println("id : " + id + "  ad  : "+ ad + " soyad : " + soyad);
            }

        }
        catch (SQLException e)
        {
            LOGGER.error("select işlemi başarısız" , e);
            e.printStackTrace();
        }


    }
    private void update()
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, "eyup");
            preparedStatement.setString(2, "baycol");
            preparedStatement.setInt(3, 1);
            preparedStatement.executeUpdate();
            LOGGER.info("update işlemi başarılı. ");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            LOGGER.error("update işlemi başarısız. ", e);
        }

    }
    private void delete ()
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,13);
            preparedStatement.executeUpdate();
            LOGGER.info("delete işlemi başarılı.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            LOGGER.error(" delete işlemi başarısız. ", e);
        }
    }

}
