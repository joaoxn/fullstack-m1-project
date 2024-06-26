package com.fullstack.educacional.infra.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${jwt.public.key}")
    RSAPublicKey key;
    @Value("${jwt.private.key}")
    RSAPrivateKey priv;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/login").permitAll() // permite os endpoints que tenham o texto que condiz com /login
                        .requestMatchers(HttpMethod.PUT, "/usuarios/senha").authenticated()
                        // Acesso de ALUNO+ => Todos os endpoints que são GET e começando em alunos/{id}
                        .requestMatchers(HttpMethod.GET, "/alunos/**")
                        .hasAnyAuthority("SCOPE_ALUNO", "SCOPE_PROFESSOR", "SCOPE_PEDAGOGICO", "SCOPE_ADM")
                        // Acesso de PROFESSOR+ => Todos os endpoints que não são DELETE e começando em notas ou igual a alunos/{id}/notas
                        .requestMatchers(req ->
                                req.getRequestURI().startsWith("/notas") &&
                                        !req.getMethod().equals(HttpMethod.DELETE.toString()))
                        .hasAnyAuthority("SCOPE_PROFESSOR", "SCOPE_ADM")
                        // Acesso de RECRUITER+ => Todos os endpoints que não são DELETE e começando em docentes
                        .requestMatchers(req ->
                                req.getRequestURI().startsWith("/docentes") &&
                                        !req.getMethod().equals(HttpMethod.DELETE.toString()))
                        .hasAnyAuthority("SCOPE_RECRUITER", "SCOPE_PEDAGOGICO", "SCOPE_ADM")
                        // Acesso de PEDAGOGICO+ => Todos os endpoints que não são DELETE e começando em turmas, cursos, docentes ou alunos
                        .requestMatchers(req -> (
                                req.getRequestURI().startsWith("/turmas") ||
                                        req.getRequestURI().startsWith("/cursos")
                        ) && !req.getMethod().equals(HttpMethod.DELETE.toString()))
                        .hasAnyAuthority("SCOPE_PEDAGOGICO", "SCOPE_ADM")
                        // Acesso de ADM => Todos os endpoints
                        .requestMatchers(req -> true)
                        .hasAuthority("SCOPE_ADM")

                        .anyRequest().authenticated() // pede autenticação para todos os endpoints que não foram permitidos
                )
                .csrf(AbstractHttpConfigurer::disable) // desabilita o CSRF, ele bloqueia alguns tipos de chamadas por padrão
                .oauth2ResourceServer(oath2 ->
                        oath2.jwt(Customizer.withDefaults()) // configurar o JWT com os padrões de projeto -> os beans JwtDecoder e JwtEncoder
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
        ;
        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder
                .withPublicKey(this.key)
                .build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key)
                .privateKey(this.priv)
                .build();

        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(
                new JWKSet(jwk)
        );
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
