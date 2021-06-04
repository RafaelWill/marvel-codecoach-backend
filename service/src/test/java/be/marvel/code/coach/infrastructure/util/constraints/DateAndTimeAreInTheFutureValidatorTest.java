package be.marvel.code.coach.infrastructure.util.constraints;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DateAndTimeAreInTheFutureValidatorTest {

    private final DateAndTimeAreInTheFutureValidator validator = new DateAndTimeAreInTheFutureValidator();

    private static final String DATE = "date";
    private static final String TIME = "time";


    @Nested
    class IsValid{

        @BeforeEach
        void setUp() {
            DateAndTimeAreInTheFuture mockDATAITF = mock(DateAndTimeAreInTheFuture.class);
            when(mockDATAITF.date()).thenReturn(DATE);
            when(mockDATAITF.time()).thenReturn(TIME);
            validator.initialize(mockDATAITF);
        }

        @Test
        void isValid_givenValidDateAndTime_thenTrue() {
            //GIVEN
            String validDate = "01/01/2038";
            String validTime = "08:08";
            Object mockObject = mock(Object.class);
            try (MockedStatic<PropertyUtils> utils = Mockito.mockStatic(PropertyUtils.class)) {
                utils.when(() -> PropertyUtils.getProperty(mockObject,DATE)).thenReturn(validDate);
                utils.when(() -> PropertyUtils.getProperty(mockObject,TIME)).thenReturn(validTime);
                //WHEN
                boolean actualResult = validator.isValid(mockObject,mock(ConstraintValidatorContext.class));
                //THEN
                assertThat(actualResult).isTrue();
            }
        }

        @Test
        void isValid_givenNullDateOrTime_thenTrue() {
            //GIVEN
            String validDate = "01/01/2038";
            String validTime = "08:08";
            Object mockObject = mock(Object.class);
            // time == null
            try (MockedStatic<PropertyUtils> utils = Mockito.mockStatic(PropertyUtils.class)) {
                utils.when(() -> PropertyUtils.getProperty(mockObject,DATE)).thenReturn(validDate);
                utils.when(() -> PropertyUtils.getProperty(mockObject,TIME)).thenReturn(null);
                //WHEN
                boolean actualResult = validator.isValid(mockObject,mock(ConstraintValidatorContext.class));
                //THEN
                assertThat(actualResult).isTrue();
            }
            // date == null
            try (MockedStatic<PropertyUtils> utils = Mockito.mockStatic(PropertyUtils.class)) {
                utils.when(() -> PropertyUtils.getProperty(mockObject,DATE)).thenReturn(null);
                utils.when(() -> PropertyUtils.getProperty(mockObject,TIME)).thenReturn(validTime);
                //WHEN
                boolean actualResult = validator.isValid(mockObject,mock(ConstraintValidatorContext.class));
                //THEN
                assertThat(actualResult).isTrue();
            }
            //time == null, date == null
            try (MockedStatic<PropertyUtils> utils = Mockito.mockStatic(PropertyUtils.class)) {
                utils.when(() -> PropertyUtils.getProperty(mockObject,DATE)).thenReturn(null);
                utils.when(() -> PropertyUtils.getProperty(mockObject,TIME)).thenReturn(null);
                //WHEN
                boolean actualResult = validator.isValid(mockObject,mock(ConstraintValidatorContext.class));
                //THEN
                assertThat(actualResult).isTrue();
            }
        }

        @Test
        void isValid_givenInvalidDateOrTime_thenTrue() {
            //GIVEN
            String validDate = "01/01/2038";
            String validTime = "08:08";
            String invalidDate = "invalidDate";
            String invalidTime = "invalidTime";
            Object mockObject = mock(Object.class);
            // time == invalidTime
            try (MockedStatic<PropertyUtils> utils = Mockito.mockStatic(PropertyUtils.class)) {
                utils.when(() -> PropertyUtils.getProperty(mockObject,DATE)).thenReturn(validDate);
                utils.when(() -> PropertyUtils.getProperty(mockObject,TIME)).thenReturn(invalidTime);
                //WHEN
                boolean actualResult = validator.isValid(mockObject,mock(ConstraintValidatorContext.class));
                //THEN
                assertThat(actualResult).isTrue();
            }
            // date == invalidDate
            try (MockedStatic<PropertyUtils> utils = Mockito.mockStatic(PropertyUtils.class)) {
                utils.when(() -> PropertyUtils.getProperty(mockObject,DATE)).thenReturn(invalidDate);
                utils.when(() -> PropertyUtils.getProperty(mockObject,TIME)).thenReturn(validTime);
                //WHEN
                boolean actualResult = validator.isValid(mockObject,mock(ConstraintValidatorContext.class));
                //THEN
                assertThat(actualResult).isTrue();
            }
            //time == invalidTime, date == invalidDate
            try (MockedStatic<PropertyUtils> utils = Mockito.mockStatic(PropertyUtils.class)) {
                utils.when(() -> PropertyUtils.getProperty(mockObject,DATE)).thenReturn(invalidDate);
                utils.when(() -> PropertyUtils.getProperty(mockObject,TIME)).thenReturn(invalidTime);
                //WHEN
                boolean actualResult = validator.isValid(mockObject,mock(ConstraintValidatorContext.class));
                //THEN
                assertThat(actualResult).isTrue();
            }
        }

        @Test
        void isValid_givenValidDateAndTimeButInThePast_thenFalse() {
            //GIVEN
            String validDateInThePast = "01/01/2000";
            String validTime = "08:08";
            Object mockObject = mock(Object.class);
            try (MockedStatic<PropertyUtils> utils = Mockito.mockStatic(PropertyUtils.class)) {
                utils.when(() -> PropertyUtils.getProperty(mockObject,DATE)).thenReturn(validDateInThePast);
                utils.when(() -> PropertyUtils.getProperty(mockObject,TIME)).thenReturn(validTime);
                //WHEN
                boolean actualResult = validator.isValid(mockObject,mock(ConstraintValidatorContext.class));
                //THEN
                assertThat(actualResult).isFalse();
            }
        }
    }


}
