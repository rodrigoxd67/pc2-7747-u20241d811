package com.campuslab.platform.u20241d811.shared.infrastructure.localization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import java.util.List;
import java.util.Locale;

/**
 * Selects a supported response language from Accept-Language.
 * @author rodrigoxd67
 */
@Configuration
public class LocalizationConfiguration {
    /**
     * Configures English and Spanish locales with English as default.
     * @return request locale resolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        var resolver = new AcceptHeaderLocaleResolver();
        resolver.setSupportedLocales(List.of(Locale.ENGLISH, Locale.US,
                Locale.forLanguageTag("es"), Locale.forLanguageTag("es-PE")));
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }
}
