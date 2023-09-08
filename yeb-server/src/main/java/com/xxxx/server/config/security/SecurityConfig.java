package com.xxxx.server.config.security;


import com.xxxx.server.config.security.component.*;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security配置类
 * @since 1.0.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private IAdminService adminService;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	@Autowired
	private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
	@Autowired
	private CustomFilter customFilter;
	@Autowired
	private CustomUrlDecisionManager customUrlDecisionManager;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//使用JWT，不需要csrf
		http.csrf()
				.disable()
				//基于token，不需要session
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				//所有请求都要求认证
				.anyRequest()
				.authenticated()
				//动态权限配置
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O object) {
						object.setAccessDecisionManager(customUrlDecisionManager);
						object.setSecurityMetadataSource(customFilter);
						return object;
					}
				})
				.and()
				//禁用缓存
				.headers()
				.cacheControl();
		//添加jwt 登录授权过滤器
		http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		//添加自定义未授权和未登录结果返回
		http.exceptionHandling()
				.accessDeniedHandler(restfulAccessDeniedHandler)
				.authenticationEntryPoint(restAuthenticationEntryPoint);
	}

	//放行静态资源
	@Override
	public void configure(WebSecurity web) throws Exception {
		//放行静态资源
		web.ignoring().antMatchers(
				"/login",
				"/logout",
				"/css/**",
				"/js/**",
				"/index.html",
				"/favicon.ico",
				"/doc.html",
				"/webjars/**",
				"/swagger-ui.html",
				"/swagger-resources/**",
				"/v2/api-docs/**",
				"/captcha");
	}

	//定义一个密码编码类
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//获取用户信息 , 账号 , 密码, 权限
	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		//获取登录用户信息
		return username -> {
			Admin admin = adminService.getAdminByUserName(username);
			if (null != admin) {
				admin.setRoles(adminService.getRoles(admin.getId()));
				return admin;
			}
			return null;
		};
	}


	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
		return new JwtAuthenticationTokenFilter();
	}
}