package com.epam.adok.core.entity.comment;

import com.epam.adok.core.entity.Category;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CT")
public class CategoryComment extends AbstractComment {

    @OneToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
