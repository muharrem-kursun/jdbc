import dao.PersonelDao;
import dao.PersonelDaoImpl;
import model.Personel;

public class App
{
    public static void main( String[] args )
    {
        PersonelDao personelDao = new PersonelDaoImpl();
        Personel personel = new Personel();
        personel.setId(6);
        personel.setAd("deneme");
        personel.setSoyad("deneme");
        // personelDao.insert(personel);
        //  personelDao.delete(personel);
        personel.setId(19);
        personelDao.update(personel);
        personelDao.select();
    }
}
