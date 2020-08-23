package io.github.mchuhaievskyi.pomidor.syntax.impl.interpreter.print;

import io.github.mchuhaievskyi.pomidor.syntax.impl.interpreter.expression.SpecificExpressionTokenInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintIntCommandTokenInterpreter extends SpecificPrintCommandTokenInterpreter<Long> {

    @Autowired
    public PrintIntCommandTokenInterpreter(SpecificExpressionTokenInterpreter<Long> intExpressionTokenInterpreter) {

        super(intExpressionTokenInterpreter);
    }
}