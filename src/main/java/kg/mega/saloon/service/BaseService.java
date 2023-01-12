package kg.mega.saloon.service;

import kg.mega.saloon.enums.WorkDayEnum;

import java.util.List;
import java.util.Map;

public interface BaseService<Z> {

    Z save(Z t);
    Z findById(Long id);
    Z delete(Long id);
    List<Z> findAll();


}
