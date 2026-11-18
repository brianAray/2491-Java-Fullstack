import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<UserEntity> userEntityCaptor;

    private UserEntity sampleUserEntity;

    @BeforeEach
    void setup(){
        sampleUserEntity = new UserEntity(1, "example@example.com", false);
    }

    @Test
    void activateUser_Success(){
        // AAA
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(sampleUserEntity));
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserEntity result = userService.activateUser(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isActive());
        assertEquals("example@example.com", result.getEmail());

        // Verify collaborator interactions
        verify(userRepository, times(1)).findById(1);
        verify(userRepository).save(userEntityCaptor.capture());

        UserEntity savedUser = userEntityCaptor.getValue();
        assertTrue(savedUser.isActive());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when user does not exist")
    void activateUser_NotFound_ThrowsException(){
        // Arrange
        when(userRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.activateUser(999)
        );

        assertEquals("User not found: 999", exception.getMessage());

        // ensure write operations are never reached
        verify(userRepository, never()).save(any());
    }

}
