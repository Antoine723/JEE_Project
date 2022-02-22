package com.videoGamesWeb.vgcore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchDTO {

    private String input;
    private String sort_by;
    private String sort_asc;
    private List<String> consoles;
    private int min_price;
    private int max_price;
}
