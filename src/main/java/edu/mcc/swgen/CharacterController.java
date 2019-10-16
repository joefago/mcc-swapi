package edu.mcc.swgen;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CharacterController {

    private final SwApiClient swApiClient;

    CharacterController(SwApiClient swApiClient) {
        this.swApiClient = swApiClient;
    }

    @GetMapping({"/", "/character-list"})
    public String findCharacters(@RequestParam(required = false) Integer page, Model model) {
        SwApiCharacterList swApiCharacterList = swApiClient.retrieveCharacters(page);
        model.addAttribute("characters", swApiCharacterList);
        return "characters";
    }
}
