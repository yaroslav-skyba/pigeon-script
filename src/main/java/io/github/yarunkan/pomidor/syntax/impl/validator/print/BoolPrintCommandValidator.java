package io.github.yarunkan.pomidor.syntax.impl.validator.print;

import io.github.yarunkan.pomidor.syntax.impl.validator.expression.SpecificExpressionValidator;
import io.github.yarunkan.pomidor.syntax.token.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BoolPrintCommandValidator extends SpecificPrintCommandValidator<Boolean> {

    @Autowired
    public BoolPrintCommandValidator(TokenValidator printKeywordValidator, SpecificExpressionValidator<Boolean> boolExpressionValidator) {

        super(printKeywordValidator, boolExpressionValidator);
    }
}