package com.ar.conde.reservaDeTurnos.SpringSecurity;

import com.ar.conde.reservaDeTurnos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class Securityconfig {

    @Autowired
    private UsuarioService usuarioService;

    public void configure(AuthenticationManagerBuilder auth) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService((UserDetailsService) usuarioService);
        auth.authenticationProvider(provider);
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);



        
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_name, password, id_number from usuarios where user_name=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(

                "select user_name, rol from usuarios where user_name=?");

        return jdbcUserDetailsManager;

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                {
                    try {
                        configurer
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/adminsIndex.html").hasRole("ADMIN")
                                .requestMatchers("/odontologosList.html").hasRole("ADMIN")
                                .requestMatchers("/usersIndex.html").hasAnyRole("ADMIN", "USER")

                                .requestMatchers(HttpMethod.GET,"/api/usuarioLogueado").permitAll()

                                .requestMatchers(HttpMethod.GET, "/api/odontologos/").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/api/odontologos/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/odontologos/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/odontologos/").hasRole("ADMIN")


                                .requestMatchers(HttpMethod.POST, "/api/pacientes/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/pacientes/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/pacientes/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/pacientes/").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.POST, "/api/usuarios/").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/usuarios/").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/api/turnos/").hasAnyRole("ADMIN","USER")
                                .requestMatchers(HttpMethod.POST, "/api/turnos/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/turnos/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/turnos/").hasRole("ADMIN")

                                .anyRequest().authenticated().and().formLogin().and().logout();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

        ).exceptionHandling(configurer ->
                configurer.accessDeniedPage("/denied-page"));

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
