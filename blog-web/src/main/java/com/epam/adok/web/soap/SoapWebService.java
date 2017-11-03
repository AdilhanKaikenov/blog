package com.epam.adok.web.soap;

import com.epam.adok.core.entity.BaseEntity;
import com.epam.adok.core.entity.Category;

import javax.jws.WebService;
import java.util.Date;
import java.util.List;
import java.util.Set;

@WebService
public interface SoapWebService<T extends BaseEntity> {

    List<T> getAllByCategoryOrPeriod(Set<Category> categories, Date from, Date to);

}
