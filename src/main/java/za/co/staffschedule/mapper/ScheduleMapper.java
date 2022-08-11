package za.co.staffschedule.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import za.co.staffschedule.dto.ScheduleDTO;
import za.co.staffschedule.model.Schedule;

@Mapper(componentModel = "spring")
public interface ScheduleMapper extends Converter<Schedule, ScheduleDTO> {

    Schedule convertToDTO(ScheduleDTO scheduleDTO);
}
