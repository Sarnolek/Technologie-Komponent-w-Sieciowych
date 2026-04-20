package sfs.ports.view;

import sfs.domain.model.User;
import sfs.ports.view.dto.CreateFacilityManagerRequest;

public interface FacilityManagerViewPort {
    User createFacilityManager(CreateFacilityManagerRequest request) throws Exception;
}
