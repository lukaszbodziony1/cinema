package com.ssi.cinema.service;

import com.ssi.cinema.dto.CinemaDto;
import com.ssi.cinema.entity.Cinema;
import com.ssi.cinema.mapper.CinemaMapper;
import com.ssi.cinema.repository.CinemaRepository;
import com.ssi.cinema.request.cinema.CreateCinemaRequest;
import com.ssi.cinema.request.cinema.UpdateCinemaRequest;
import com.ssi.cinema.request.common.CommonGetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    private final CinemaRepository repository;
    private final CinemaMapper mapper;

    @Autowired
    public CinemaService(CinemaRepository repository, CinemaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void addCinema(CreateCinemaRequest request) {
        repository.addCinema(request);
    }

    public List<CinemaDto> getAllCinemas(CommonGetRequest request) {
        return mapper.toCinema(repository.getAllCinemas(request));
    }

    public CinemaDto getCinemaById(int id) {
        return mapper.toCinema(repository.getCinemaById(id));
    }

    public void deleteCinema(int id) {
        repository.deleteCinema(id);
    }

    public void updateCinema(UpdateCinemaRequest request) {
        Cinema cinema = repository.getCinemaById(request.getId());
        cinema.setAddress(request.getAddress());
        cinema.setName(request.getName());
        repository.updateCinema(cinema);
    }
}
