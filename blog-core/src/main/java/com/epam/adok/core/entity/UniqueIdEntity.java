package com.epam.adok.core.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "unique_id")
@Inheritance(strategy = InheritanceType.JOINED)
public class UniqueIdEntity extends BaseEntity {

}
