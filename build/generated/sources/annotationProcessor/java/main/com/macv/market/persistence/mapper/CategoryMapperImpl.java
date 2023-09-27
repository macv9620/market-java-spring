package com.macv.market.persistence.mapper;

import com.macv.market.domain.Category;
import com.macv.market.persistence.entity.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-26T19:27:04-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 11.0.20 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        Category category = new Category();

        if ( categoria.getIdCategoria() != null ) {
            category.setId( categoria.getIdCategoria() );
        }
        category.setDescription( categoria.getDescripcion() );
        if ( categoria.getEstado() != null ) {
            category.setActive( categoria.getEstado() );
        }

        return category;
    }

    @Override
    public List<Category> toCategories(List<Categoria> categorias) {
        if ( categorias == null ) {
            return null;
        }

        List<Category> list = new ArrayList<Category>( categorias.size() );
        for ( Categoria categoria : categorias ) {
            list.add( toCategory( categoria ) );
        }

        return list;
    }

    @Override
    public Categoria toCategoria(Category category) {
        if ( category == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setIdCategoria( category.getId() );
        categoria.setDescripcion( category.getDescription() );
        categoria.setEstado( category.isActive() );

        return categoria;
    }
}
