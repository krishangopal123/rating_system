package com.example.oye_rikhshaw.Repositry;

import com.example.oye_rikhshaw.Dtos.RikhshawDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RikhshawRepo  extends JpaRepository<RikhshawDto,Integer> {
    RikhshawDto findByTelephoneNumber(String telephoneNumber);
}
