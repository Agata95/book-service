package j25.cloud.cloud.mapper;
import j25.cloud.cloud.model.Book;
import j25.cloud.cloud.model.dto.CreateBookRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book createBookFromDto(CreateBookRequest dto);
}
