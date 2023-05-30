package ase.category;

import ase.activity.Activity;
import ase.timeframe.TimeFrameDTOToTimeFrameMapper;
import ase.types.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryDTOToCategoryMapper implements Function<CategoryDTO, Category> {
    private TimeFrameDTOToTimeFrameMapper timeFrameDTOToTimeFrameMapper;
    @Autowired
    public CategoryDTOToCategoryMapper(TimeFrameDTOToTimeFrameMapper timeFrameDTOToTimeFrameMapper){
        this.timeFrameDTOToTimeFrameMapper = timeFrameDTOToTimeFrameMapper;
    }
    @Override
    public Category apply(CategoryDTO categoryDTO) { return map(categoryDTO);}
    private Category map(CategoryDTO categoryDTO){
        if(categoryDTO != null) {
            return new Category(categoryDTO.getId(), categoryDTO.getName(), timeFrameDTOToTimeFrameMapper.apply(categoryDTO.getTimeFrameDTO()));
        }
        else{
            return null;
        }
    }
}
