package io.github.mchuhaievskyi.pomidor.syntax.impl.validator.print;

import io.github.mchuhaievskyi.pomidor.syntax.impl.validator.expression.SpecificExpressionValidator;
import io.github.mchuhaievskyi.pomidor.syntax.token.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StringPrintCommandValidator extends SpecificPrintCommandValidator<String> {

    @Autowired
    public StringPrintCommandValidator(TokenValidator printKeywordValidator,
                                       SpecificExpressionValidator<String> stringExpressionValidator) {

        super(printKeywordValidator, stringExpressionValidator);
    }
}