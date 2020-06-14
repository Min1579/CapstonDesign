package org.devs.heythere_backend.video;

import org.devs.heythere_backend.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByUser(final User user);
}
