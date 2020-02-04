package com.example.my_oyo.Repositry;

import com.example.my_oyo.OyoData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OyoRepositry extends CrudRepository<OyoData,Integer> {
    List<OyoData>findOyoDataByGender(String gender);
    OyoData findOyoDataById(Integer id);
    OyoData findOyoDataByFileName(String filename);
    List<OyoData>findOyoDataByCityName(String CityName);
}
