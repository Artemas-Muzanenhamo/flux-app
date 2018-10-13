package com.movies.movieclient.domain;


import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class MovieEvent {
    private Movie movie;
    private Date when;
}
