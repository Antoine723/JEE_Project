package com.videoGamesWeb.vgweb.viewControllers;


import org.springframework.beans.factory.annotation.Value;

public class GenericViewController {

    @Value("${prefix}")
    protected String prefix;
}
