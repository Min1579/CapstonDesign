package org.devs.heythere_backend.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.devs.heythere_backend.config.SaveFilePath;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;
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
    public boolean existByName(final String name) {
        return userRepository.existsByUsername(name);
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
                        .userId(user.getId())
                        .username(user.getUsername())
                        .name(user.getName())
                        .email(user.getEmail())
                        .picture(user.getPicture())
                        .build())
                .collect(Collectors.toList());
//        return userRepository.findByUsernameOrEmail(usernameOrNameOrEmail,usernameOrNameOrEmail);
    }

    @Transactional
    public String updatePicture(final Long userId, final MultipartFile picture) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found, Id : " + userId))
                .updateProfilePicture(saveUserProfileImgToLocalServer(picture))
                .getPicture();
    }

    private synchronized MultipartFile saveUserProfileImgToLocalServer(final MultipartFile picture) {
//        File file = new File(picture.getOriginalFilename());
//        file.renameTo(String.format("%ld_%s",userId, picture.getOriginalFilename()));

        final String path = String.format("%s%s", SaveFilePath.FILE_IMG_SAVE_DIR, picture.getOriginalFilename());
        try {
            byte[] file = picture.getBytes();
            final FileOutputStream fos = new FileOutputStream(path);
            fos.write(file);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picture;
    }

    public String getUserPictureById(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found, ID : " + userId))
                .getPicture();
    }

    @Transactional
    public String updateUsername(Long userId, String username) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found, ID : " + userId))
                .updateUsername(username)
                .getUsername();

    }

    @Transactional
    public String updateName(Long userId, String name) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found, ID : " + userId))
                .updateName(name)
                .getName();
    }

    @Transactional
    public String updateEmail(Long userId, String email) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found, ID : " + userId))
                .updateEmail(email)
                .getEmail();
    }


    //this need to update login soon!
    @Transactional
    public List<UserOnStreamingResponseDto> findAllUserOnStreaming() {
        return userRepository.findAll().stream()
                .map(user -> UserOnStreamingResponseDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .name(user.getName())
                        .picture(user.getPicture())
                        .build())
                .collect(Collectors.toList());
    }
}
