package ase.category;


import ase.timeframe.TimeFrameToTimeFrameDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CategoryToCategoryDTOMapper implements Function<Category, CategoryDTO> {
    private TimeFrameToTimeFrameDTOMapper timeFrameToTimeFrameDTOMapper;
    @Autowired
    public CategoryToCategoryDTOMapper(TimeFrameToTimeFrameDTOMapper timeFrameToTimeFrameDTOMapper){
        this.timeFrameToTimeFrameDTOMapper = timeFrameToTimeFrameDTOMapper;
    }
    @Override
    public CategoryDTO apply(Category category) {return map(category);}
    private CategoryDTO map(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), timeFrameToTimeFrameDTOMapper.apply(category.getTimeFrame()));
    }
}
