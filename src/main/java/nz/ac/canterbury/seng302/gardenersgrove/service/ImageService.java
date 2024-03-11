package nz.ac.canterbury.seng302.gardenersgrove.service;


import nz.ac.canterbury.seng302.gardenersgrove.entity.FormResult;
import nz.ac.canterbury.seng302.gardenersgrove.entity.Gardener;
import nz.ac.canterbury.seng302.gardenersgrove.repository.FormRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.InputStream;
import java.util.Optional;



@Service
public class ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final GardenerFormService gardenerFormService;
    private final String IMAGE_PATH = "/images/";
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @Autowired
    public ImageService(GardenerFormService gardenerFormService) {
        this.gardenerFormService = gardenerFormService;
    }

    public void saveImage(MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        Optional<Gardener> gardenerOptional = gardenerFormService.findByEmail(currentUserEmail);

        try {
            Files.createDirectories(Paths.get(UPLOAD_DIRECTORY));
            String fileName = file.getOriginalFilename();
            if (gardenerOptional.isPresent()) {
                Gardener gardener = gardenerOptional.get();
                String newFileName = gardener.getId() + "." + fileName.substring(fileName.lastIndexOf(".")+1); // mess around with better version of this
                Path filePath = Paths.get(UPLOAD_DIRECTORY, newFileName);

                Files.write(filePath, file.getBytes());
                gardener.setProfilePicture(newFileName);
                gardenerFormService.addGardener(gardener);

            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

    }

}
