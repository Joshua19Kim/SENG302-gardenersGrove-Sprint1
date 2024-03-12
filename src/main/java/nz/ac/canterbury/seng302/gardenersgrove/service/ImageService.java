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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;




@Service
public class ImageService {

    private final Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final GardenerFormService gardenerFormService;
    private final String IMAGE_PATH = "/images/";
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";
    private static int MAX_SIZE = 10*1024*1024;

    public List<String> validExtensions = new ArrayList<>(Arrays.asList("jpg", "png", "svg"));
    @Autowired
    public ImageService(GardenerFormService gardenerFormService) {
        this.gardenerFormService = gardenerFormService;
    }

    public Optional<String> saveImage(MultipartFile file) {
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
                logger.info("File location: " + filePath);

                if (checkValidImage(file, fileName).isEmpty()) {
                    Files.write(filePath, file.getBytes());
                    gardener.setProfilePicture(newFileName);
                    gardenerFormService.addGardener(gardener);
                    return Optional.empty();
                } else {
                    return checkValidImage(file, fileName);
                }

            } else {
                return Optional.of("I made a boo boo");
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return Optional.empty();
    }

    public boolean isFileSizeValid(MultipartFile file) {
        return file.getSize() <= MAX_SIZE;
    }

    public String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "";
    }


    public boolean checkValidExtension (String fileName) {
        String extension = getFileExtension(fileName);
        return validExtensions.contains(extension);
    }

    public Optional<String> checkValidImage(MultipartFile file, String fileName) {
        if (checkValidExtension(fileName) && isFileSizeValid(file)) {
            return Optional.empty();
        } else if ((!checkValidExtension(fileName)) && isFileSizeValid(file)) {
            return Optional.of("Image must be of type png, jpg or svg");
        } else if (checkValidExtension(fileName) && (!isFileSizeValid(file))) {
            return Optional.of("Image must be less than 10MB");
        } else {
            return Optional.of("Image must be of type png, jpg or svg " + "\n" +
                    "Image must be less than 10MB");
        }
    }



}
