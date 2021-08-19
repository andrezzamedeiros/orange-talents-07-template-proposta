package com.propostas.Propostas.proposal;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
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

