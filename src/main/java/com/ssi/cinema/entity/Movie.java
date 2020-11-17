package com.ssi.cinema.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "premiere")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiere;

    @Column(name = "director")
    private String director;

    @Column(name = "min_age")
    private int minAge;

    @OneToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Movie(String title, LocalDate premiere, String director, int minAge, Genre genre) {
        this.title = title;
        this.premiere = premiere;
        this.director = director;
        this.minAge = minAge;
        this.genre = genre;
    }
}
