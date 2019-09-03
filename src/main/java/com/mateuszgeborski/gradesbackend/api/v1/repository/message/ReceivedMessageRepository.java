package com.mateuszgeborski.gradesbackend.api.v1.repository.message;

import com.mateuszgeborski.gradesbackend.domain.message.ReceivedMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivedMessageRepository extends MessageRepository <ReceivedMessage> {

    @Query(value =
            "FROM ReceivedMessage m " +
                    "WHERE m.messageContainer = :messageContainer" +
                    " AND m.owner.id = :userDetailsId")
    Page<ReceivedMessage> findReceivedMessagesByUserIdAndMessageContainer(
            @Param("userDetailsId") Long id,
            @Param("messageContainer") String messageContainer,
            Pageable pageable
    );
}
