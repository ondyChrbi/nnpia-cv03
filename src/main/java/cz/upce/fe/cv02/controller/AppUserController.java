package cz.upce.fe.cv02.controller;

import cz.upce.fe.cv02.domain.AppUser;
import cz.upce.fe.cv02.repository.AppUserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app-user")
public class AppUserController {
    private final AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("")
    public List<AppUser> findAll() {
        return appUserRepository.findAllByActiveEquals(true);
    }

    @GetMapping("/{id}")
    public AppUser findById(@PathVariable final Long id) {
        var result = appUserRepository.findById(id);

        return result.orElse(null);
    }
}
