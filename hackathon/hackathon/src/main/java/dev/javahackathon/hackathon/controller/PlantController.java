package dev.javahackathon.hackathon.controller;

import dev.javahackathon.hackathon.model.Plant;
import dev.javahackathon.hackathon.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    private PlantService plantService;

    @GetMapping
    public ResponseEntity<List<Plant>> getAllPlants(
        @RequestParam(required = false) String type,
        @RequestParam(required = false) String region,
        @RequestParam(required = false) String use,
        @RequestParam(required = false) String search
    ) {
        try {
            List<Plant> plants = plantService.filterPlants(type, region, use, search);
            return ResponseEntity.ok(plants);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Plant>> getPlantById(@PathVariable String id) {
        try {
            Optional<Plant> plant = plantService.getPlantById(id);
            if (plant.isPresent()) {
                return ResponseEntity.ok(plant);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Plant> addPlant(@RequestBody Plant plant) {
        try {
            Plant savedPlant = plantService.addPlant(plant);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPlant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable String id, @RequestBody Plant plant) {
        try {
            Plant updatedPlant = plantService.updatePlant(id, plant);
            return ResponseEntity.ok(updatedPlant);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable String id) {
        try {
            plantService.deletePlant(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
