package sfs.adapter.rest;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sfs.domain.model.FacilityManager;
import sfs.domain.model.User;
import sfs.ports.api.UserService;
import sfs.ports.view.FacilityManagerViewPort;
import sfs.ports.view.dto.CreateFacilityManagerRequest;


@RestController
@RequestMapping("/api/v1/facility-managers")
public class FacilityManagerRestController implements FacilityManagerViewPort {

    private final UserService userService;

    public FacilityManagerRestController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createFacilityManager(@Valid @RequestBody CreateFacilityManagerRequest request) throws Exception {
        FacilityManager manager = new FacilityManager();
        manager.setLogin(request.getLogin());
        manager.setFirstName(request.getFirstName());
        manager.setLastName(request.getLastName());

        return userService.createFacilityManager(manager);
    }
}