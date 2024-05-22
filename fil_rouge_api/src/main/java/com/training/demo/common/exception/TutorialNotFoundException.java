package com.training.demo.common.exception;

public class TutorialNotFoundException extends RuntimeException {

    public TutorialNotFoundException(Long idTuto) {
        super("Tutoriel does not exist with id=" + idTuto);
    }

}
