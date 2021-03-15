package br.com.usermanagementapi;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ProjectProperties {

    @Value("${api.title}")
    private String apiTitle;

    @Value("${api.version}")
    private String apiVersion;
}
