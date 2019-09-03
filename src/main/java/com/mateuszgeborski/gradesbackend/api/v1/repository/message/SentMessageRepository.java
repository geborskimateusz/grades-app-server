package com.mateuszgeborski.gradesbackend.api.v1.repository.message;

import com.mateuszgeborski.gradesbackend.domain.message.SentMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface SentMessageRepository extends MessageRepository<SentMessage> {

    @Query(value =
            "FROM SentMessage m " +
                    "WHERE m.messageContainer = :messageContainer" +
                    " AND m.owner.id = :userDetailsId")
    Page<SentMessage> findSentMessagesByUserIdAndMessageContainer(
            @Param("userDetailsId") Long id,
            @Param("messageContainer") String messageContainer,
            Pageable pageable
    );
}
