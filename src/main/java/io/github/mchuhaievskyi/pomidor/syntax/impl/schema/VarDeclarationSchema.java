package io.github.mchuhaievskyi.pomidor.syntax.impl.schema;

import io.github.mchuhaievskyi.pomidor.syntax.token.TokenParser;
import io.github.mchuhaievskyi.pomidor.syntax.token.TokenSchema;
import io.github.mchuhaievskyi.pomidor.syntax.token.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VarDeclarationSchema implements TokenSchema {

    private final TokenValidator varDeclarationValidator;
    private final TokenParser varDeclarationTokenParser;

    @Autowired
    public VarDeclarationSchema(TokenValidator varDeclarationValidator,
                                TokenParser varDeclarationTokenParser) {

        this.varDeclarationValidator = varDeclarationValidator;
        this.varDeclarationTokenParser = varDeclarationTokenParser;
    }

    @Override
    public String getDescription() {
        return "10) <var-declaration> --> var <var-name> = <literal>\n";
    }

    @Override
    public TokenValidator getTokenValidator() {
        return varDeclarationValidator;
    }

    @Override
    public TokenParser getTokenParser() {
        return varDeclarationTokenParser;
    }
}