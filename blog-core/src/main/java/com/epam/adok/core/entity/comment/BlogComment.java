package com.epam.adok.core.entity.comment;

import com.epam.adok.core.entity.AbstractEntity;
import com.epam.adok.core.entity.Blog;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("blog_type")
public class BlogComment extends AbstractEntity {

    @Column(name = "blog_id")
    private Blog blog;

    @Column(name = "parent_comment_id")
    private BlogComment parentComment;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public BlogComment getParentComment() {
        return parentComment;
    }

    public void setParentComment(BlogComment parentComment) {
        this.parentComment = parentComment;
    }


}
