package in.nic.login.controller;

import in.nic.login.dto.SignUpRequest;
import in.nic.login.model.Client;
import in.nic.login.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/signup")
    public ResponseEntity<Client> signup(@RequestParam String clientId, @RequestBody SignUpRequest signUpRequest) {
        return clientService.signup(clientId, signUpRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam long mobileNo) {
        Optional<String> otp = clientService.login(mobileNo);
        return otp.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().body("Invalid mobile number"));
    }
}
