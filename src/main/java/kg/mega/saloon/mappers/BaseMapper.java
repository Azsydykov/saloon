package kg.mega.saloon.mappers;
import kg.mega.saloon.enums.WorkDayEnum;

import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.Map;

@MappedSuperclass
public interface BaseMapper<E,D> {

    E toEntity(D d);
    D toDto(E e);
    List<E> toEntities(List<D> list);
    List<D> toDtos (List<E> list);



}
