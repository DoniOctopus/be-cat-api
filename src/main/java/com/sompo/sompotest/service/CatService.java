package com.sompo.sompotest.service;

import com.sompo.sompotest.dto.CatDTO;

import java.util.List;
import java.util.Optional;

public interface CatService {
    CatDTO saveCat(CatDTO catDTO);
    List<CatDTO> getAllCats();
    Optional<CatDTO> getCatById(String id);
    void deleteCat(String id);
    CatDTO updateCat(CatDTO catDTO);
}
