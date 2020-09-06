package io.github.yarunkan.pomidor.syntax.impl.validator.operation;

import io.github.yarunkan.pomidor.syntax.impl.validator.expression.SpecificExpressionValidator;
import io.github.yarunkan.pomidor.syntax.impl.validator.operand.SpecificOperandValidator;
import io.github.yarunkan.pomidor.syntax.token.TokenValidator;

public abstract class SpecificOperationValidator<T> implements TokenValidator {

    private final SpecificOperandValidator<T> operandValidator;
    private final TokenValidator assigmentOperatorValidator;
    private final SpecificExpressionValidator<T> expressionValidator;

    public SpecificOperationValidator(SpecificOperandValidator<T> operandValidator,
                                      TokenValidator assigmentOperatorValidator,
                                      SpecificExpressionValidator<T> expressionValidator) {

        this.operandValidator = operandValidator;
        this.assigmentOperatorValidator = assigmentOperatorValidator;
        this.expressionValidator = expressionValidator;
    }

    @Override
    public boolean validate(String... sourceCodeTokens) {

        if (!preValidate(sourceCodeTokens)) {

            return false;
        }

        if (!operandValidator.validate(sourceCodeTokens[0])) {

            return false;
        }

        if (!assigmentOperatorValidator.validate(sourceCodeTokens[1])) {

            return false;
        }

        final int notExpressionTokensCount = 2;
        final int expressionTokensCount = sourceCodeTokens.length - notExpressionTokensCount;
        final String[] expressionTokens = new String[expressionTokensCount];

        System.arraycopy(sourceCodeTokens, 2, expressionTokens, 0, expressionTokensCount);

        return expressionValidator.validate(expressionTokens);
    }

    @Override
    public boolean preValidate(String... sourceCodeTokens) {

        return sourceCodeTokens != null && sourceCodeTokens.length % 2 != 0;
    }

    @Override
    public int getExpectedTokensCount() {

        return 3;
    }
}