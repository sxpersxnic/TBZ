package com.github.sxpersxnic.tbz.todo.items;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemResponseDTO toDTO(Item item);
    List<ItemResponseDTO> toDTO(List<Item> items);

    @Mapping(target = "id", ignore = true)
    Item fromDTO(ItemRequestDTO requestDTO);
}
