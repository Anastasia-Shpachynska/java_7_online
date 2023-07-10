package service;

import dao.CatsDao;
import entity.Cats;

public class CatsService {

    private static CatsDao catDao = new CatsDao();
    private int size = 0;

    public void create(Cats cats) {
        if (cats.getAge() <= 0 || cats.getAge() >= 35) {
            System.out.println("\u001B[31m" + "The cat was not saved. Please, enter the correct value." + "\u001B[0m");
        }
        if (size == cats.length) {
            catDao.expandArray();
        }
        catDao.create(cats);
    }

    public void update(Cats cat){
        catDao.update(cat);
    }

    public static void delete(String id){
        catDao.delete(id);
    }

    public Cats findOne(String id){
        return catDao.findOne(id);
    }

    public Cats[] findAll(){
        return catDao.findAll();
    }
}
