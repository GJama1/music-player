package com.study.myprojects.musicplayer.annotation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordValidatorImpl implements ConstraintValidator<Password, CharSequence> {

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value.toString().isBlank()) {
            return throwError(context, "Password must not be blank.");
        }

        PasswordValidator validator = validator();
        RuleResult result = validator.validate(new PasswordData(value.toString()));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = String.join("/n", messages);
        return throwError(context, messageTemplate);
    }

    private PasswordValidator validator() {

        return new PasswordValidator(Arrays.asList(
                new LengthRule(8, 100),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule()
        ));

    }

    private boolean throwError(ConstraintValidatorContext context, String message) {
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

}
