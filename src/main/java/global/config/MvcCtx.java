package global.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import domain.vaildator.MemberInfoValidator;
import global.interceptor.LoginCheckInterceptor;

@Configuration
@EnableWebMvc
public class MvcCtx implements WebMvcConfigurer {
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/",".jsp");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/view/naver_login").setViewName("naver_login");
		registry.addViewController("/view/find_id").setViewName("find_id");
		registry.addViewController("/view/find_pw").setViewName("find_pw");
		registry.addViewController("/view/withdraw").setViewName("withdraw");
		registry.addViewController("/view/dormant").setViewName("dormant");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginCheckInterceptor())
			.addPathPatterns("/edit/**"
					, "/member/logout"
					, "/view/my_introduce"
					, "/view/registed_post"
					, "/view/notice_list"
					, "/view/chat"
					, "/my_info/add"
					, "/post/application_add"
					, "/introduce_update"
					, "/confirm/application_status"
					, "/post/update"
					, "/view/post_update"
					,"/post/delete"
					,"/view/post_form"
					, "/reload/chat"
					,"/send_message"
					,"/my_info/update"
					,"/pw_update"
					,"/notice_delete"
					,"/post/like_add"
					,"/post/application_delete"
					,"/member/update"
					,"/member/delete"
					);
	}
	
	@Bean
	public LoginCheckInterceptor loginCheckInterceptor() {
		return new LoginCheckInterceptor();
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		
		ms.setBasename("message.label");
		ms.setDefaultEncoding("UTF-8");
		
		return ms;
	}

}
