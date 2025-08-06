package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/config")
@CrossOrigin(origins = "*")
public class ConfigController {

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${app.base.url:http://localhost:8080}")
    private String baseUrl;

    @GetMapping
    public Map<String, String> getConfig() {
        Map<String, String> config = new HashMap<>();
        
        // Se não há uma URL base configurada, usar a URL do próprio servidor
        if (baseUrl.equals("http://localhost:8080")) {
            // No Render, usar a URL do próprio serviço
            String renderUrl = System.getenv("RENDER_EXTERNAL_URL");
            if (renderUrl != null) {
                config.put("API_BASE_URL", renderUrl);
            } else {
                config.put("API_BASE_URL", baseUrl);
            }
        } else {
            config.put("API_BASE_URL", baseUrl);
        }
        
        return config;
    }
}
