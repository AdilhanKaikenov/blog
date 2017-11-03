package com.epam.adok.web.soap;

import com.epam.adok.core.entity.Blog;

import javax.jws.WebService;

@WebService(endpointInterface = "com.epam.adok.web.soap.SoapWebService", serviceName = "BlogSoapWS")
public class BlogSoapWS implements SoapWebService<Blog> {
}
