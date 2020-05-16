package org.devs.heythere_backend.system;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.user.User;
import org.devs.heythere_backend.user.UserRepository;
import org.devs.heythere_backend.user.UserStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SystemService {
    private final UserRepository userRepository;

    @Transactional
    public List<UserStatusOnUserListResponseDto> getAllUserByStatusIsOn() {
        return userRepository.findByUserStatusIsOn(UserStatus.ON).stream()
                .map(user -> {
                    return UserStatusOnUserListResponseDto.builder()
                            .id(user.getId())
                            .email(user.getEmail())
                            .name(user.getName())
                            .username(user.getUsername())
                            .picture(user.getPicture())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
