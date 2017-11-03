package com.epam.adok.web.soap;

import com.epam.adok.core.entity.Blog;
import com.epam.adok.core.entity.Category;
import com.epam.adok.core.service.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;
import java.util.Set;

@WebService(endpointInterface = "com.epam.adok.web.soap.SoapWebService", serviceName = "BlogSoapWS")
public class BlogSoapWS implements SoapWebService<Blog> {

    private static final Logger log = LoggerFactory.getLogger(BlogSoapWS.class);

    @EJB
    private BlogService blogService;

    @WebMethod
    @Override
    public List<Blog> getAllByCategoryOrPeriod(Set<Category> categories, Date from, Date to) {
        log.info("Entering getAllByCategoryOrPeriod() method.");
        return blogService.findAllBlogsByParameters(categories, from, to);
    }
}
