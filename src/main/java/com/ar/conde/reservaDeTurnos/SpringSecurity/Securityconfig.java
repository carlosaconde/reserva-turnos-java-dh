package com.ar.conde.reservaDeTurnos.SpringSecurity;

import com.ar.conde.reservaDeTurnos.entity.Usuario;
import com.ar.conde.reservaDeTurnos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.sql.DataSource;

import static org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.matchers;

@Configuration
@EnableWebSecurity
public class Securityconfig {



    @Autowired
    private UsuarioService usuarioService;

    public void configure(AuthenticationManagerBuilder auth){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService((UserDetailsService) usuarioService);
        auth.authenticationProvider(provider);
    }


   /* @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails pablo = User.builder()
                .username("pablo")
                .password("{noop}test123")
                .roles("ADMIN")
                .build();

        UserDetails carlos = User.builder()
                .username("carlos")
                .password("{noop}test123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(pablo,carlos);
    }*/


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

       jdbcUserDetailsManager.setUsersByUsernameQuery(
               "select user_name, password, id_number from usuarios where user_name=?");

       jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(

               "select user_name, rol from usuarios where user_name=?");

        return jdbcUserDetailsManager;
        
    }




 /*   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.GET, "/pacientes").permitAll()
                .requestMatchers( "/").permitAll()
                .requestMatchers("/usersIndex").hasRole("USER")
                .requestMatchers("/adminsIndex").hasRole("ADMIN")
                .anyRequest().authenticated().and().formLogin().and().logout();

        http.httpBasic();
        return http.build();
    }*/

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


        http.authorizeHttpRequests(configurer ->
                {


                    try {
                        configurer
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/adminsIndex.html").hasRole("ADMIN")
                                .requestMatchers("/odontologosList.html").hasRole("ADMIN")
                                .requestMatchers("/usersIndex.html").hasRole("USER")
                                .requestMatchers(HttpMethod.GET,"/api/odontologos/").hasAnyRole("ADMIN","USER")
                                .requestMatchers(HttpMethod.POST,"/api/odontologos/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/pacientes/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/pacientes/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/usuarios/").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/usuarios/").hasRole("ADMIN")

                                .anyRequest().authenticated().and().formLogin().and().logout();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }


                }


        ).exceptionHandling(configurer->
               configurer.accessDeniedPage("/denied-page") );


        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
   }
}
