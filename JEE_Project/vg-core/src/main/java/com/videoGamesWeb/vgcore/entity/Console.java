package com.videoGamesWeb.vgcore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@DiscriminatorValue("1")
@Table(name="Console")
public class Console extends Product {
}
