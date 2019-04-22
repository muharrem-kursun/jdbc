package personel.dao;
import model.Personel;

public interface PersonelDao {
    public void insert(Personel personel);
    public void delete(Personel personel);
    public void update(Personel personel);
    public void select();
    public void join();
}
