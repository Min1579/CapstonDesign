package org.devs.heythere_backend.chat;

import org.springframework.data.repository.CrudRepository;

public interface ChatHistoryRepository extends CrudRepository<ChatHistory, Long> {
}
