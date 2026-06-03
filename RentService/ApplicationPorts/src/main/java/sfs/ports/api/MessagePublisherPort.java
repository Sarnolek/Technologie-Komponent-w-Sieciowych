package sfs.ports.api;

public interface MessagePublisherPort {
    void publishClientCreationFailedEvent(String userId, String reason);
}