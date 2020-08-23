package io.github.mchuhaievskyi.pomidor.syntax.impl.interpreter.operation;

import io.github.mchuhaievskyi.pomidor.database.PomidorVariablesDatabase;
import io.github.mchuhaievskyi.pomidor.database.PomidorVariablesDatabaseImpl;
import io.github.mchuhaievskyi.pomidor.syntax.PomidorParser;
import io.github.mchuhaievskyi.pomidor.syntax.PomidorToken;
import io.github.mchuhaievskyi.pomidor.syntax.impl.PomidorParserImpl;
import io.github.mchuhaievskyi.pomidor.syntax.impl.interpreter.expression.SpecificExpressionTokenInterpreter;
import io.github.mchuhaievskyi.pomidor.syntax.token.PomidorTokenInterpreter;
import java.util.List;

public abstract class SpecificOperationTokenInterpreter<T> extends PomidorTokenInterpreter {

    final PomidorVariablesDatabase variablesDatabase = PomidorVariablesDatabaseImpl.getInstance();

    private final SpecificExpressionTokenInterpreter<T> specificExpressionTokenInterpreter;

    protected SpecificOperationTokenInterpreter(SpecificExpressionTokenInterpreter<T> specificExpressionTokenInterpreter) {

        this.specificExpressionTokenInterpreter = specificExpressionTokenInterpreter;
    }

    @Override
    public boolean interpret(PomidorToken token) {

        final List<PomidorToken> subTokens = token.getSubTokens();
        final String assignableVarName = subTokens.get(0).getSourceCode();
        String assignableVarValue = variablesDatabase.getVariable(assignableVarName);

        if (assignableVarValue == null) {

            return false;
        }

        final List<PomidorToken> expressionTokens = subTokens.subList(2, subTokens.size());
        final String[] expressionSourceCodeLines = new String[expressionTokens.size()];

        for (int i = 0; i < expressionSourceCodeLines.length; i++) {

            expressionSourceCodeLines[i] = expressionTokens.get(i).getSourceCode();
        }

        final String expressionSourceCodeLine = String.join(" ", expressionSourceCodeLines);
        final PomidorParser expressionParser = new PomidorParserImpl(expressionSourceCodeLine, token.getType());
        final PomidorToken expressionToken = expressionParser.takeNextToken();

        if (!specificExpressionTokenInterpreter.interpret(expressionToken)) {

            return false;
        }

        assignableVarValue = specificExpressionTokenInterpreter.getExpressionResult(expressionToken).toString();
        variablesDatabase.setVariable(assignableVarName, assignableVarValue);

        return true;
    }
}