package com.videoGamesWeb.vgweb.controllers;


import org.springframework.beans.factory.annotation.Value;

public class GenericController {

    @Value("${prefix}")
    protected String prefix;
}
