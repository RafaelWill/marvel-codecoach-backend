package be.marvel.code.coach.service.service;

import be.marvel.code.coach.domain.entity.CoachingTopic;
import be.marvel.code.coach.domain.repository.CoachingTopicRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoachingTopicServiceImplementationTest {

    @Mock
    private CoachingTopicRepository repository;
    @InjectMocks
    private CoachingTopicServiceImplementation service;

    @Nested
    public class GetById {
        @Test
        void getById_givenCorrectParameter_thenVerifyServiceCallsRepository() {
            //GIVEN
            CoachingTopic mockTopic = mock(CoachingTopic.class);
            when(repository.findById(any())).thenReturn(Optional.of(mockTopic));
            //WHEN
            service.getById(any());
            //THEN
            verify(repository).findById(any());
        }

        @Test
        void getById_givenCorrectParameter_thenReturnMatchingCoachingTopic() {
            //GIVEN
            CoachingTopic mockTopic = mock(CoachingTopic.class);
            when(repository.findById(any())).thenReturn(Optional.of(mockTopic));
            //WHEN
            CoachingTopic actualResult = service.getById(any());
            //THEN
            Assertions.assertThat(actualResult).isInstanceOf(CoachingTopic.class);
        }

        @Test
        void getById_givenTopicDoesNotExist_thenThrowIllegalArgumentException() {
            //GIVEN
            when(repository.findById(any())).thenReturn(Optional.empty());
            //THEN
            Assertions.assertThatIllegalArgumentException().isThrownBy(() -> service.getById(any()));
        }
    }

    @Nested
    class GetAllTopicsFromCoach {

        @Test
        void getAllTopicsFromCoach_thenVerifyServiceCallsRepository() {
            //GIVEN
            List mockTopics = mock(List.class);
            when(repository.findAllByCoach_Id(any())).thenReturn(mockTopics);
            //WHEN
            service.getAllTopicsFromCoach(any());
            //THEN
            verify(repository).findAllByCoach_Id(any());
        }

        @Test
        void getAllTopicsFromCoach_thenReturnListOfAllTopicsFromCoach() {
            //GIVEN
            List mockTopics = mock(List.class);
            when(repository.findAllByCoach_Id(any())).thenReturn(mockTopics);
            //WHEN
            List<CoachingTopic> actualResult = service.getAllTopicsFromCoach(any());
            //THEN
            Assertions.assertThat(actualResult).isInstanceOf(Collection.class);
        }

    }

}
