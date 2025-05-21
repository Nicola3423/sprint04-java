package br.com.fiap.sprint03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var medico = User.withUsername("medico")
                .password("{noop}medico")
                .roles("MEDICO")
                .build();

        var paciente = User.withUsername("paciente")
                .password("{noop}paciente")
                .roles("PACIENTE")
                .build();

        return new InMemoryUserDetailsManager(medico, paciente);
    }

    // Configuração de Segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/ai-chat",      // Nova rota GET para o formulário
                                "/ai-chat/**"    // Cobre POST e quaisquer subcaminhos
                        ).permitAll()
                        .requestMatchers("/medicos/**").hasRole("MEDICO")
                        .requestMatchers("/pacientes/**").hasAnyRole("MEDICO", "PACIENTE")
                        .requestMatchers("/initial").hasAnyRole("MEDICO", "PACIENTE")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/initial", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/?logout")
                        .permitAll()
                )
                .exceptionHandling(handling -> handling
                        .accessDeniedPage("/acesso-negado")
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}