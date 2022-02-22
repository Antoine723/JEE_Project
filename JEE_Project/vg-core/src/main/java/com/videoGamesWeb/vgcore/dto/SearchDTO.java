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
    private float min_price;
    private float max_price;
    private int min_score;
}
