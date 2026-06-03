package sfs.ports.api;

public interface MessagePublisherPort {
    void publishUserCreatedEvent(String userId, String firstName, String lastName);
}