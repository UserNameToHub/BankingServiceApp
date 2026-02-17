package ru.yandex.practicum.mybankfront.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.mybankfront.client.TransferClient;
import ru.yandex.practicum.mybankfront.controller.dto.AccountResponse;
import ru.yandex.practicum.mybankfront.controller.dto.CashAction;
import ru.yandex.practicum.mybankfront.controller.dto.ResponseShort;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final TransferClient transferClient;

    @GetMapping
    public String index() {
        return "redirect:/account";
    }

    @GetMapping("/account")
    public String getAccount(Model model) {
        AccountResponse response = transferClient.submit2GatewayGET("/account", null, AccountResponse.class);
        model.addAttribute("name", response.getName());
        model.addAttribute("birthday", response.getBirthdate());
        model.addAttribute("sum", response.getAmount());
        model.addAttribute("accounts", response.getAccounts());

        return "main";
    }

    @PostMapping("/account")
    public String editAccount(
            Model model,
            @RequestParam("name") String name,
            @RequestParam("birthdate") LocalDate birthdate
    ) {
        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("birthday", birthdate.format(DateTimeFormatter.ISO_DATE));

        AccountResponse response = transferClient.submit2GatewayPOST("/account", params, AccountResponse.class);
        model.addAttribute("name", response.getName());
        model.addAttribute("birthday", response.getBirthdate());
        model.addAttribute("sum", response.getAmount());
        model.addAttribute("accounts", response.getAccounts());

        return "main";
    }

    @PostMapping("/cash")
    public String editCash(
            Model model,
            @RequestParam("value") int value,
            @RequestParam("action") CashAction action
            ) {
        Map<String, String> params = new HashMap<>();
        params.put("value", String.valueOf(value));
        params.put("action", action.toString());

        ResponseShort response= transferClient.submit2GatewayPOST("/cash", params, ResponseShort.class);
        model.addAttribute("errors", response.getError());
        model.addAttribute("info", response.getInfo());

        return "main";
    }

    @PostMapping("/transfer")
    public String transfer(
            Model model,
            @RequestParam("value") int value,
            @RequestParam("login") String login
    ) {
        Map<String, String> params = new HashMap<>();
        params.put("value", String.valueOf(value));
        params.put("login", login);

        ResponseShort response= transferClient.submit2GatewayPOST("/transfer", params, ResponseShort.class);
        model.addAttribute("errors", response.getError());
        model.addAttribute("info", response.getInfo());

        return "main";
    }
}