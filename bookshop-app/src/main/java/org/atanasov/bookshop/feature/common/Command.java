package org.atanasov.bookshop.feature.common;

import java.util.List;

public interface Command {
    String execute(List<String> arguments);
}
