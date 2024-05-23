package com.training.demo.service;

import com.training.demo.common.dto.CreateTutorial;
import com.training.demo.common.dto.TutorialDto;
import com.training.demo.common.mapper.TutorialMapper;
import com.training.demo.repository.TutorialRepository;
import com.training.demo.repository.UserRepository;
import com.training.demo.repository.entity.Tutorial;
import com.training.demo.repository.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TutorialServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private TutorialRepository tutorialRepository;
    @Mock
    private TutorialMapper tutorialMapper;

    @InjectMocks
    private TutorialService tutorialServiceMock;

    private CreateTutorial createTutorialDto;
    private Tutorial tutoEntity;
    private User userEntity;
    private TutorialDto tutorialDto;


    @BeforeEach
    void setUp() {
        // Mock DTO CreateTutorial
        createTutorialDto = new CreateTutorial();
        createTutorialDto.setTitle("Test_Title");
        createTutorialDto.setContent("Test_Content");
        createTutorialDto.setDescription("Test_Description");
        createTutorialDto.setAuthorId(1L);
        // Mock DTO TutorialDTO
        tutorialDto = new TutorialDto();
        tutorialDto.setId(10L);
        tutorialDto.setTitle("Test_Title");
        // Mock User Entity
        userEntity = new User();
        userEntity.setId(1L);
        userEntity.setEmail("sauvageboris.pro@gmail.com");
        // Mock Tutorial Entiy
        tutoEntity = new Tutorial();
        tutoEntity.setId(10L);
        tutoEntity.setTitle("Test_Title");
    }

    @Test
    @DisplayName("Given valid createTutorial and existing userId, when adding tutorial, then tutorial is created with author")
    void addTutorialSuccess() {
        // Given
        when(tutorialMapper.fromDto(createTutorialDto)).thenReturn(tutoEntity);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        when(tutorialRepository.save(tutoEntity)).thenReturn(tutoEntity);
        when(tutorialMapper.fromEntity(tutoEntity)).thenReturn(tutorialDto);

        // When
        TutorialDto result = tutorialServiceMock.addTutorial(createTutorialDto);

        // Then
        verify(userRepository, times(1)).findById(1L);
        verify(tutorialRepository, times(1)).save(tutoEntity);
        verify(tutorialMapper, times(1)).fromEntity(tutoEntity);
        Assertions.assertEquals(10L, result.getId());
        Assertions.assertEquals("Test_Title", result.getTitle());
    }

    @Test
    @DisplayName("Given valid createTutorial and no existing userId, when adding tutorial, runtime")
    void addTutorialError() {
        // Given
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        Throwable result = Assertions.assertThrows(RuntimeException.class, () -> {
            // When
            tutorialServiceMock.addTutorial(createTutorialDto);
        });

        Assertions.assertEquals(RuntimeException.class, result.getClass());
    }
}
