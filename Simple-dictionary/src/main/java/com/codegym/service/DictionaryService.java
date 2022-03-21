package com.codegym.service;

import com.codegym.model.Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictionaryService {
    private static Map<Integer, Dictionary> dictionaries = new HashMap<>();

    static {
        dictionaries.put(1,new Dictionary("hello", "Xin chào"));
        dictionaries.put(2,new Dictionary("how", "Thế nào"));
        dictionaries.put(3,new Dictionary("book", "Quyển vở"));
        dictionaries.put(4,new Dictionary("computer", "Máy tính"));
    }

    public List<Dictionary> findAll(){
        return new ArrayList<>(dictionaries.values());
    }
}
