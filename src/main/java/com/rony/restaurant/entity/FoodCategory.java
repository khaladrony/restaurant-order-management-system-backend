package com.rony.restaurant.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Data
@Table(name = "features_mapping")
public class FoodCategory  extends CommonColumn{
}
