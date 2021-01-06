package com.ssi.cinema.service;

import com.ssi.cinema.dto.SpectacleDto;
import com.ssi.cinema.entity.Cinema;
import com.ssi.cinema.entity.Movie;
import com.ssi.cinema.entity.Spectacle;
import com.ssi.cinema.mapper.SpectacleMapper;
import com.ssi.cinema.repository.CinemaRepository;
import com.ssi.cinema.repository.MovieRepository;
import com.ssi.cinema.repository.SpectacleRepository;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.spectacle.AddSpectacleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpectacleService {
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final SpectacleRepository spectacleRepository;
    private final SpectacleMapper mapper;

    @Autowired
    SpectacleService(MovieRepository movieRepository, CinemaRepository cinemaRepository, SpectacleRepository spectacleRepository, SpectacleMapper mapper) {
        this.cinemaRepository = cinemaRepository;
        this.movieRepository = movieRepository;
        this.spectacleRepository = spectacleRepository;
        this.mapper = mapper;
    }

    public void addNewSpectacle(AddSpectacleRequest request) {
        Movie movie = movieRepository.getMovieById(request.getMovieId());
        Cinema cinema = cinemaRepository.getCinemaById(request.getCinemaId());
        Spectacle spectacle = new Spectacle(request.getDate(), movie, cinema);
        spectacleRepository.addNewSpectacle(spectacle);
    }

    public List<SpectacleDto> getSpectacles(CommonGetRequest request) {
        return mapper.toDto(spectacleRepository.getAllSpectacles(request));
    }

    public List<SpectacleDto> getSpectaclesForCinema(int id) {
        return mapper.toDto(spectacleRepository.getAllSpectaclesFromCinema(id));
    }

    public SpectacleDto getSpectacleById(int id) {
        return mapper.toDto(spectacleRepository.getSpectacleById(id));
    }

    public void deleteSpectacle(int id) {
        Spectacle spectacle = spectacleRepository.getSpectacleById(id);
        spectacleRepository.deleteSpectacle(spectacle);
    }
}
