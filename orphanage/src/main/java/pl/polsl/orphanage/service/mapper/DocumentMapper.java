package pl.polsl.orphanage.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.polsl.orphanage.domain.Document;
import pl.polsl.orphanage.service.dto.DocumentDTO;

/**
 * Mapper for the entity Document and its DTO DocumentDTO.
 */
@Mapper(componentModel = "spring", uses = FosterlingMapper.class)
public interface DocumentMapper extends EntityMapper<DocumentDTO,Document>{

    @Mapping(source="fosterling.id", target = "fosterlingId")
    DocumentDTO toDto(Document document);

    @Mapping(source = "fosterlingId", target = "fosterling.id")
    Document toEntity(DocumentDTO documentDTO);

    default Document fromId(Long id) {
        if (id == null) {
            return null;
        }
        Document document = new Document();
        document.setId(id);
        return document;
    }
}
