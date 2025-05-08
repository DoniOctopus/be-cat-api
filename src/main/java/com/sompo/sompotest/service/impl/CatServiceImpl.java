package com.sompo.sompotest.service.impl;

import com.sompo.sompotest.dto.CatDTO;
import com.sompo.sompotest.entity.Cat;
import com.sompo.sompotest.repository.CatRepository;
import com.sompo.sompotest.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;

    @Override
    public CatDTO saveCat(CatDTO catDTO) {
        Cat cat = new Cat(catDTO.getId(), catDTO.getUrl(), catDTO.getWidth(), catDTO.getHeight());
        cat = catRepository.save(cat);
        return new CatDTO(cat.getId(), cat.getUrl(), cat.getWidth(), cat.getHeight());
    }

    @Override
    public List<CatDTO> getAllCats() {
        return catRepository.findAll().stream()
                .map(cat -> new CatDTO(cat.getId(), cat.getUrl(), cat.getWidth(), cat.getHeight()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CatDTO> getCatById(String id) {
        return catRepository.findById(id).map(cat -> new CatDTO(cat.getId(), cat.getUrl(), cat.getWidth(), cat.getHeight()));
    }

    @Override
    public void deleteCat(String id) {
        catRepository.deleteById(id);
    }

    @Override
    public CatDTO updateCat(CatDTO catDTO) {
        Cat cat = new Cat(catDTO.getId(), catDTO.getUrl(), catDTO.getWidth(), catDTO.getHeight());
        cat = catRepository.save(cat);
        return new CatDTO(cat.getId(), cat.getUrl(), cat.getWidth(), cat.getHeight());
    }
}
