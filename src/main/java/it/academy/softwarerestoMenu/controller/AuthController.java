package it.academy.softwarerestoMenu.controller;//package it.academy.softwarerestoMenu.controller;
//
//import it.academy.softwarerestoMenu.authenticationRequest.AuthenticationRequest;
//import it.academy.softwarerestoMenu.util.JWTUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class RegistrationController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private JWTUtil jwtUtil;
//
//    @GetMapping(value = "/registration")
//    public String getProductName() {
//        return "Main  PAGE";
//    }
//
//
//
//
//    @RequestMapping("/oauth2/authorization/github")
//    public String redirectToGitHubAuthorization() {
//        return "redirect:/oauth2/authorization/{registrationId}";
//    }
//
//
//
//    @PostMapping(value = "/authenticate")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect user or password");
//        }
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        final String jwt = jwtUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }
//
//    @GetMapping("/login/oauth2")
//    public String redirectToAuthorizationEndpoint() {
//        return "redirect:/login/oauth2/code/github";
//    }
//
//    @GetMapping("/login/oauth21")
//    public String redirectToAuthorizationEndpoint1() {
//        return "redirect:/login/oauth2/code/google";
//    }
//
//    @GetMapping("/login/oauth2/code/github")
//    public ResponseEntity<?> handleGitHubCallback(@RequestParam("code") String code, OAuth2AuthenticationToken authenticationToken) {
//        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authenticationToken.getPrincipal();
//
//        // Get the user's name attribute, if available
//        String username = oauth2User.getAttribute("name");
//        System.out.println();
//        if (username == null) {
//            // Handle the case when the name attribute is null
//            // You can choose to set a default name or return an error response
//            return ResponseEntity.badRequest().body("User name attribute is missing");
//        }
//
//        // Perform necessary actions with the user's name
//        return ResponseEntity.ok("GitLab OAuth2 Login successful for user: " + username);
//    }
//
//    @GetMapping("/login/oauth2/code/google")
//    public ResponseEntity<?> handleGitLabCallback1(@RequestParam("code") String code, OAuth2AuthenticationToken authenticationToken) {
//        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authenticationToken.getPrincipal();
//
//        // Get the user's name attribute, if available
//        String username = oauth2User.getAttribute("name");
//        System.out.println();
//        if (username == null) {
//            // Handle the case when the name attribute is null
//            // You can choose to set a default name or return an error response
//            return ResponseEntity.badRequest().body("User name attribute is missing");
//        }
//
//        // Perform necessary actions with the user's name
//        return ResponseEntity.ok("GitLab OAuth2 Login successful for user: " + userName);
//    }
//}
//


import it.academy.softwarerestoMenu.authenticationRequest.AuthenticationRequest;
import it.academy.softwarerestoMenu.authenticationRequest.AuthneticationResponse;
import it.academy.softwarerestoMenu.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthneticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect user or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthneticationResponse(jwt));
    }

    @GetMapping("/login/oauth2")
    public String redirectToAuthorizationEndpoint() {
        return "redirect:/login/oauth2/code/gitlab";
    }

    @GetMapping("/login/oauth21")
    public String redirectToAuthorizationEndpoint1() {
        return "redirect:/login/oauth2/code/google";
    }

    @GetMapping("/login/oauth2/code/gitlab")
    public ResponseEntity<?> handleGitLabCallback(@RequestParam("code") String code, OAuth2AuthenticationToken authenticationToken) {
        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authenticationToken.getPrincipal();

        // Get the user's name attribute, if available
        String userName = oauth2User.getAttribute("name");
        System.out.println();
        if (userName == null) {
            // Handle the case when the name attribute is null
            // You can choose to set a default name or return an error response
            return ResponseEntity.badRequest().body("User name attribute is missing");
        }

        // Perform necessary actions with the user's name
        return ResponseEntity.ok("GitLab OAuth2 Login successful for user: " + userName);
    }

    @GetMapping("/login/oauth2/code/google")
    public ResponseEntity<?> handleGitLabCallback1(@RequestParam("code") String code, OAuth2AuthenticationToken authenticationToken) {
        DefaultOAuth2User oauth2User = (DefaultOAuth2User) authenticationToken.getPrincipal();

        // Get the user's name attribute, if available
        String userName = oauth2User.getAttribute("name");
        System.out.println();
        if (userName == null) {
            // Handle the case when the name attribute is null
            // You can choose to set a default name or return an error response
            return ResponseEntity.badRequest().body("User name attribute is missing");
        }

        // Perform necessary actions with the user's name
        return ResponseEntity.ok("GitLab OAuth2 Login successful for user: " + userName);
    }
}
