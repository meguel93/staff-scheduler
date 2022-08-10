package za.co.staffschedule.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import za.co.staffschedule.dto.StaffDTO;
import za.co.staffschedule.model.Staff;

@Mapper(componentModel = "spring")
public interface StaffMapper extends Converter<Staff, StaffDTO> {

    Staff convertToDTO(StaffDTO staffDTO);
}
