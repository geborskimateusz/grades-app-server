package com.mateuszgeborski.gradesbackend.api.v1.repository.message;

import com.mateuszgeborski.gradesbackend.domain.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository<T extends Message> extends
        JpaRepository<T, Long>,
        PagingAndSortingRepository<T, Long> {

}
