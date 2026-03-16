package com.train.notice.exception;



import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public String handleBadCredentials(BadCredentialsException e, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
        return "redirect:/login";
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUsernameNotFound(UsernameNotFoundException e,
                                         RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "redirect:/login";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException e,
                                                RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
        return "redirect:/login";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

     @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> HandleAccessDeniedException(AccessDeniedException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }



}
