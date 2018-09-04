package com.movies.movieclient.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MovieEvent {
    private Movie movie;
    private Date when;
}
