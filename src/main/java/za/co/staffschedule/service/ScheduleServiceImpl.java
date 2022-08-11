package za.co.staffschedule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.staffschedule.dto.ScheduleDTO;
import za.co.staffschedule.exception.ConflictException;
import za.co.staffschedule.exception.NotFoundException;
import za.co.staffschedule.exception.ScheduleException;
import za.co.staffschedule.mapper.ScheduleMapper;
import za.co.staffschedule.model.Schedule;
import za.co.staffschedule.repository.ScheduleRepository;
import za.co.staffschedule.request.ScheduleRequest;
import za.co.staffschedule.request.ScheduleResponse;
import za.co.staffschedule.response.StaffResponse;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired private ScheduleRepository scheduleRepository;

    private ScheduleMapper scheduleMapper;

    @Override
    public ScheduleResponse createSchedule(ScheduleRequest request) throws ScheduleException {
        ScheduleDTO shift = request.getScheduleDetails();
        validateSchedule(shift);

        Schedule newShift = scheduleMapper.convertToDTO(shift);
        newShift = scheduleRepository.save(newShift);
        log.info(String.format("User has been scheduled successfully for the following work date %s", newShift.getWorkDate()));
        return getScheduleResponse(newShift);
    }

    @Override
    public void deleteSchedule(Long scheduleId) throws ScheduleException {
        Schedule shift = fetchScheduleById(scheduleId);
        scheduleRepository.delete(shift);
    }

    @Override
    public StaffResponse getSchedules() throws ScheduleException {
        return null;
    }

    @Override
    public ScheduleResponse getScheduleById(Long scheduleId) throws ScheduleException {
        Schedule shift = fetchScheduleById(scheduleId);
        return new ScheduleResponse(scheduleMapper.convert(shift));
    }

    @Override
    public List<Schedule> getAllSchedules(String username) {
        return scheduleRepository.findAll();
    }

    @Override
    public ScheduleResponse updateSchedule(ScheduleRequest request) throws ScheduleException {
        ScheduleDTO shift = request.getScheduleDetails();
        fetchScheduleById(shift.getId());

        Schedule updateShift = scheduleMapper.convertToDTO(shift);
        updateShift = scheduleRepository.save(updateShift);
        log.info("User's schedule has been updated successfully");
        return getScheduleResponse(updateShift);
    }

    private void validateSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = getShift(scheduleDTO);
        if (Objects.nonNull(schedule)) throw new ConflictException("User is already scheduled for this shift");
    }

    private ScheduleResponse getScheduleResponse(Schedule shift) {
        return new ScheduleResponse(scheduleMapper.convert(shift));
    }

    private Schedule getShift(ScheduleDTO scheduleDTO) {
        return scheduleRepository.findByStaffUserNameAndWorkDate(scheduleDTO.getStaff_id(), scheduleDTO.getWorkDate());
    }

    private Schedule fetchScheduleById(Long id) {
        Optional<Schedule> userOptional = scheduleRepository.findById(id);
        if (!userOptional.isPresent()) throw new NotFoundException("User not found");
        return userOptional.get();
    }
}
