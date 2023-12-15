package com.myfoodie.calendarservice;

import com.myfoodie.calendarservice.dto.CalendarResponse;
import com.myfoodie.calendarservice.repository.CalendarRepository;
import com.myfoodie.calendarservice.service.CalendarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalendarControllerIntTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CalendarRepository calendarRepository;

    @MockBean
    private CalendarService calendarService;

    @Test
    void getCalendarEntriesByUserIdAndDateTest() {
        String testUserId = "user123";
        Date testDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(testDate);

        List<CalendarResponse> expectedResponses = new ArrayList<>();

        when(calendarService.getCalendarEntriesByUserIdAndDate(testUserId, testDate)).thenReturn(expectedResponses);

        CalendarResponse[] calendarResponses = testRestTemplate.getForObject(
                "/api/calendar/" + testUserId + "/" + formattedDate,
                CalendarResponse[].class
        );

        assertThat(calendarResponses).isNotNull();
        assertThat(calendarResponses).hasSize(expectedResponses.size());
        assertThat(calendarResponses).isEqualTo(expectedResponses.toArray(new CalendarResponse[0]));
    }
    @Test
    void connectionEstablished() {
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }

}
