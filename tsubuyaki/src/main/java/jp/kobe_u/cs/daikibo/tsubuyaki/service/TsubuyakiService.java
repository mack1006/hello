package jp.kobe_u.cs.daikibo.tsubuyaki.service;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
import jp.kobe_u.cs.daikibo.tsubuyaki.repository.TsubuyakiRepository;

@Service

public class TsubuyakiService {
    @Autowired
    TsubuyakiRepository repo;

    public Tsubuyaki postTsubuyaki(Tsubuyaki t) {
        String name = t.getName();
        if (name == null || name.length() == 0) {
            t.setName("名無しさん");
        }
        t.setCreatedAt(new Date(0));
        return repo.save(t);

    }

    public ArrayList<Tsubuyaki> getAllTsubuyaki() {
        Iterable<Tsubuyaki> found = repo.findAll();
        ArrayList<Tsubuyaki> list = new ArrayList<>();
        found.forEach(list::add);
        return list;
    }

    public ArrayList<Tsubuyaki> searchTsubuyaki(String word) {
        ArrayList<Tsubuyaki> list = getAllTsubuyaki();
        ArrayList<Tsubuyaki> searchedList = new ArrayList<>();
        for(Tsubuyaki t: list){
           if( t.getComment().contains(word)){
               searchedList.add(t);
           }
        }
        return searchedList;
    }
}
