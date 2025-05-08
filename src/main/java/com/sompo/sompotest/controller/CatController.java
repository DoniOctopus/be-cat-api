package com.sompo.sompotest.controller;

import com.sompo.sompotest.dto.CatDTO;
import com.sompo.sompotest.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cat")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @PostMapping("/insert")
    public ResponseEntity<CatDTO> createCat(@RequestBody CatDTO catDTO) {
        CatDTO savedCat = catService.saveCat(catDTO);
        return new ResponseEntity<>(savedCat, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<CatDTO> getAllCats() {
        return catService.getAllCats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatDTO> getCatById(@PathVariable String id) {
        Optional<CatDTO> catDTO = catService.getCatById(id);
        return catDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public ResponseEntity<CatDTO> updateCat(@RequestBody CatDTO catDTO) {
        CatDTO updatedCat = catService.updateCat(catDTO);
        return new ResponseEntity<>(updatedCat, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCat(@PathVariable String id) {
        catService.deleteCat(id);
        return ResponseEntity.noContent().build();
    }
}

