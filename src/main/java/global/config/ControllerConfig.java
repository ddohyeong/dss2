package global.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import domain.chat.controller.ChatController;
import domain.chat.dao.ChatDao;
import domain.introduce.controller.IntroduceController;
import domain.introduce.dao.IntroduceDao;
import domain.member.controller.LoginController;
import domain.member.controller.MemberController;
import domain.member.dao.MemberDao;
import domain.member.dao.WithdrawlMemberDao;
import domain.member.service.MemberService;
import domain.notice.controller.NoticeController;
import domain.notice.dao.NoticeDao;
import domain.notice.service.NoticeService;
import domain.post.controller.PostController;
import domain.post.controller.PostRestController;
import domain.post.dao.PostAndMemberDao;
import domain.post.dao.PostDao;
import domain.post.dao.PostLikeDao;
import domain.post.dao.PostNameDao;
import domain.post.dto.PostAndMemberMapper;
import domain.post.dto.PostMapper;
import domain.post.service.PostService;
import domain.postApplicationManagement.controller.PostApplicationController;
import domain.postApplicationManagement.controller.PostApplicationRestController;
import domain.postApplicationManagement.dao.PostApplicationMangagementDao;
import domain.postApplicationManagement.dao.PostApplicationPostIdxDao;
import domain.postApplicationManagement.service.PostApplicationManagementService;
import domain.tech.dao.TechDao;
import domain.utils.Conversion;
import domain.utils.Sha256;
import domain.view.ViewController;

@Configuration
@EnableTransactionManagement
public class ControllerConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		
		ds.setDriverClassName("org.mariadb.jdbc.Driver");
		ds.setUrl("jdbc:mariadb://ec2-13-209-64-132.ap-northeast-2.compute.amazonaws.com:3306/dds");
		ds.setUsername("root");
		ds.setPassword("1234");
		
		return ds;
	}
	
	@Bean
	public Sha256 sha256() {
		return new Sha256();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	@Bean
	public Conversion conversion() {
		return new Conversion();
	}
	
	@Bean
	public PostMapper postMapper() {
		return new PostMapper(conversion());
	}

	@Bean
	public PostAndMemberMapper postAndMemberMapper() {
		return new PostAndMemberMapper(conversion());
	}
	
	@Bean
	public ViewController viewController() {
		return new ViewController();
	}
	
	@Bean
	public MemberController memberController() {
		return new MemberController();
	}
	
	@Bean
	public LoginController loginController() {
		return new LoginController();
	}
	
	@Bean 
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberDao());
	}
	@Bean
	public WithdrawlMemberDao withdrawlMemberDao() {
		return new WithdrawlMemberDao(dataSource());
	}
	
	@Bean
	public IntroduceDao introduceDao() {
		return new IntroduceDao(dataSource());
	}
	
	@Bean
	public IntroduceController introduceController() {
		return new IntroduceController();
	}
	
	@Bean 
	public TechDao techDao() {
		return new TechDao(dataSource());
	}
	
	@Bean
	public ChatController chatController() {
		return new ChatController();
	}
	
	@Bean 
	public ChatDao chatDao() {
		return new ChatDao(dataSource());
	}
	
	@Bean
	public PostDao postDao() {
		return new PostDao(dataSource());
	}

	@Bean
	public PostLikeDao postLikeDao() {
		return new PostLikeDao(dataSource());
	}
	
	@Bean
	public PostAndMemberDao postAndMemberDao() {
		return new PostAndMemberDao(dataSource());
	}
	
	@Bean
	public PostNameDao postNameDao() {
		return new PostNameDao(dataSource());
	}
	
	@Bean
	public PostService postService() {
		return new PostService();
	}
	
	@Bean
	public PostController postController() {
		return new PostController();
	}
	
	@Bean
	public PostRestController postRestController() {
		return new PostRestController();
	}
	
	@Bean 
	public PostApplicationManagementService postApplicationManagementService() {
		return new PostApplicationManagementService();
	}
	@Bean
	public PostApplicationRestController applicationRestController() {
		return new PostApplicationRestController();
	}
	
	
	@Bean
	public PostApplicationMangagementDao postApplicationMangagementDao() {
		return new PostApplicationMangagementDao(dataSource());
	}
	
	@Bean
	public PostApplicationController postApplicationController() {
		return new PostApplicationController();
	}
	
	@Bean
	public NoticeDao noticeDao() {
		return new NoticeDao(dataSource());
	}
	
	@Bean
	public NoticeController noticeController() {
		return new NoticeController();
	}
	
	@Bean
	public NoticeService noticeService() {
		return new NoticeService();
	}
	@Bean
	public PostApplicationPostIdxDao postApplicationPostIdxDao() {
		return new PostApplicationPostIdxDao(dataSource());
	}
	
}
