package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devs.heythere_backend.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NameNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public UserMypageResponseDto findUserByIdAndSendToMypage(final Long userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("USER ID NOT FOUND"));

        return UserMypageResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .picture(user.getPicture())
                .email(user.getEmail())
                .build();
    }

    @Transactional
    public boolean existByUsername(final String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public boolean existByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public Long register(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(RoleName.ROLE_USER);
        user.setRoles(Collections.singleton(userRole));
        user.setStatus(UserStatus.OFF);
        return userRepository.save(user).getId();
    }

    @Transactional
    public List<UserResearchFoundResponseDto> searchByUsernameOrNameOrEmail(final String usernameOrNameOrEmail) {
        return userRepository.findByUsernameContainingOrNameContainingOrEmailContaining(
                usernameOrNameOrEmail,
                usernameOrNameOrEmail,
                usernameOrNameOrEmail).stream()
                .map(user -> UserResearchFoundResponseDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .name(user.getName())
                        .email(user.getEmail())
                        .picture(user.getPicture())
                        .build())
                .collect(Collectors.toList());
//        return userRepository.findByUsernameOrEmail(usernameOrNameOrEmail,usernameOrNameOrEmail);
    }
    @Transactional
    public Long editProfile(final Long userId, final UserProfileEditForm form){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("Not Found UserID : " + userId));
        return user.getId();
    }

    @Transactional
    public Long uploadProfile(final Long userId, final MultipartFile file) throws IOException{
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("Not Found UserID : "+userId));

        String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/profile/";
        Path fileNameAndPath = Paths.get(uploadDirectory + userId + "_" + file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        user.setPicture("http://localhost:8080/profile/" + userId + "_" + file.getOriginalFilename());
        return Long.valueOf("1");
    }



}
