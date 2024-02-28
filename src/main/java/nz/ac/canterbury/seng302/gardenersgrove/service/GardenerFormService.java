package nz.ac.canterbury.seng302.gardenersgrove.service;

import nz.ac.canterbury.seng302.gardenersgrove.entity.FormResult;
import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.repository.FormRepository;
import nz.ac.canterbury.seng302.gardenersgrove.repository.GardenerFormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for FormResults, defined by the @link{Service} annotation.
 * This class links automatically with @link{FormRepository}, see the @link{Autowired} annotation below
 */
@Service
public class GardenerFormService {
    private GardenerFormRepository gardenerFormRepository;

//    @Autowired
    public GardenerFormService(GardenerFormRepository gardenerFormRepository) {
        this.gardenerFormRepository = gardenerFormRepository;
    }
    /**
     * Gets all FormResults from persistence
     * @return all FormResults currently saved in persistence
     */
    public List<Gardener> getGardeners() {
        return gardenerFormRepository.findAll();
    }

    /**
     * Adds a formResult to persistence
     * @param gardener object to persist
     * @return the saved formResult object
     */
    public Gardener addGardener(Gardener gardener) {
        return gardenerFormRepository.save(gardener);
    }
}