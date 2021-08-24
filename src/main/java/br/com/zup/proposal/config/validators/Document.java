package br.com.zup.proposal.config.validators;

import br.com.zup.proposal.config.validators.DocumentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {DocumentValidator.class})
@Target({ FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface Document {

    String message() default "{Invalid document}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

