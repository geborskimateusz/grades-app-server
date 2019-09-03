package com.mateuszgeborski.gradesbackend.api.v1.service.message;

public interface DeletedMessageService extends BaseMessageService {

    void putMessageToDeletedContainer(String messageType, Long messageId);

    void deleteById(Long msgId);
}
