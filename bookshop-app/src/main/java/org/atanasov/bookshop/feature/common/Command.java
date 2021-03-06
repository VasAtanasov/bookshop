package org.atanasov.bookshop.feature.common;

import java.util.List;

public interface Command {
    void execute(List<String> arguments);

    String getName();

    String helpString();
}
