package shop.mtcoding.blog._core.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.mtcoding.blog._core.interceptor.DeleteInterceptor;
import shop.mtcoding.blog._core.interceptor.LoginInterceptor;
import shop.mtcoding.blog._core.interceptor.UpdateInterceptor;

@Configuration // IoC
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/board/**", "/user/**")
                .addPathPatterns("/board/**", "/user/**","/reply/**")
                .excludePathPatterns("/board/{id:\\d+}");
//        registry.addInterceptor((new UpdateInterceptor()))
//                .addPathPatterns("/board/{id:\\d+}/**")
//                .excludePathPatterns("/board/{id:\\d+}");
    }
}