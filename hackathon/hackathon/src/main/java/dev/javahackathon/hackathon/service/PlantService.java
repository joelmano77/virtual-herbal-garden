package dev.javahackathon.hackathon.service;

import dev.javahackathon.hackathon.model.Plant;
import dev.javahackathon.hackathon.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    @Autowired
    private PlantRepository plantRepository;

    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    public Optional<Plant> getPlantById(String id) {
        return plantRepository.findById(id);
    }

    public Plant addPlant(Plant plant) {
        return plantRepository.save(plant);
    }

    public Plant updatePlant(String id, Plant plant) {
        plant.setId(id);
        return plantRepository.save(plant);
    }

    public void deletePlant(String id) {
        plantRepository.deleteById(id);
    }

    public List<Plant> filterPlants(String plantType, String region, String medicinalUse, String search) {
        List<Plant> plants = plantRepository.findAll();
        
        // Apply filters sequentially
        if (plantType != null && !plantType.isEmpty()) {
            plants = plants.stream()
                .filter(plant -> plantType.equalsIgnoreCase(plant.getPlantType()))
                .toList();
        }
        
        if (region != null && !region.isEmpty()) {
            plants = plants.stream()
                .filter(plant -> region.equalsIgnoreCase(plant.getRegion()))
                .toList();
        }
        
        if (medicinalUse != null && !medicinalUse.isEmpty()) {
            plants = plants.stream()
                .filter(plant -> plant.getMedicinalUses() != null && 
                    plant.getMedicinalUses().stream()
                        .anyMatch(use -> use.toLowerCase().contains(medicinalUse.toLowerCase())))
                .toList();
        }
        
        if (search != null && !search.isEmpty()) {
            plants = plants.stream()
                .filter(plant -> 
                    (plant.getBotanicalName() != null && 
                        plant.getBotanicalName().toLowerCase().contains(search.toLowerCase())) ||
                    (plant.getCommonNames() != null && 
                        plant.getCommonNames().stream()
                            .anyMatch(name -> name.toLowerCase().contains(search.toLowerCase()))))
                .toList();
        }
        
        return plants;
    }
}
