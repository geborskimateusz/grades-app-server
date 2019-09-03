package com.mateuszgeborski.gradesbackend.api.v1.repository.message;

import com.mateuszgeborski.gradesbackend.domain.message.DeletedMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface DeletedMessageRepository extends MessageRepository <DeletedMessage> {

    @Query(value =
            "from DeletedMessage m" +
                    " WHERE m.messageContainer = :messageContainer" +
                    " AND (m.owner.id = :userDetailsId)")
    Page<DeletedMessage> findDeletedMessagesByUserIdAndMessageContainer(
            @Param("userDetailsId") Long id,
            @Param("messageContainer") String messageContainer,
            Pageable pageable
    );
}
