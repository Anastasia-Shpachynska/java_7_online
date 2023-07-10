package dao;

import entity.Cats;
import utill.AppUtill;

public class CatsDao {

    private Cats[] cats = new Cats[2];

    public void expandArray() {
        Cats[] newCats = new Cats[cats.length * 2];
        System.arraycopy(cats, 0, newCats, 0, cats.length);
        cats = newCats;
    }

    public void create(Cats cat) {
        for (int i = 0; i < cats.length; i++) {
            if (cats[i] == null) {
                String id = AppUtill.getUUID();
                cat.setId(id);
                cats[i] = cat;
                break;
            }
        }
    }

    public void update(Cats cat) {
        for (int i = 0; i < cats.length; i++) {
            if (cats[i].getId().equals(cat.getId())) {
                cats[i] = cat;
            }
        }
    }

    public void delete(String id) {
        for (int i = 0; i < cats.length; i++) {
            try {
                if (cats[i].getId().equals(id)) {
                    cats[i] = null;
                }
            }
            catch (Exception e) {
                i++;
            }
        }
    }

    public Cats findOne(String id) {
        for (Cats cat : cats) {
            if (cat.getId().equals(id)) {
                return cat;
            }
        }
        return null;
    }

    public Cats[] findAll() {
        return cats;
    }
}

