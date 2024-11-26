package m320.projekt.payload.mapper;

import m320.projekt.lib.annotations.Mapper;
import m320.projekt.lib.interfaces.CrudMapper;
import m320.projekt.model.Item;
import m320.projekt.model.User;
import m320.projekt.payload.dto.request.ItemReqDTO;
import m320.projekt.payload.dto.response.ItemResDTO;

@Mapper
public class ItemMapper implements CrudMapper<Item, ItemReqDTO, ItemResDTO> {

    @Override
    public ItemResDTO toDTO(Item src) {
        return new ItemResDTO(src.getId(), src.getTitle(), src.getAuthor().getId());
    }

    @Override
    public Item fromDTO(ItemReqDTO dto) {
        Item item = new Item();
        User author = new User();

        author.setId(dto.getAuthorId());

        item.setId(dto.getId());
        item.setTitle(dto.getTitle());
        item.setAuthor(author);

        return item;
    }
}
