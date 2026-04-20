package sfs.ports.view;

import sfs.domain.model.User;
import sfs.ports.view.dto.CreateAdminRequest;

public interface AdminViewPort {
    User createAdmin (CreateAdminRequest request) throws Exception;
}
