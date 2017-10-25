package com.epam.adok.core.entity.comment;

import com.epam.adok.core.entity.Category;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CT")
public class CategoryComment extends AbstractComment {

    @Column(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
