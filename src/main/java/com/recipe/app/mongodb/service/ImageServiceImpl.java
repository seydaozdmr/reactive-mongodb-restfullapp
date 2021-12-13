package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.Recipe;
import com.recipe.app.mongodb.repository.reaktive.RecipeReactiveRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService{
    private final RecipeReactiveRepository repository;

    public ImageServiceImpl(RecipeReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> saveImageFile(String recipeId, MultipartFile multipartFile) {
        Recipe recipe = repository.findById(recipeId).block();
        if(recipe!=null){
            Byte[] bytes=new Byte[0];
            try{
                bytes=new Byte[multipartFile.getBytes().length];

                int i=0;
                for(byte b:multipartFile.getBytes()){
                    bytes[i++]=b;
                }

                recipe.setImage(bytes);
            }catch (IOException e){
                System.out.println(e.getMessage());
                throw new RuntimeException("dosya kaydetme işlemi başarısız");
            }
        }else{
            throw new RuntimeException("tarif bilgisi bulunamıştır.");
        }

        repository.save(recipe).block();
        return Mono.empty();
    }
}
