package com.videoGamesWeb.vgcore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {

    private String input;
    private String sort_by;
    private String sort_asc;
}
