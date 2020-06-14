package org.devs.heythere_backend.community;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.board.BoardRepository;
import org.devs.heythere_backend.user.User;
import org.devs.heythere_backend.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityService {
    private final UserRepository userRepository;
    private final CommunityPostRepository postRepository;
    private final CommunityPostCommentRepository communityPostCommentRepository;

    @Transactional
    public CommunityMainResponseDto getCommunityOwnerInfo(final Long ownerId) {
        User communityOwner = userRepository.findById(ownerId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return CommunityMainResponseDto.builder()
                .name(communityOwner.getName())
                .picture(communityOwner.getPicture())
                .build();
    }

    public List<CommunityPostResponseDto> postOnCommunityAndGetAllPosts(Long ownerId, MultipartFile picture, String content) {
        return null;
    }

    /*
    @Transactional
    public List<CommunityPostResponseDto> postOnCommunityAndGetAllPosts(final Long ownerId,
                                                                        final MultipartFile picture,
                                                                        final String content) {


    }

     */
}
