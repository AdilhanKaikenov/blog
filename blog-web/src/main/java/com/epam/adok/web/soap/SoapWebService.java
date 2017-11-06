package com.epam.adok.web.soap;

import com.epam.adok.core.entity.AbstractBaseEntity;
import com.epam.adok.core.entity.Category;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;
import java.util.Set;

@WebService
public interface SoapWebService<T extends AbstractBaseEntity> {

        List<T> getAllByCategoryOrPeriod(@WebParam(name = "categories")Set<Category> categories,
                                     @WebParam(name = "from") Date from,
                                     @WebParam(name = "to") Date to);

}
