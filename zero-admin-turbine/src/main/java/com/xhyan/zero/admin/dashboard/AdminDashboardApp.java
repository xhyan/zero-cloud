package com.xhyan.zero.admin.dashboard;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.values.InstanceId;
import de.codecentric.boot.admin.server.notify.LoggingNotifier;
import de.codecentric.boot.admin.server.notify.Notifier;
import de.codecentric.boot.admin.server.notify.RemindingNotifier;
import de.codecentric.boot.admin.server.notify.filter.FilteringNotifier;
import java.time.Duration;
import java.util.function.BiFunction;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class AdminDashboardApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminDashboardApp.class).run(args);
    }

    @Configuration
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // Page with login form is served as /login.html and does a POST on /login
            http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();
            // The UI does a POST on /logout on logout
            http.logout().logoutUrl("/logout");
            // The ui currently doesn't support csrf
            http.csrf().disable();

            // Requests for the login page and the static assets are allowed
            http.authorizeRequests()
                .antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
                .permitAll();
            // ... and any other request needs to be authorized
            http.authorizeRequests().antMatchers("/**").authenticated();

            // Enable so that the clients can authenticate via HTTP basic for registering
            http.httpBasic();
        }
    }
    // end::configuration-spring-security[]

    @Configuration
    public static class NotifierConfig {

        @Bean
        public InstanceRepository instanceRepository() {
            return new InstanceRepository() {
                @Override
                public Mono<Instance> save(Instance instance) {
                    return null;
                }

                @Override
                public Flux<Instance> findAll() {
                    return null;
                }

                @Override
                public Mono<Instance> find(InstanceId instanceId) {
                    return null;
                }

                @Override
                public Flux<Instance> findByName(String s) {
                    return null;
                }

                @Override
                public Mono<Instance> compute(InstanceId instanceId,
                    BiFunction<InstanceId, Instance, Mono<Instance>> biFunction) {
                    return null;
                }

                @Override
                public Mono<Instance> computeIfPresent(InstanceId instanceId,
                    BiFunction<InstanceId, Instance, Mono<Instance>> biFunction) {
                    return null;
                }
            };
        }

//        @Bean
//        @Primary
//        public RemindingNotifier remindingNotifier(InstanceRepository repository) {
//
//            RemindingNotifier notifier = new RemindingNotifier(filteringNotifier());
//            notifier.setReminderPeriod(Duration.ofMillis(10));
//            return notifier;
//        }

        @Scheduled(fixedRate = 1_000L)
        public void remind() {
        }

        @Bean
        public Notifier filteringNotifier(RemindingNotifier notifier,
            InstanceRepository repository) {
            return new FilteringNotifier(notifier, repository);
        }

        @Bean
        public LoggingNotifier loggerNotifier(InstanceRepository repository) {
            return new LoggingNotifier(repository);
        }
    }
}
