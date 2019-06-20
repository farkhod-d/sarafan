package uz.gigalab.sarafan.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.Transactional;
import uz.gigalab.sarafan.domain.User;
import uz.gigalab.sarafan.repository.UserDetailsRepository;
import uz.gigalab.sarafan.service.UserDetailsService;

import java.time.LocalDateTime;
import java.util.Optional;

@EnableOAuth2Sso
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .antMatcher("/**")
                    .authorizeRequests()
                .antMatchers("/", "/login**", "/error**", "/js/**", "/favicon.ico")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll()
                .and()
                    .csrf().disable();
        // @formatter:on
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserDetailsService service) {
        return map -> {
            String id = (String) map.get("sub");
            User build = service.findOne(id)
                    .orElseGet(() -> User.builder().id(id).build());

            //region Update data
            User user = build.toBuilder()
                    .name((String) map.get("name"))
                    .email((String) map.get("email"))
                    .gender((String) map.get("gender"))
                    .locale((String) map.get("gender"))
                    .userpic((String) map.get("picture"))
                    .lastVisit(LocalDateTime.now())
                    .build();
            //endregion

            return service.save(user);
        };
    }
}
















