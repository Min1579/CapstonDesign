package org.devs.heythere_backend.subscribe;

import lombok.RequiredArgsConstructor;
import org.devs.heythere_backend.user.User;
import org.devs.heythere_backend.user.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class SubscribeService {
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

    public Boolean doSubscribe(final Long targetId, final Long userId) {
        final User targetUser = userRepository.findById(targetId)
                .orElseThrow(() -> new UsernameNotFoundException("user not found, id : " + targetId));

        return subscribeRepository.save(new Subscriber(targetUser)).getId() > 0;
    }

    public void doCancelSubscribe (){

    }
}
