package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;
    @Autowired
    private CityRepository cityRepository;

    @Transactional
    public EventDTO update(EventDTO dto, Long id){
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("recurso não encontrado");
        }
        Event event = repository.getReferenceById(id);
        City city = cityRepository.getReferenceById(dto.getCityId());
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setUrl(dto.getUrl());
        event.setCity(city);
        repository.save(event);
        return new EventDTO(event);
    }
}
