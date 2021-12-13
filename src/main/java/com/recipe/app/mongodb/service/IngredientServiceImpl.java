package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.Ingredient;
import com.recipe.app.mongodb.model.Recipe;
import com.recipe.app.mongodb.repository.reaktive.RecipeReactiveRepository;
import com.recipe.app.mongodb.repository.reaktive.UnitOfMeasureReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final RecipeReactiveRepository recipeReactiveRepository;
    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    public IngredientServiceImpl(RecipeReactiveRepository recipeReactiveRepository, UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
    }

    @Override
    public Mono<Ingredient> findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
//        Mono<Recipe> recipeMono=recipeReactiveRepository.findById(recipeId);
//        Recipe recipe=recipeMono.block();
//        if(recipe==null)
//            throw new NoSuchElementException("aranan eleman bulunamamıştır");
//
//        Ingredient ingredient=recipe.getIngredients().stream()
//                .filter(ingredient1 -> ingredient1.getId().equals(ingredientId))
//                .findFirst()
//                .orElseThrow(()-> new NoSuchElementException("aranan malzeme bulunamadı"));

        //ingredient.setRecipe(recipe);

        //return Mono.just(ingredient);
        return recipeReactiveRepository.findById(recipeId)
                .flatMapIterable(Recipe::getIngredients)
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .single();
//        return  recipeReactiveRepository.findById(recipeId)
//                .map(r->
//                    r.getIngredients()
//                            .stream()
//                            .filter(i->i.getId().equals(ingredientId))
//                            .findFirst())
//                .filter(Optional::isPresent)
//                .map(Optional::get);
    }

    @Override
    public Mono<Ingredient> saveIngredient(String recipeId,Ingredient ingredient) {
        Recipe recipe=recipeReactiveRepository.findById(recipeId).block();

        if(recipe==null)
            throw new NoSuchElementException("aranana eleman bulunamıştır");

        Optional<Ingredient> ingredient1=recipe
                .getIngredients()
                .stream()
                .filter(i->i.equals(ingredient))
                .findFirst();

        if(ingredient1.isPresent()){
            Ingredient foundIngredient=ingredient1.get();
            foundIngredient.setAmount(ingredient.getAmount());
            foundIngredient.setDescription(ingredient.getDescription());
            foundIngredient.setUnitOfMeasure(unitOfMeasureReactiveRepository
                    .findById(ingredient.getUnitOfMeasure().getId())
                    .block());
            if(foundIngredient.getUnitOfMeasure()==null)
                throw new NoSuchElementException("uom bulunamadı");
        }else{
            recipe.addIngredient(ingredient);
        }

        Recipe savedRecipe=recipeReactiveRepository.save(recipe).block();

        Optional<Ingredient> savedIngredientOptional=savedRecipe.getIngredients().stream()
                .filter(i->i.equals(ingredient)).findFirst();

        if(!savedIngredientOptional.isPresent()){
            savedIngredientOptional=recipe.getIngredients().stream()
                    .filter(recipeIngredient->recipeIngredient.getDescription().equals(ingredient.getDescription()))
                    .filter(recipeIngredient->recipeIngredient.getAmount().equals(ingredient.getAmount()))
                    .filter(recipeIngredient->recipeIngredient.getUnitOfMeasure().getId().equals(ingredient.getUnitOfMeasure().getId()))
                    .findFirst();
        }

        return Mono.just(savedIngredientOptional.get());
    }

    @Override
    public Flux<List<Ingredient>> findAllByRecipeId(String recipeId) {
        Mono<Recipe> recipe=recipeReactiveRepository.findById(recipeId);
        Recipe recipe1=recipe.block();
        if(recipe1==null)
            throw new NoSuchElementException("aranan tarif bulunamadı");

        List<Ingredient> list=recipe1.getIngredients();
        return Flux.just(list);
    }

    @Override
    public Mono<Void> deleteById(String recipeId, String idToDelete) {
        //first i'm looking for Recipe
        Recipe recipe=recipeReactiveRepository.findById(recipeId).block();

        if(recipe!=null){
            Optional<Ingredient> optionalIngredient=recipe.getIngredients().stream()
                    .filter(ing->ing.getId().equals(idToDelete))
                    .findFirst();
            if(optionalIngredient.isPresent()){
                recipe.getIngredients().remove(optionalIngredient.get());
                recipeReactiveRepository.save(recipe);
            }else{
                throw new NoSuchElementException("silinmesi istenen malzeme bulunamımıştır");
            }
        }else{
            throw new NoSuchElementException("silinmesi istenen malzemeye ait tarif bulunamamıştır.");
        }

        return Mono.empty();
    }


}
