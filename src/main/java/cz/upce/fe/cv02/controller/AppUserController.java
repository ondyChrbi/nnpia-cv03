package cz.upce.fe.cv02.controller;

import cz.upce.fe.cv02.domain.AppUser;
import cz.upce.fe.cv02.dto.AppUserResponseDtoV1;
import cz.upce.fe.cv02.dto.AppUserResponseInputDtoV1;
import cz.upce.fe.cv02.service.AppUserService;
import cz.upce.fe.cv02.service.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app-user")
@AllArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;

    @GetMapping("")
    public ResponseEntity<List<AppUserResponseDtoV1>> findAll() {
        var result = appUserService.findAllByActiveEquals();

        return ResponseEntity.ok(result
                .stream()
                .map(AppUserController::toDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) throws ResourceNotFoundException {
        var result = appUserService.findById(id);

        return ResponseEntity.ok(toDto(result));
    }

    @PostMapping("")
    public ResponseEntity<AppUserResponseDtoV1> create(@RequestBody final AppUserResponseInputDtoV1 input) {
        var result = appUserService.create(toEntity(input));

        return ResponseEntity.ok(toDto(result));
    }

    private static AppUserResponseDtoV1 toDto(final AppUser appUser) {
        return new AppUserResponseDtoV1(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getActive(),
                appUser.getCreationDate(),
                appUser.getUpdateDate(),
                appUser.getRoles()
        );
    }

    private static AppUser toEntity(final AppUserResponseInputDtoV1 input) {
        return new AppUser(
                input.getUsername(),
                input.getPassword(),
                input.getActive(),
                input.getCreationDate(),
                input.getUpdateDate()
        );
    }
}
