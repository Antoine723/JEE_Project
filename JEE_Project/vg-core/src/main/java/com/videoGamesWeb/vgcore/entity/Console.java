package com.videoGamesWeb.vgcore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@DiscriminatorValue("1")
@Table(name="console")
public class Console extends Product {

    @JsonIgnore
    @OneToMany(mappedBy = "console")
    private Set<GameConsole> games;

    public Console(long id){
        this.id = id;
    } //TODO : find how to fix this (autoincrement on both child tables)
}
