package com.recipe.app.mongodb.controller;

import com.recipe.app.mongodb.model.Recipe;
import com.recipe.app.mongodb.service.ImageService;
import com.recipe.app.mongodb.service.RecipeService;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
public class ImageController {
    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable String id){
        Recipe recipe=recipeService.findById(id).block();

        if(recipe.getImage()!=null){
            byte[] array=new byte[recipe.getImage().length];

            int i=0;

            for(Byte wrappedData:recipe.getImage()){
                array[i++]=wrappedData;
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(array, headers, HttpStatus.OK);
        }else {
            throw new RuntimeException("dosya bulunmamıştır.");
        }
    }

    @PostMapping("/recipe/{id}/image")
    public ResponseEntity<Void> saveImage(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){
        return ResponseEntity.ok(imageService.saveImageFile(id,file).block());
    }


}
