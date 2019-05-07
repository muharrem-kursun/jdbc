package dao;

import model.Personel;

public interface PersonelDao {

    void insert(Personel personel);

    void update(Personel personel);

    void delete(Personel personel);

    void select();
}
