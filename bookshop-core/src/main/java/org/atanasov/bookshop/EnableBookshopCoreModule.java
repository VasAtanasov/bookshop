package org.atanasov.bookshop;

import java.lang.annotation.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(BookshopCoreConfiguration.class)
@Configuration
public @interface EnableBookshopCoreModule {}
