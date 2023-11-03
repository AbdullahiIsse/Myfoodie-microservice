package com.myfoodie.calendarservice.service;

import com.myfoodie.calendarservice.dto.CalendarRequest;
import com.myfoodie.calendarservice.dto.CalendarResponse;
import com.myfoodie.calendarservice.feign.DishServiceClient;
import com.myfoodie.calendarservice.model.Calendar;
import com.myfoodie.calendarservice.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final DishServiceClient dishServiceClient;

    @Override
    @Transactional
    public void createCalendar(CalendarRequest calendarRequest) {

        Calendar calendar = Calendar.builder()
                .userId(calendarRequest.getUserId())
                .dishId(calendarRequest.getDishId())
                .date(calendarRequest.getDate())
                .build();

        calendarRepository.save(calendar);

        log.info("Calendar meal plan from the user {} is saved", calendarRequest.getUserId());

    }

    @Override
    @Transactional
    public void updateCalendar(String userId, long dishId, CalendarRequest calendarRequest) throws Exception {

        var calender = calendarRepository.findCalendarByUserIdAndDishId(userId, dishId);

        if (calender != null) {

            if (calendarRequest.getDishId() != 0) {
                calender.setDishId(calendarRequest.getDishId());
            }

            if (calendarRequest.getDate() != null) {
                calender.setDate(calendarRequest.getDate());
            }
            calendarRepository.save(calender);

            log.info("Calender meal plan  {} has been updated", calender.getId());
        } else {
            log.error("Calender with UserId {} and dishId {} not found", userId, dishId);
            throw new Exception("Calender with UserId not found");
        }

    }

    @Override
    @Transactional
    public void deleteCalendar(String userId, long dishId) {
        calendarRepository.deleteByUserIdAndDishId(userId, dishId);
        log.info("calender with userId {}  and dishId {} is deleted", userId, dishId);
    }

    @Override
    public List<CalendarResponse> getCalendarEntriesByUserId(String userId) {
        var calendars = calendarRepository.findAllByUserId(userId);
        return calendars.stream()
                .map(calendar -> {
                    var dish = dishServiceClient.getDishesById(calendar.getDishId());
                    return CalendarResponse.builder()
                            .userId(calendar.getUserId())
                            .dishId(calendar.getDishId())
                            .date(calendar.getDate())
                            .name(dish.getName())
                            .description(dish.getDescription())
                            .ingredients(dish.getIngredients())
                            .recipe(dish.getRecipe())
                            .imageURL(dish.getImageURL())
                            .timeEstimate(dish.getTimeEstimate())
                            .nutritionalContent(dish.getNutritionalContent())
                            .mealType(dish.getMealType())
                            .build();
                })
                .toList();
    }

}
