package com.example;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
public class DemoConfigurerAdapter extends RepositoryRestConfigurerAdapter {

    @Autowired(required = false)
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    @Autowired
    private JacksonProperties jacksonProperties;

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {

        // Mimic the SpringBootRepositoryRestConfigurer because it is only loaded when @ConditionalOnMissingBean(RepositoryRestMvcConfiguration.class)
        // Please remove these lines once the RestServicesCoreConfig no longer extends RepositoryRestMvcConfiguration. Then we will let spring-boot configure
        // the jackson's objectMapper.
        if (this.objectMapperBuilder != null) {
            this.objectMapperBuilder.configure(objectMapper);
        }

        objectMapper.registerModule(new SimpleModule("NemesisModule") {
            /**
             * Default serial version uid.
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void setupModule(Module.SetupContext context) {
                context.addAbstractTypeResolver(new SimpleAbstractTypeResolver().addMapping(Map.class, HashMap.class));

                SimpleSerializers serializers = new SimpleSerializers();
                //serializers.addSerializer(LocalDateTime.class, new DateSerializer(date2StringConverter));
                //serializers.addSerializer(InternetAddress.class, new InternetAddressSerializer());

                SimpleDeserializers deserializers = new SimpleDeserializers();

                deserializers.addDeserializer(Map.class, new LocalizableMapDeserializer());
                deserializers.addDeserializer(LocalizedValue.class, new LocalizedValueDeserializer());
                deserializers.addDeserializer(Locale.class, new LocaleDeserializer());

                context.addSerializers(serializers);
                context.addDeserializers(deserializers);
            }
        });

        Hibernate5Module module = new Hibernate5Module();
        module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        objectMapper.registerModule(module);

        jacksonProperties.getSerialization().entrySet().stream().forEach(e -> objectMapper.configure(e.getKey(), e.getValue()));
        jacksonProperties.getDeserialization().entrySet().stream().forEach(e -> objectMapper.configure(e.getKey(), e.getValue()));

        //objectMapper.registerModule(new MoneyModule());
    }

}
