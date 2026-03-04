package com.examly.springapp.config;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
/**
 * SecurityConfig is a configuration class that sets up security for the
 * application. It enables web security and method security, and configures JWT
 * authentication and authorization.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
 
        @Autowired
        private UserDetailsService myService;
 
        @Autowired
        private JwtAuthenticationFilter myJwtAuthFilter;
 
        @Autowired
        private CorsConfig corsConfig;
 
        private static final String[] WHITELIST = {
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/api/register",
                        "/api/login",
                        "/api/user/{userId}"
                        // "/api/course/**",
                        // "/api/feedback/**",
                        // "/api/material/**",
                        // "/api/enrollment/**"
 
        };
 
        private static final String[] EDUCATOR_ENDPOINTS = {
                        "/api/feedback/user/{userId}",
                        "/api/material/**",
                        "/api/enrollment/course/{courseId}"
 
        };
        private static final String[] EDUCATOR_STUDENTS_ENDPOINTS = {
                        "/api/course/{courseId}",
                        "/api/enrollement/user/{userId}",
                        "/api/material/{materialId}",
                        "/api/material",
                        "/api/enrollment",
                        "/api/enrollment/{enrollmentId}",
                        "/api/material/course/{courseId}",
                        "/api/material/{materialId}",
                        "/api/material",
                        "/api/feedback/user/{userId}",
                        "/api/feedback"
 
        };
 
        private static final String[] STUDENT_ENDPOINTS = {
                        "/api/feedback/**"
 
        };
 
        /**
         * Configures the security filter chain for the application. It disables CSRF,
         * configures URL authorization, sets session management to stateless, and adds
         * the JWT authentication filter.
         *
         * @param http The HttpSecurity object to configure.
         * @return A configured SecurityFilterChain object.
         * @throws Exception If an error occurs during configuration.
         */
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .cors(cors -> cors.configurationSource(corsConfig.corsConfigurationSource()))
                                .csrf().disable()
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(HttpMethod.POST,"/api/course").hasRole("EDUCATOR")
                                                .requestMatchers(HttpMethod.GET,"/api/course").hasAnyRole("EDUCATOR", "STUDENT")
                                                .requestMatchers(HttpMethod.DELETE,"/api/course/{courseId}").hasRole("EDUCATOR")
                                                .requestMatchers(HttpMethod.PUT,"/api/course/{courseId}").hasRole("EDUCATOR")
                                                .requestMatchers(HttpMethod.GET,"/api/course/{courseId}").hasAnyRole("EDUCATOR","STUDENT")
                                                .requestMatchers(HttpMethod.POST,"/api/material").hasRole("EDUCATOR")
                                                .requestMatchers(HttpMethod.DELETE,"/api/material/{materialId}").hasRole("EDUCATOR")
                                                .requestMatchers(HttpMethod.GET,"/api/material/{materialId}").hasAnyRole("EDUCATOR","STUDENT")
                                                .requestMatchers(HttpMethod.GET,"/api/enrollment/{enrollmentId}").hasAnyRole("EDUCATOR","STUDENT")
                                                .requestMatchers(HttpMethod.PUT,"/api/enrollment/{enrollmentId}").hasAnyRole("EDUCATOR")
                                                .requestMatchers(HttpMethod.POST,"/api/enrollment").hasAnyRole("STUDENT")
                                                .requestMatchers(HttpMethod.DELETE,"/api/enrollment/{enrollmentId}").hasAnyRole("STUDENT")
                                                .requestMatchers(HttpMethod.DELETE,"/api/enrollment/{enrollmentId}").hasAnyRole("EDUCATOR","STUDENT")
                                                .requestMatchers(HttpMethod.GET,"/api/enrollment/{enrollmentId}").hasAnyRole("EDUCATOR","STUDENT")
                                                .requestMatchers(HttpMethod.POST,"/api/feedback").hasAnyRole("STUDENT")
                                                .requestMatchers(HttpMethod.DELETE,"/api/feedback").hasAnyRole("STUDENT")
                                               
                                                .requestMatchers(WHITELIST).permitAll()
                                                .requestMatchers(EDUCATOR_STUDENTS_ENDPOINTS)
                                                .hasAnyRole("EDUCATOR", "STUDENT")
                                                .requestMatchers(STUDENT_ENDPOINTS).hasRole("STUDENT")
                                                .anyRequest().authenticated())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider())
                                .addFilterBefore(myJwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();
        }
 
        /**
         * Creates a PasswordEncoder bean that uses BCrypt hashing algorithm.
         *
         * @return A BCryptPasswordEncoder object.
         */
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
 
        /**
         * Creates an AuthenticationProvider bean that uses DaoAuthenticationProvider.
         * It sets the custom UserDetailsService and PasswordEncoder.
         *
         * @return A configured DaoAuthenticationProvider object.
         */
        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                authenticationProvider.setUserDetailsService(myService);
                authenticationProvider.setPasswordEncoder(passwordEncoder());
                return authenticationProvider;
        }
 
        /**
         * Creates an AuthenticationManager bean using the provided
         * AuthenticationConfiguration.
         *
         * @param config The AuthenticationConfiguration object to configure the
         *               AuthenticationManager.
         * @return A configured AuthenticationManager object.
         * @throws Exception If an error occurs during configuration.
         */
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
                return config.getAuthenticationManager();
        }
}
 